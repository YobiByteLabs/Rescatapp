package mx.yobibytelabs.rescatapp.objetos;

public class ListaRazas {
    private String nombre = "";
    private int drawableImageID = 0;
    private int id;
    private int talla;


    public ListaRazas(int id, String nombre, int drawableImageID, int talla) {
        this.nombre = nombre;
        this.drawableImageID = drawableImageID;
        this.id = id;
        this.talla = talla;

    }
    public void setDrawableImageID(int drawableImageID) {
        this.drawableImageID = drawableImageID;
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
