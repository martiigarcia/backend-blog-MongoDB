package servicios;

import api.ProductoServicio;
import modelo.Categoria;
import modelo.Marca;
import modelo.Producto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoService implements ProductoServicio {

    private EntityManagerFactory emf; //= Persistence.createEntityManagerFactory("objectdb:myDbFile.odb");
    private EntityManager em;//= emf.createEntityManager();
    private EntityTransaction tx;//= em.getTransaction();


    public ProductoService(String servicio) {

        this.emf = Persistence.createEntityManagerFactory(servicio);

    }

    @Override
    public void crearProducto(String codigo, String descripcion, float precio, Long IdCategoria, Long IdMarca) {
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();
        List<Producto> productos;
        Marca marca;
        Categoria categoria;
        try {
            tx.begin();
            TypedQuery<Marca> q = em.createQuery("select m from Marca m where m.id =: id", Marca.class);
            q.setParameter("id", IdMarca);
            marca = q.getSingleResult();
            if (marca == null)
                throw new RuntimeException("La marca no esta registrado.");

            TypedQuery<Categoria> qc = em.createQuery("select ca from Categoria ca where ca.id=: id", Categoria.class);
            qc.setParameter("id", IdCategoria);
            categoria = qc.getSingleResult();
            if (categoria == null)
                throw new RuntimeException("La categoria no esta registrado.");

            TypedQuery<Producto> qp = em.createQuery("select p from Producto p where p.codigo=:codigo", Producto.class);
            qp.setParameter("codigo", codigo);
            productos = qp.getResultList();
            if (!productos.isEmpty())
                throw new RuntimeException("El codigo de producto ingresado ya esta registrado.");
            Producto producto = new Producto(codigo, precio, descripcion, categoria, marca);
            em.persist(producto);

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            throw new RuntimeException(e);

        } finally {
            if (em.isOpen())
                em.close();
//            if (emf.isOpen())
//                emf.close();
        }
    }

    @Override
    public void modificarProducto(Long idProducto, String codigo, String descripcion, float precio, Long IdCategoria,
                                  Long IdMarca, Long version) {
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();
        List<Producto> productos;

        try {
            tx.begin();

            TypedQuery<Producto> q = em.createQuery("select p from Producto p where p.id=:id", Producto.class);
            q.setParameter("id", idProducto);
            productos = q.getResultList();
            if (productos.isEmpty())
                throw new RuntimeException("El producto no esta registrado.");

            Marca marca = em.find(Marca.class, IdMarca);
            Categoria categoria = em.find(Categoria.class, IdCategoria);

            Producto producto = new Producto(idProducto, codigo, precio, descripcion, categoria, marca);

            producto.setVersion(version);

            em.merge(producto);
            tx.commit();
        } catch (OptimisticLockException e) {
            throw new RuntimeException("El producto se modifico por otro usuario");

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            throw new RuntimeException(e);

        } finally {
            if (em.isOpen())
                em.close();
//            if (emf.isOpen())
//                emf.close();
        }
    }

    @Override
    public List listarProductos() {
        if (emf.isOpen()) {
            System.out.println("1");

        }
        System.out.println("2");
        this.em = emf.createEntityManager();
        System.out.println("3");
        this.tx = em.getTransaction();
        System.out.println("4");

        List<Producto> productos = new ArrayList<>();
        System.out.println("5");
        try {
            tx.begin();
            System.out.println("6");
            TypedQuery<Producto> qp = em.createQuery("select p from Producto p", Producto.class);
            System.out.println("7");
            productos = qp.getResultList();
            System.out.println("8");
            System.out.println(productos);
            System.out.println("9");
            if (productos.isEmpty())
                throw new RuntimeException("No hay productos registrados.");
            System.out.println("10");
            tx.commit();
            System.out.println("11");
        } catch (Exception e) {
            System.out.println("12");
            e.printStackTrace();
            tx.rollback();
            throw new RuntimeException(e);

        } finally {
            if (em.isOpen())
                em.close();
//            if (emf.isOpen())
//                emf.close();
        }
        return productos;
    }


}
