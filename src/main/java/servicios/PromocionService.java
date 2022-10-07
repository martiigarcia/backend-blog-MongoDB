package servicios;

import api.PromocionServicio;
import modelo.*;

import javax.persistence.*;
import java.time.LocalDate;

public class PromocionService implements PromocionServicio {
    private EntityManagerFactory emf; //= Persistence.createEntityManagerFactory("objectdb:myDbFile.odb");
    private EntityManager em;//= emf.createEntityManager();
    private EntityTransaction tx;//= em.getTransaction();

    public PromocionService(String servicio) {
        this.emf = Persistence.createEntityManagerFactory(servicio);

    }
    @Override
    public void crearDescuentoSobreTotal(String marcaTarjeta, LocalDate fechaDesde, LocalDate fechaHasta,
            float porcentaje) {
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();

        try {
            tx.begin();
            // validar que las fechas no se superpongan

            Tienda tienda = em.find(Tienda.class, 13L);
            Promocion tarjetaPromocion = new TarjetaPromocion(true,
                    fechaDesde, fechaHasta, (double) porcentaje, marcaTarjeta.toUpperCase());
         //   System.out.println(tarjetaPromocion);
          //  em.persist(tarjetaPromocion);

            tienda.setTarjetaPromocion(tarjetaPromocion);
            tienda.setTarjetaPromocion(new TarjetaPromocion(true,
                    fechaDesde, fechaHasta, (double) porcentaje, marcaTarjeta.toUpperCase()));

            tx.commit();

        } catch (Exception e) {
            System.out.println("FALLAAAAAAAAAAAAAAAAAA: "+ e.getMessage());
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
    public void crearDescuento(String marcaProducto, LocalDate fechaDesde, LocalDate fechaHasta, float porcentaje) {
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();
        Marca marca;
        try {
            tx.begin();
            // validar que las fechas no se superpongan

            TypedQuery<Marca> qm = em.createQuery("select m from Marca m where m.nombre=:nombre", Marca.class);
            qm.setParameter("nombre", marcaProducto);
            marca = qm.getSingleResult();
            if (marca == null)
                throw new RuntimeException("La marca ingresada no existe.");

            Tienda tienda = em.find(Tienda.class, 13L);
            tienda.setMarcaPromocion(new MarcaPromocion(true, fechaDesde, fechaHasta, (double) porcentaje, marca.getNombre()));

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
