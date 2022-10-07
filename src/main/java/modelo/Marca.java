package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nombre;

    public Marca (String nombre){
        this.nombre = nombre;
    }

    public Marca() {

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

    public Map<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<String, Object>(
                Map.of("id", id, "nombre", nombre));

        return map;
    }

    @Override
    public String toString() {
        return "Marca:" + nombre + "id: "+ id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marca marca = (Marca) o;
        return Objects.equals(nombre, marca.nombre);
    }

}