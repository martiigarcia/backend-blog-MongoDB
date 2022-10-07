package api;

import modelo.Categoria;
import modelo.Producto;

import java.util.List;

public interface ProductoServicio {
    //validar que sea una categoría existente y que codigo no se repita
    void crearProducto(String codigo, String descripcion, float precio, Long IdCategoría, Long IdMarca);

    //validar que sea un producto existente
    void modificarProducto(Long idProducto, String codigo, String descripcion, float precio, Long IdCategoria, Long IdMarca, Long version);
    //Devuelve todos los productos
    List<Producto> listarProductos();




}
