package api;

import modelo.Marca;

import java.util.List;

public interface MarcaServicio {

    void crearMarca(String nombre);

    List<Marca> listarMarcas();

}
