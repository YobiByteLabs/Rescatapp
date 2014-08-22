package mx.yobibytelabs.rescatapp.objetos;

/**
 * Created by jagspage2013 on 03/08/14.
 */
public class Perro {

    String nombre;
    int id,imagen,talla;

    public Perro(String nombre, int id, int imagen,int talla){
        this.id = id;
        this.nombre = nombre;
        this.talla = talla;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int talla) {
        this.talla = talla;
    }
}
