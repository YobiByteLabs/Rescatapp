package mx.yobibytelabs.rescatapp.util;

import java.util.ArrayList;

import mx.yobibytelabs.rescatapp.R;
import mx.yobibytelabs.rescatapp.objetos.Perro;

/**
 * Created by jagspage2013 on 12/08/14.
 */
public class Rellenador {


    public static void rellenarGigantes(ArrayList<Perro> lista){

        lista.add(new Perro("Raza Mixta",386,R.drawable.icn_mixed));
        lista.add(new Perro("Borzoi",386,R.drawable.gigante_borzoi));
        lista.add(new Perro("Deerhound",387,R.drawable.gigante_deerhound));
        //lista.add(new Perro("Gigante de los Pirineos",388,R.drawable.gigante_gigante_de_los_pirineos));
        lista.add(new Perro("Gran Danés",389,R.drawable.gigante_gran_danes));
        lista.add(new Perro("Irish Wolfhound",390,R.drawable.gigante_irish_wolfhound));
        //lista.add(new Perro("Komondor",391,R.drawable.gigante_komondor));
        lista.add(new Perro("Kuvasz",392,R.drawable.gigante_kuvasz));
        lista.add(new Perro("Landseer",393,R.drawable.gigante_landseer));
        lista.add(new Perro("Leonberger",394,R.drawable.gigante_leonberger));
        //lista.add(new Perro("Lobero de Saarlos",395,R.drawable.gigante_lobero_de_saarlos));
        lista.add(new Perro("Mastín Inglés",396,R.drawable.gigante_mastin_ingles));
        lista.add(new Perro("Mastín Tibetano",397,R.drawable.gigante_mastin_tibetano));
        //lista.add(new Perro("Pastor de Anatolia",398,R.drawable.gigante_pastor_de_anatolia));

    }
}
