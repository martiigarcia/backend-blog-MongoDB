package servicios;

import api.CategoriaServicio;
import api.ClienteServicio;
import modelo.Categoria;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaService implements CategoriaServicio {

    private EntityManagerFactory emf; //= Persistence.createEntityManagerFactory("objectdb:myDbFile.odb");
    private EntityManager em;//= emf.createEntityManager();
    private EntityTransaction tx;//= em.getTransaction();
    private String servicio;

    public CategoriaService(String servicio) {
        this.servicio = servicio;
        this.emf = Persistence.createEntityManagerFactory(servicio);

    }

    @Override
    public void crearCategoria(String nombre) {
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();

        try {
            tx.begin();

            Categoria categoria = new Categoria(nombre);
            em.persist(categoria);

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
    public List listarCategorias() {
        if(emf.isOpen()){
            System.out.println("13");
        }
        System.out.println("14");
        this.em = emf.createEntityManager();
        System.out.println("15");
        this.tx = em.getTransaction();
        System.out.println("16");

        List<Categoria> categorias = new ArrayList<>();
        System.out.println("17");
        try {
            tx.begin();
            System.out.println("18");
            TypedQuery<Categoria> qp = em.createQuery("select c from Categoria c", Categoria.class);
            System.out.println("19");
            categorias = qp.getResultList();
            System.out.println("20");
            if (categorias.isEmpty())
                throw new RuntimeException("No hay categorias registradas.");
            System.out.println("21");
            tx.commit();
            System.out.println("22");
        } catch (Exception e) {
            System.out.println("23");
            e.printStackTrace();
            tx.rollback();
            throw new RuntimeException(e);

        } finally {
            if (em.isOpen())
                em.close();
//            if (emf.isOpen())
//                emf.close();
        }
        return categorias;
    }

}
