package mx.yobibytelabs.rescatapp.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jagspage2013 on 21/07/14.
 */
public class Constants {

    public static final String DEBUG_TAG = "Rescatapp";

    public static boolean isFbLogIn(Context context){
        SharedPreferences prefs = context.getSharedPreferences("Dogsom_LogIn", Context.MODE_PRIVATE);
        return prefs.getBoolean("fb",false);
    }

    public static void setFbLogIn(Context context,boolean valor){
        SharedPreferences prefs = context.getSharedPreferences("Dogsom_LogIn", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putBoolean("fb",valor);
        edit.apply();
    }
}
