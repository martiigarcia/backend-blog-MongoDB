package web;

public class CategoriaDTO {

    private long id;

    private String nombre;

    public CategoriaDTO(String nombre) {
        this.nombre = nombre;
    }

    public CategoriaDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
