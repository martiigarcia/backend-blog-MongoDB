package web;

import modelo.Categoria;
import modelo.Marca;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

public class ProductoDTO {

    private String codigo;
    private String descripcion;
    private CategoriaDTO categoria;
    private double precio;
    private MarcaDTO marca;
    private long id;

    private long version;

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public MarcaDTO getMarca() {
        return marca;
    }

    public void setMarca(MarcaDTO marca) {
        this.marca = marca;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}