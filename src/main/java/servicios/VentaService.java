package servicios;

import api.VentaServicio;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import modelo.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VentaService implements VentaServicio {
    private EntityManagerFactory emf; //= Persistence.createEntityManagerFactory("objectdb:myDbFile.odb");
    private EntityManager em;//= emf.createEntityManager();
    private EntityTransaction tx;//= em.getTransaction();

    public VentaService(String servicio) {
        this.emf = Persistence.createEntityManagerFactory(servicio);

    }

    @Override
    public void realizarVenta(Long idCliente, List<Long> productosLong, Long idTarjeta) {
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();
        List<Producto> productosX = new ArrayList<>();
        try {
            tx.begin();

            if (idCliente == null)
                throw new RuntimeException("El cliente no puede ser nulo.");
            if (productosLong.isEmpty())
                throw new RuntimeException("El carrito no puede estar vacio.");
            if (idTarjeta == null)
                throw new RuntimeException("La tarjeta no debe ser vacia.");

            // validaciones:
            // - debe ser un cliente existente
            // - la lista de productos no debe estar vacía
            // - La tarjeta debe pertenecer al cliente

            Carrito carrito = new Carrito();
            Cliente cliente = em.find(Cliente.class, idCliente);
            if (cliente == null)
                throw new RuntimeException("El cliente no esta registrado.");
            Tarjeta tarjeta = em.find(Tarjeta.class, idTarjeta);
            if (tarjeta == null)
                throw new RuntimeException("La tarjeta no existe");
            if (!cliente.tarjetaPropia(tarjeta))
                throw new RuntimeException("La tarjeta solicitada no pertenece al cliente");

            for (Long producto : productosLong) {
                Producto producto1 = em.find(Producto.class, producto);
                if (producto1 == null)
                    throw new RuntimeException("El producto no esta registrado.");
                productosX.add(producto1);
            }
            if (productosX.isEmpty())
                throw new RuntimeException("La lista de productos es vacia.");
            for (Producto producto : productosX) {
                carrito.agregarProductoAlCarrito(producto);
            }
            Tienda tienda = em.find(Tienda.class, 13L);
            Venta venta = carrito.pagar(cliente, tienda.MarcaPromocionVigente(), tienda.TarjetaPromocionVigente(), tarjeta);

            LocalDate hoy = LocalDate.now();
            TypedQuery<NumeroVenta> qnv = em.createQuery("select nv from NumeroVenta nv where nv.anio=:anio", NumeroVenta.class);
            qnv.setParameter("anio", hoy.getYear());
            qnv.setLockMode(LockModeType.PESSIMISTIC_WRITE);

            List<NumeroVenta> lista = qnv.getResultList();
            System.out.println(lista);
            NumeroVenta numeroVenta;
            if (lista.isEmpty()) {
                System.out.println("vacia");
                numeroVenta = new NumeroVenta(1, hoy.getYear());
                em.persist(numeroVenta);
            } else {
                System.out.println("llena");
                numeroVenta = lista.get(0);
                numeroVenta.numeroSiguiente();
            }
            System.out.println(numeroVenta.crearCodigo());

            venta.setNumeroVenta(numeroVenta.crearCodigo());
            tienda.agregarVenta(venta);

            JedisPool pool = new JedisPool("localhost", 6379);
            Jedis jedis = pool.getResource();
            jedis.del("user:"+ idCliente);

            jedis.close();
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw new RuntimeException(e);

        } finally {
            if (em.isOpen())
                em.close();
//            if (emf.isOpen())
//                emf.close();
        }
    }

    @Override
    public float calcularMonto(List<Long> productosLong, Long idTarjeta) {
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();
        List<Producto> productosX = new ArrayList<>();
        float montoFinal = 0;
        Cliente cliente;
        try {

            tx.begin();
            if (productosLong.isEmpty())
                throw new RuntimeException("El carrito no puede estar vacio.");
            if (idTarjeta == null)
                throw new RuntimeException("La tarjeta no debe ser vacia.");

            //Devuelve el monto total aplicando los descuentos al día de la fecha
            // validar que no llegue una lista vacía y la tarjeta exista
            Carrito carrito = new Carrito();

            Tarjeta tarjeta = em.find(Tarjeta.class, idTarjeta);
            if (tarjeta == null)
                throw new RuntimeException("La tarjeta no existe");

            for (Long producto : productosLong) {
                Producto producto1 = em.find(Producto.class, producto);
                if (producto1 == null)
                    throw new RuntimeException("El producto no esta registrado.");
                productosX.add(producto1);
            }
            if (productosX.isEmpty())
                throw new RuntimeException("La lista de productos es vacia.");
            for (Producto producto : productosX) {
                carrito.agregarProductoAlCarrito(producto);
            }

            Tienda tienda = em.find(Tienda.class, 13L);
            montoFinal = (float) carrito.calcularMontoCarrito(tienda.MarcaPromocionVigente(), tienda.TarjetaPromocionVigente(), tarjeta);


            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);

        } finally {
            if (em.isOpen())
                em.close();
//            if (emf.isOpen())
//                emf.close();
        }
        return montoFinal;
    }

    @Override
    public List<Venta> ventas() {
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();
        List<Venta> ventas;
        try {
            tx.begin();

            TypedQuery<Venta> qv = em.createQuery("select v from Venta v", Venta.class);
            ventas = qv.getResultList();
            for (Venta venta :
                    ventas) {
                venta.tocarProductoVendido();
            }
            if (ventas.isEmpty())
                throw new RuntimeException("No hay ventas registradas.");

            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw new RuntimeException(e);

        } finally {
            if (em.isOpen())
                em.close();
//            if (emf.isOpen())
//                emf.close();
        }
        return ventas;
    }

    @Override
    public List ultimasVentas(Long idCliente) {
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();
        List<Cliente> clientes = new ArrayList<>();
        List<Venta> ventas = new ArrayList<>();
        List<Venta> ventasRetorno = new ArrayList<>();
        Gson gson = new Gson();

        try {
            JedisPool pool = new JedisPool("localhost", 6379);
            Jedis jedis = pool.getResource();
            //jedis.del("user:"+ idCliente);

            String ventasJson = jedis.hget("user:" + idCliente, "ventas");

            System.out.println(ventasJson);

            if (ventasJson == null) {
//                System.out.println("Entra");
                this.tx.begin();

                TypedQuery<Venta> q = this.em.createQuery("SELECT v FROM Venta v WHERE v.cliente=" + idCliente + " ORDER BY v.id DESC ", Venta.class);
                ventas = q.getResultList();

                if (ventas.isEmpty())
                    throw new RuntimeException("No existen ventas registradas para el cliente solicitado");

                int tamanio = ventas.size();

                if (tamanio >= 3) {
                    ventasRetorno.add(ventas.get(0));
                    ventasRetorno.add(ventas.get(1));
                    ventasRetorno.add(ventas.get(2));
                } else if (tamanio == 2) {
                    ventasRetorno.add(ventas.get(0));
                    ventasRetorno.add(ventas.get(1));
                } else {
                    ventasRetorno.add(ventas.get(0));
                }

                for (Venta venta :
                        ventasRetorno) {
                    venta.tocarProductoVendido();
                }
                String json = gson.toJson(ventasRetorno);
                jedis.hset("user:" + idCliente, "ventas", json);

                this.tx.commit();

            } else {
//                System.out.println("NO ENTRA");
                Type tipo = new TypeToken<List<Venta>>() {
                }.getType();
                ventasRetorno = gson.fromJson(ventasJson, tipo);
//                System.out.println("VENTAS DEL JSON PARSEADAS A LISTA DE NUEVO: ");
//                System.out.println(ventasRetorno);
            }


        } catch (Exception e) {
            this.tx.rollback();
            throw new RuntimeException(e);

        } finally {
            if (this.em.isOpen())
                this.em.close();
//            if (this.emf.isOpen())
//                this.emf.close();
        }
        return ventasRetorno;
    }
}

