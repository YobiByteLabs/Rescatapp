package mx.yobibytelabs.rescatapp.controladores;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mx.yobibytelabs.rescatapp.R;
import mx.yobibytelabs.rescatapp.adapters.RazaAdapter;
import mx.yobibytelabs.rescatapp.controladores.ActividadConfirmacion;
import mx.yobibytelabs.rescatapp.objetos.Confirmacion;
import mx.yobibytelabs.rescatapp.objetos.ListaRazas;
import mx.yobibytelabs.rescatapp.objetos.Perro;
import mx.yobibytelabs.rescatapp.util.Rellenador;

public class ActividadRaza extends ActionBarActivity {
    private ArrayList<Perro> animales;
    private Typeface roboto;

    private GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raza);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView titulo = (TextView)findViewById(R.id.tvRaza);
        Typeface multicolore = Typeface.createFromAsset(getAssets(), "multicolore-webfont.ttf");
        titulo.setTypeface(multicolore);
        titulo.setText("¿Qué Raza es "+ Confirmacion.getNombre()+"?");
        gridView = (GridView)findViewById(R.id.gridview);
        animales = new ArrayList<Perro>();

        rellenarLista(animales,Confirmacion.getTalla());
        gridView.setAdapter( new RazaAdapter(this,animales));
        gridView.setNumColumns(3);

    }

    private void rellenarLista(ArrayList<Perro> animales, int talla) {

        switch (talla){
            case 1:
                Rellenador.rellenarChicos(animales);
                break;
            case 2:
                Rellenador.rellenarMedianos(animales);

                break;
            case 3:
                Rellenador.rellenarGrandes(animales);
                break;
            case 4:
                Rellenador.rellenarGigantes(animales);
                break;
            default:

                break;

        }

    }


}


