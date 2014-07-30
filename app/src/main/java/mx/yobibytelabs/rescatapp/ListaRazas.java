package mx.yobibytelabs.rescatapp;

public class ListaRazas {
    private String nombre="";
    private String nombre2="";
    private String nombre3="";
    private int drawableImageID=0;
    private int drawableImageID2=0;
    private int drawableImageID3=0;




    public ListaRazas(String nombre, int drawableImageID,String nombre2, int drawableImageID2,String nombre3, int drawableImageID3) {
        this.nombre = nombre;
        this.drawableImageID = drawableImageID;
        this.nombre2 = nombre2;
        this.drawableImageID2 = drawableImageID2;
        this.nombre3 = nombre3;
        this.drawableImageID3 = drawableImageID3;
    }
    public ListaRazas(String nombre, int drawableImageID,String nombre2, int drawableImageID2) {
        this.nombre = nombre;
        this.drawableImageID = drawableImageID;
        this.nombre2 = nombre2;
        this.drawableImageID2 = drawableImageID2;
    }

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

    public void setDrawableImageID(int drawableImageID) {
        this.drawableImageID = drawableImageID;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }
    public int getDrawableImageID2() {
        return drawableImageID2;
    }

    public void setDrawableImageID2(int drawableImageID2) {
        this.drawableImageID2 = drawableImageID2;
    }
    public String getNombre3() {
        return nombre3;
    }

    public void setNombre3(String nombre3) {
        this.nombre3 = nombre3;
    }
    public int getDrawableImageID3() {
        return drawableImageID3;
    }

    public void setDrawableImageID3(int drawableImageID3) {
        this.drawableImageID3 = drawableImageID3;
    }

}
