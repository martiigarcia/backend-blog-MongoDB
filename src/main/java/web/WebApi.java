package web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import api.*;
import exceptions.ClienteException;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import modelo.*;
import servicios.PromocionService;
import servicios.VentaService;

public class WebApi {


    //- Mostrará todos los productos (en un JList por ejemplo si utilizan Swing o
    //      un Select HTML de selección múltiple), permitiendo seleccionar uno o varios.
    //- Mostrará todas las tarjetas de crédito del cliente (en un Jlist o Select
    //      HTML) (iniciemos la aplicación seleccionando un cliente cualquiera pasando
    //      el ID por parámetro). Permitiendo elegir una para pagar.
    //- Permitirá revisar el monto total hasta el momento de los productos
    //      seleccionados (mediante un botón “precio total”).
    //- Permitirá realizar la compra (mediante otro botón).
    // - Mostrará los mensajes de error utilizando un JOptionPane en Swing o
    //      <span> en HTML.


    private ProductoServicio productoServicio;
    private PromocionService promocionService;
    private ClienteServicio clienteServicio;
    private VentaServicio ventaServicio;
    private int webPort;
    private CategoriaServicio categoriaServicio;
    private MarcaServicio marcaServicio;
    private List<Producto> productosList;

    public WebApi(int webPort, ProductoServicio productoServicio,
                  PromocionService promocionService, VentaService ventaServicio,
                  ClienteServicio clienteServicio, CategoriaServicio categoriaServicio, MarcaServicio marcaServicio) {
        this.webPort = webPort;
        this.productoServicio = productoServicio;
        this.promocionService = promocionService;
        this.clienteServicio = clienteServicio;
        this.ventaServicio = ventaServicio;
        this.categoriaServicio = categoriaServicio;
        this.productosList = new ArrayList<>();
        this.marcaServicio = marcaServicio;
    }

    public void start() {
        Javalin app = Javalin.create(config ->
        {
            config.enableCorsForAllOrigins();
        }).start(this.webPort);
        //posts y gets
       // app.get("/obtenerProducto", this.getProducto());
        app.get("/productos", this.listarProductos());
        app.post("/update-producto", this.updateProducto());
        app.get("/tarjetas", this.listarTarjetas());
        app.post("/calcularmonto", this.precioTotal());
        app.post("/pagar", this.comprar());
        app.get("/ventas", this.listarVentas());
        app.get("/categorias", this.listarCategorias());
        app.get("/marcas", this.listarMarcas());

        //excepciones
        app.exception(ClienteException.class, (e, ctx) -> {
            e.printStackTrace();
            ctx.json(Map.of("result", "error", "errors", e.toMap()));
            // log error in a stream...
        });

        app.exception(Exception.class, (e, ctx) -> {
            e.printStackTrace();
            ctx.json(Map.of("result", "error", "message", "Ups... algo se rompió: " + e.getMessage()));
            // log error in a stream...
        });
    }

    /*public Handler getProducto() throws RuntimeException {
        return ctx -> {

            String idProducto = ctx.queryParam("id_producto");
            System.out.println("idProducto: "+idProducto);
            if (idProducto == null)
                throw new RuntimeException("Se debe ingresar un producto.");

            Long id = Long.valueOf(idProducto);
            System.out.println("id: "+id);
            Producto producto = null;

            System.out.println("size: "+this.productosList.size());
            for (Producto productoX : this.productosList) {
                System.out.println("entra al for");
                if (id == productoX.getId()) {
                    System.out.println(id + " - " + productoX.getId());
                    producto = productoX;
                }
                //System.out.println(id + " - " + productoX.getId());
            }
            if(producto == null)
                throw new RuntimeException("No existe un producto con el id ingresado");

            ctx.json(Map.of("result", "success", "producto", producto));

        };
    }*/

    public Handler updateProducto() {
        return ctx -> {

            ProductoDTO producto = ctx.bodyAsClass(ProductoDTO.class); //agregar version

//            System.out.println(producto.getId());
//            System.out.println(producto.getCodigo());
//            System.out.println(producto.getDescripcion());
//            System.out.println(producto.getPrecio());

            this.productoServicio.modificarProducto(producto.getId(), producto.getCodigo(),
                    producto.getDescripcion(), (float) producto.getPrecio(),
                    producto.getCategoria().getId(),producto.getMarca().getId(), producto.getVersion());

            ctx.json(Map.of("result", "success"));

        };
    }

    public Handler listarProductos() {
        return ctx -> {

            this.productosList = this.productoServicio.listarProductos();
            System.out.println(productosList);
            var list = new ArrayList<Map<String, Object>>();
            for (Producto producto : productosList) {
                list.add(producto.toMap());
            }

            ctx.json(Map.of("result", "success", "productos", list));

        };
    }

    public Handler listarVentas() {
        return ctx -> {

            List<Venta> ventaList = this.ventaServicio.ventas();

            var list = new ArrayList<Map<String, Object>>();
            for (Venta venta : ventaList) {
                list.add(venta.toMap());
            }

            ctx.json(Map.of("result", "success", "ventas", list));

        };
    }

    public Handler listarCategorias() {
        return ctx -> {

            List<Categoria> categoriaList = this.categoriaServicio.listarCategorias();
            System.out.println(categoriaList);
            var list = new ArrayList<Map<String, Object>>();
            for (Categoria categoria : categoriaList) {
                list.add(categoria.toMap());
            }

            ctx.json(Map.of("result", "success", "categorias", list));

        };
    }
    public Handler listarMarcas() {
        return ctx -> {

            List<Marca> marcaList = this.marcaServicio.listarMarcas();
            System.out.println(marcaList);
            var list = new ArrayList<Map<String, Object>>();
            for (Marca marca : marcaList) {
                list.add(marca.toMap());
            }

            ctx.json(Map.of("result", "success", "marcas", list));

        };
    }

    public Handler listarTarjetas() {
        return ctx -> {

            List<Tarjeta> tarjetaList = this.clienteServicio.listarTarjetas(1L);

            var list = new ArrayList<Map<String, Object>>();
            for (Tarjeta tarjeta : tarjetaList) {
                list.add(tarjeta.toMap());
            }

            ctx.json(Map.of("result", "success", "tarjetas", list));

        };
    }

    public Handler precioTotal() throws RuntimeException {
        return ctx -> {

            Map<String, List<String>> parametros = ctx.queryParamMap();

            String prod = parametros.get("productos").get(0);
            String tarj = parametros.get("tarjeta").get(0);

            if (prod.isEmpty())
                throw new RuntimeException("No se puede calcular el monto sin productos seleccionados.");
            if (tarj.isEmpty())
                throw new RuntimeException("No se puede calcular el monto sin una tarjeta seleccionada seleccionados.");

            List<Long> productosLong = Arrays.asList(prod.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
            float monto = this.ventaServicio.calcularMonto(productosLong, Long.valueOf(tarj));

            ctx.json(Map.of("result", "success", "monto", monto));

        };
    }

    public Handler comprar() throws RuntimeException {
        return ctx -> {
            Map<String, List<String>> parametros = ctx.queryParamMap();

            String prod = parametros.get("productos").get(0);
            String tarj = parametros.get("tarjeta").get(0);


            if (prod.isEmpty())
                throw new RuntimeException("No se puede generar la compra sin productos seleccionados.");
            if (tarj.isEmpty())
                throw new RuntimeException("No se puede generar la compra sin una tarjeta seleccionada seleccionados.");

            List<Long> productosLong = Arrays.asList(prod.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
            this.ventaServicio.realizarVenta(1L, productosLong, Long.valueOf(tarj));


            ctx.json(Map.of("result", "success"));

        };
    }

}
