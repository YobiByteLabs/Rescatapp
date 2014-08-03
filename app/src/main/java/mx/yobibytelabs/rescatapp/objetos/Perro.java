package mx.yobibytelabs.rescatapp.objetos;

/**
 * Created by jagspage2013 on 03/08/14.
 */
public class Perro {

    String id,nombre,talla;

    public Perro(String id,String nombre,String talla){
        this.id = id;
        this.nombre = nombre;
        this.talla = talla;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }
}
