package main;

import api.*;
import modelo.*;
import servicios.*;
import web.WebApi;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Date());
        //String persistenceUnit = "objectdb:myDbFile.odb";
        String persistenceUnit = "jpamysql";

        WebApi servicio =
                new WebApi(1234,
                        new ProductoService(persistenceUnit),
                        new PromocionService(persistenceUnit),
                        new VentaService(persistenceUnit),
                        new ClienteService(persistenceUnit),
                        new CategoriaService(persistenceUnit),
                        new MarcaSevice(persistenceUnit));
        servicio.start();

//        ProductoService productoService = new ProductoService(persistenceUnit);
//        productoService.modificarProducto(11L, "2222", "Pera", 38, 5L, 7L);
//        productoService.modificarProducto(11L, "2222", "Pera", 40, 5L, 7L);

//        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit); //= Persistence.createEntityManagerFactory("objectdb:myDbFile.odb");
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        List<Producto> productos;
//
//        try {
//            tx.begin();
//
//            TypedQuery<Producto> q = em.createQuery("select p from Producto p where p.id=:id", Producto.class);
//            q.setParameter("id", 11L);
//            productos = q.getResultList();
//            if (productos.isEmpty())
//                throw new RuntimeException("El producto no esta registrado.");
//
//            Producto producto = em.find(Producto.class, 11L);
//
//            TypedQuery<Producto> qp = em.createQuery("select p from Producto p where p.codigo=:codigo", Producto.class);
//            qp.setParameter("codigo", "2222");
//            if (!qp.getResultList().isEmpty() && !producto.codigo().equals("2222")) {
//                throw new RuntimeException("Ya Existe este codigo de producto");
//            }
//
//            Marca marca = em.find(Marca.class, 7L);
//            Categoria categoria = em.find(Categoria.class, 5l);
//
//            producto.setCategoria(categoria);
//            producto.setCodigo("2222");
//            producto.setDescripcion("Pera");
//            producto.setMarca(marca);
//            producto.setPrecio(40);
//
//            long version = producto.getVersion(); // como le decis q # de version queres?
//            producto.setVersion(version);
//            em.merge(producto);
//
//            tx.commit();
//
//        } catch (OptimisticLockException e1) {
//            throw new RuntimeException("El producto se modifico por otro usuario");
//
//        } catch (Exception e) {
//            tx.rollback();
//            e.printStackTrace();
//            throw new RuntimeException(e);
//
//        } finally {
//            if (em.isOpen())
//                em.close();
////            if (emf.isOpen())
////                emf.close();
//        }


//        ClienteServicio clienteServicio = new ClienteService(persistenceUnit);
//        PromocionService promocionService = new PromocionService(persistenceUnit);
//        VentaService ventaService = new VentaService(persistenceUnit);
//        TiendaServicio tiendaService = new T
//        iendaService(persistenceUnit);
//        tiendaService.generarCodigoUnicoVenta(1, 2022);
//        tiendaService.generarCodigoUnicoVenta(1, 2023);

//        VentaServicio vs = new VentaService(persistenceUnit);
//        List<Long>productos = new ArrayList<>();
//        productos.add(10L);
//        vs.realizarVenta(1L, productos, 3L);

//        List<Venta> ventas = vs.ventas();
//        System.out.println("VENTAS: " + ventas);
//
//        try {

//            ClienteServicio sc = new ClienteService(persistenceUnit);
//
//            sc.crearCliente("Mar", "G", "12345678", "marti@gmail.com");
//            sc.crearCliente("Tony", "Stark", "87654321", "tony@g.com");
//            sc.modificarCliente(1L, "Martina", "Garcia", "987654321", "martina@gmail.com");
//            sc.agregarTarjeta(1L, "1586", "mercadopago");
//            sc.agregarTarjeta(1L, "2896", "uala");
//            System.out.println(sc.listarTarjetas(1L));


//            ProductoServicio ps = new ProductoService(persistenceUnit);
//            ps.crearCategoria("frutas");
//            ps.crearCategoria("verdura");
//
//            ps.crearMarca("Acme");
//            ps.crearMarca("Eco");
//            ps.crearMarca("Frutiloqui");
//
//            ps.crearProducto("1111", "Manzana", 34, 5L, 7L);
//            ps.crearProducto("2222", "Pera", 34, 5L, 7L);
//            ps.crearProducto("3333", "Banana", 34, 6L, 8L);

//            PromocionServicio prs = new PromocionService(persistenceUnit);
//            prs.crearTienda();

//            prs.crearDescuentoSobreTotal("mercadopago",
//                    LocalDate.now().minusDays(2), LocalDate.now().plusDays(2), (float) 0.08);
//            prs.crearDescuento("Acme",
//                    LocalDate.now().minusDays(2), LocalDate.now().plusDays(2), (float) 0.05);


//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }


    }
}
//        ClienteServicio clienteServicio = new ClienteService(persistenceUnit);
//        ProductoService productoService = new ProductoService(persistenceUnit);
//        PromocionService promocionService = new PromocionService(persistenceUnit);
//        VentaService ventaService = new VentaService(persistenceUnit);
//        WebApi servicio =
//                new WebApi(1234,
//                        new ProductoService(persistenceUnit),
//                        new PromocionService(persistenceUnit),
//                        new VentaService(persistenceUnit),
//                        new ClienteService(persistenceUnit));
//        servicio.start();
//    }
//}




        /*Cliente cliente1 = new Cliente("Martina", "Garcia", "56784252", "marti@gmai.com");
        Cliente cliente2 = new Cliente("Emilia", "Mernes", "65895341", "emi@gmail.com");

        Tarjeta tarjeta1 = new Tarjeta(1, TipoTarjeta.MP);
        Tarjeta tarjeta2 = new Tarjeta(2, TipoTarjeta.UALA);
        Tarjeta tarjeta3 = new Tarjeta(3, TipoTarjeta.MP);

        cliente1.agregarTarjeta(tarjeta1);
        cliente1.agregarTarjeta(tarjeta2);
        cliente2.agregarTarjeta(tarjeta3);

        Marca marca1 = new Marca("Eco");
        Marca marca2 = new Marca("Frutiloqui");
        Categoria categoria1 = new Categoria("frutas");
        Producto producto1 = new Producto("1", 34, "manzana", categoria1,  marca1);
        Producto producto2 = new Producto("1", 6, "pera", categoria1,  marca2);

        Carrito carrito1 = new Carrito();
        Carrito carrito2 = new Carrito();

        carrito1.agregarProductoAlCarrito(producto1);
        carrito1.agregarProductoAlCarrito(producto2);
        carrito2.agregarProductoAlCarrito(producto2);


        Tienda tienda = new Tienda();

        tienda.setTarjetaPromocion(
                new TarjetaPromocion(true,
                        LocalDate.of(2022, 8, 29), LocalDate.of(2022, 8, 30),
                        TipoTarjeta.MP));
        tienda.setMarcaPromocion(
                new MarcaPromocion(true,
                        LocalDate.of(2022, 8, 30), LocalDate.of(2022, 9, 2),
                        marca1));


        Scanner lectura = new Scanner(System.in);

        System.out.println("----------Seleccionar tarjeta: ----------");

        int indice = 0;
        for (Tarjeta tarjeta : cliente1.getTarjetas()) {
           // System.out.println(indice + "- Numero de tarjeta: " + tarjeta.getNumero() + " . Tipo tarjeta: " + tarjeta.tipoTarjeta());
            indice++;
        }
        System.out.println("Elegir tarjeta: ");
        int opcion = lectura.nextInt();
     //   System.out.println("Opcion elegida: " + opcion + " - Numero de tarjeta: " + cliente1.getTarjetas().get(opcion).getNumero());

        double precio = carrito1.calcularMontoCarrito(tienda.MarcaPromocionVigente(), tienda.TarjetaPromocionVigente(), cliente1.getTarjetas().get(opcion));
       // System.out.println("Precio calculado con promociones de marca y tarjeta: " + precio);

        tienda.agregarVenta(carrito1.pagar(cliente1, tienda.MarcaPromocionVigente(), tienda.TarjetaPromocionVigente(), cliente1.getTarjetas().get(opcion)));

        tienda.verVentasRealizadas().forEach(venta -> System.out.println(venta.toString()));
        //System.out.println(tienda.verVentasRealizadas());
        //probar agregar promocion nueva a las listas de promociones

       /* tienda.setMarcaPromocion(
                new MarcaPromocion(true,
                        LocalDate.of(2022, 8, 30), LocalDate.of(2022, 9, 1),
                        marca2)
        );

        tienda.promocionList().forEach(marcaPromocion -> System.out.println(marcaPromocion.marca() + ", " + marcaPromocion.estado()));
        */


