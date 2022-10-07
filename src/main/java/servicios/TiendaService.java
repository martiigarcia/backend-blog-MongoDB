package servicios;

import api.TiendaServicio;
import modelo.Marca;
import modelo.NumeroVenta;
import modelo.Tienda;

import javax.persistence.*;
import java.time.LocalDate;

public class TiendaService implements TiendaServicio {

    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;

    public TiendaService(String servicio) {
        this.emf = Persistence.createEntityManagerFactory(servicio);

    }

    @Override
    public void crearTienda() {
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();

        try {
            tx.begin();
            Tienda tienda = new Tienda();
            em.persist(tienda);

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
    public void generarCodigoUnicoVenta(int numero, int anio) {
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();

        try {
            tx.begin();

            NumeroVenta numeroVenta = new NumeroVenta(numero, anio);
            em.persist(numeroVenta);

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

}
