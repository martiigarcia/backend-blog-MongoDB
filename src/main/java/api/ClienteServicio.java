package api;

import modelo.Tarjeta;

import java.util.List;

public interface ClienteServicio {

    // validar que el dni no se repita
    void crearCliente(String nombre, String apellido, String dni, String email);
    // validar que sea un cliente existente
    void modificarCliente(Long idCliente, String nombre, String apellido, String dni, String email);
    // validar que sea un cliente existente
    void agregarTarjeta(Long idCliente, String nro, String marca);

    //Devuelve las tarjetas de un cliente específico
    List listarTarjetas(Long idCliente);



}
