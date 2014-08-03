package mx.yobibytelabs.rescatapp.objetos;

public class ListaRazas {
    private String nombre = "";
    private int drawableImageID = 0;


    public ListaRazas(String nombre, int drawableImageID) {
        this.nombre = nombre;
        this.drawableImageID = drawableImageID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDrawableImageID() {
        return drawableImageID;
    }



}
