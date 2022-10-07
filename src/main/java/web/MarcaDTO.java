package web;

public class MarcaDTO {

    private long id;
    private String nombre;

    public MarcaDTO (String nombre){
        this.nombre = nombre;
    }

    public MarcaDTO() {

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