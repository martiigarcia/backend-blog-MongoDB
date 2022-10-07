package api;

import java.time.LocalDate;

public interface PromocionServicio {
    // validar que las fechas no se superpongan
    void crearDescuentoSobreTotal(String marcaTarjeta, LocalDate fechaDesde,
                                  LocalDate fechaHasta, float porcentaje);

    // validar que las fechas no se superpongan
    void crearDescuento(String marcaProducto, LocalDate fechaDesde, LocalDate
            fechaHasta, float porcentaje);



}
