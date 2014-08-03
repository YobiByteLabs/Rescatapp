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

public class ActividadRaza extends ActionBarActivity {
    private ArrayList<ListaRazas> animales;
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
        titulo.setText("¿Qué Raza es "+ Confirmacion.getNombre());
        gridView = (GridView)findViewById(R.id.gridview);
        animales = new ArrayList<ListaRazas>();
        rellenarArrayList();
        gridView.setAdapter( new RazaAdapter(this,animales));
        gridView.setNumColumns(3);

    }
    private void rellenarArrayList() {
        animales.add(new ListaRazas("uno",R.drawable.dog_holder));
        animales.add(new ListaRazas("uno",R.drawable.dog_holder));
        animales.add(new ListaRazas("uno",R.drawable.dog_holder));
        animales.add(new ListaRazas("uno",R.drawable.dog_holder));
        animales.add(new ListaRazas("uno",R.drawable.dog_holder));
        animales.add(new ListaRazas("uno",R.drawable.dog_holder));
        animales.add(new ListaRazas("uno",R.drawable.dog_holder));
        animales.add(new ListaRazas("uno",R.drawable.dog_holder));
        animales.add(new ListaRazas("uno",R.drawable.dog_holder));
        animales.add(new ListaRazas("uno",R.drawable.dog_holder));
        animales.add(new ListaRazas("uno",R.drawable.dog_holder));

    }

}


