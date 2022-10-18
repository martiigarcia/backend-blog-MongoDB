package modelo;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Venta {
    /* *

    Agregar en la Venta, un número único irrepetible que la identifique. El número debe formarse de la forma N-AÑO,
    donde N es un número entero y AÑO representa al año en el cual se realizó la venta. Cada nuevo año, N inicia de 1.

    * */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private double montoAbonado;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Cliente cliente;
    @Enumerated(EnumType.ORDINAL)
    private EstadoVenta estadoVenta;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Tarjeta tarjeta;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_venta")
    private List<ProductoVendido> productosVendidos;

    private String numeroVenta;

    public Venta(Cliente cliente, Tarjeta tarjeta, EstadoVenta estadoVenta, List<Producto> productosVendidos, double montoAbonado) {
        this.cliente = cliente;
        this.tarjeta = tarjeta;
        this.estadoVenta = estadoVenta;
        this.productosVendidos = new ArrayList<>();
        this.agregarProductos(productosVendidos);
        this.montoAbonado = montoAbonado;
    }
    public Venta(Cliente cliente, Tarjeta tarjeta, EstadoVenta estadoVenta, List<Producto> productosVendidos, double montoAbonado, String numeroVenta) {
        this(cliente, tarjeta, estadoVenta, productosVendidos, montoAbonado);
        this.numeroVenta = numeroVenta;
    }

    protected Venta() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public EstadoVenta getEstadoVenta() {
        return estadoVenta;
    }

    public void setEstadoVenta(EstadoVenta estadoVenta) {
        this.estadoVenta = estadoVenta;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    public List<ProductoVendido> getProductosVendidos() {
        return productosVendidos;
    }

    public void setProductosVendidos(List<ProductoVendido> productosVendidos) {
        this.productosVendidos = productosVendidos;
    }

    public double getMontoAbonado() {
        return montoAbonado;
    }

    public void setMontoAbonado(double montoAbonado) {
        this.montoAbonado = montoAbonado;
    }

    public String getNumeroVenta() {
        return numeroVenta;
    }

    public void setNumeroVenta(String numeroVenta) {
        this.numeroVenta = numeroVenta;
    }



    private void agregarProductos(List<Producto> productos) {
        productos.forEach(producto ->
                this.productosVendidos.add(
                        new ProductoVendido(producto.codigo(), producto.precio(), producto.descripcion()
                        )));

    }

    /*
       @Override
        public String toString() {
            return "Venta{" +
                    "cliente=" + cliente +
                    ", estadoVenta=" + estadoVenta +
                    ", tarjeta=" + tarjeta +
                    ", productosVendidos=" + productosVendidos +
                    ", montoAbonado=" + montoAbonado +
                    '}';
        }*/
    public void tocarProductoVendido() {
        this.productosVendidos.size();
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<String, Object>(
                Map.of("id", id, "numeroVenta", numeroVenta, "estado", estadoVenta, "montoAbonado", montoAbonado,
                        "cliente", cliente, "tarjeta", tarjeta, "productosVendidos", productosVendidos));

        return map;
    }
    @Override
    public String toString() {
        return "Venta id: "+id+" {" +
                "cliente=" + cliente.getNombre() +
                ", productosVendidos=" + productosVendidos +
                ", montoAbonado=" + montoAbonado +
                '}';
    }
}
