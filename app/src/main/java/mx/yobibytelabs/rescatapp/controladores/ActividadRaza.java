package mx.yobibytelabs.rescatapp.controladores;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mx.yobibytelabs.rescatapp.R;
import mx.yobibytelabs.rescatapp.controladores.ActividadConfirmacion;
import mx.yobibytelabs.rescatapp.objetos.ListaRazas;

public class ActividadRaza extends Activity  {
    private ArrayList<ListaRazas> animales;
    private ArrayList<ListaRazas> datos;
    private Typeface roboto;
    private String nombre,cumpleaños,talla,sexo;
    private Bitmap foto;
    private GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raza);
        TextView titulo = (TextView)findViewById(R.id.tvRaza);
        Typeface multicolore = Typeface.createFromAsset(getAssets(), "multicolore-webfont.ttf");
        titulo.setTypeface(multicolore);
        gridView = (GridView)findViewById(R.id.gridView);


        nombre = getIntent().getStringExtra("nombre");
        cumpleaños = getIntent().getStringExtra("cumpleaños");
        talla = getIntent().getStringExtra("talla");
        sexo = getIntent().getStringExtra("sexo");
        foto = getIntent().getParcelableExtra("foto");

        animales = new ArrayList<ListaRazas>();
        rellenarArrayList();
        ImageView image = new ImageView(this);
        image.setImageResource(R.drawable.dog_holder);



    }
    private void rellenarArrayList() {
        animales.add(new ListaRazas("uno",R.drawable.dog_holder));
        animales.add(new ListaRazas("dos", R.drawable.dog_holder,"tres", R.drawable.dog_holder,"cuatro", R.drawable.dog_holder));
        animales.add(new ListaRazas("cinco", R.drawable.dog_holder,"seis", R.drawable.icn_mixed,"siete", R.drawable.dog_holder));
        animales.add(new ListaRazas("ocho",R.drawable.dog_holder,"nueve",R.drawable.dog_holder));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.raza, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


