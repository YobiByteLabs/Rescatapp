package mx.yobibytelabs.rescatapp.objetos;

import android.graphics.Bitmap;

/**
 * Created by jagspage2013 on 31/07/14.
 */
public  class Confirmacion {

    public static Bitmap getBitmap() {
        return bitmap;
    }

    public static void setBitmap(Bitmap bitmap) {
        Confirmacion.bitmap = bitmap;
    }

    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String nombre) {
        Confirmacion.nombre = nombre;
    }

    public static String getCumpleaños() {
        return cumpleaños;
    }

    public static void setCumpleaños(String cumpleaños) {
        Confirmacion.cumpleaños = cumpleaños;
    }

    public static String getSexo() {
        return sexo;
    }

    public static void setSexo(String sexo) {
        Confirmacion.sexo = sexo;
    }

    public static String getRaza() {
        return raza;
    }

    public static void setRaza(String raza) {
        Confirmacion.raza = raza;
    }

    private static Bitmap bitmap;
    private static String nombre;
    private static String cumpleaños;
    private static String sexo;
    private static String raza;



}
