package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class NumeroVenta {
      /* *

    Agregar en la Venta, un número único irrepetible que la identifique. El número debe formarse de la forma N-AÑO,
    donde N es un número entero y AÑO representa al año en el cual se realizó la venta. Cada nuevo año, N inicia de 1.

    * */

    @Id
    @GeneratedValue
    private long id;
    private int numeroActual;
    private int anio;


    protected NumeroVenta() {
    }

    public NumeroVenta(int numero, int anio) {
        this.anio = anio;
        this.numeroActual = numero;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumeroActual() {
        return numeroActual;
    }

    public void setNumeroActual(int numActual) {
        this.numeroActual = numActual;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String crearCodigo() { //N-AÑO
        return (this.numeroActual + "-" + this.anio);
    }

    public String getNumeroSiguiente() {
        LocalDate fechaActual = LocalDate.now();
        if (fechaActual.getYear() != this.anio)
            this.numeroActual = 0;

         this.numeroActual += 1;
         return crearCodigo();
    }

    public int numeroSiguiente(){
        this.numeroActual += 1;
        return this.numeroActual;
    }

}
