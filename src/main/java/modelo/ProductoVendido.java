package modelo;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class ProductoVendido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String codigo;
    private double precioPago;
    private String descripcion;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }


    public ProductoVendido(String codigo, double precio, String descripcion) {
        this.codigo = codigo;
        this.precioPago = precio;
        this.descripcion = descripcion;
    }

    public ProductoVendido() {

    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioPago() {
        return precioPago;
    }

    public void setPrecioPago(double precioPago) {
        this.precioPago = precioPago;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String codigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return "Producto vendido: " + descripcion + "{ " +
                "codigo=" + codigo +
                ", precio=" + precioPago +
                '}';
    }

}