package servicios;

import api.MarcaServicio;
import modelo.Categoria;
import modelo.Marca;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class MarcaSevice implements MarcaServicio {

    private EntityManagerFactory emf; //= Persistence.createEntityManagerFactory("objectdb:myDbFile.odb");
    private EntityManager em;//= emf.createEntityManager();
    private EntityTransaction tx;//= em.getTransaction();


    public MarcaSevice(String servicio) {
        this.emf = Persistence.createEntityManagerFactory(servicio);
    }

    @Override
    public void crearMarca(String nombre) {
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();

        try {
            tx.begin();

            Marca marca = new Marca(nombre);
            em.persist(marca);

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
    public List<Marca> listarMarcas() {


        this.em = emf.createEntityManager();

        this.tx = em.getTransaction();


        List<Marca> marcas = new ArrayList<>();

        try {
            tx.begin();

            TypedQuery<Marca> qp = em.createQuery("select m from Marca m", Marca.class);

            marcas = qp.getResultList();

            if (marcas.isEmpty())
                throw new RuntimeException("No hay categorias registradas.");

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
        return marcas;
    }

}
