package api;

import modelo.Categoria;

import java.util.List;

public interface CategoriaServicio {
    List<Categoria> listarCategorias();
    void crearCategoria(String nombre);
}
