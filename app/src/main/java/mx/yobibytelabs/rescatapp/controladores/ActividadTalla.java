package mx.yobibytelabs.rescatapp.controladores;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mx.yobibytelabs.rescatapp.R;
import mx.yobibytelabs.rescatapp.adapters.AnimalesAdapter;
import mx.yobibytelabs.rescatapp.objetos.Animal;
import mx.yobibytelabs.rescatapp.objetos.Confirmacion;

public class ActividadTalla extends ActionBarActivity implements OnItemClickListener {
    private ArrayList<Animal> animales;
    private TextView titulo;
    private ListView lvAnimales;
    private AnimalesAdapter adapter;
    private String nombre,cumpleaños,sexo;
    Bitmap foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talla);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Inicializamos las variables.
        animales = new ArrayList<Animal>();
        rellenarArrayList();
        Typeface multicolore = Typeface.createFromAsset(getAssets(), "multicolore-webfont.ttf");
        Typeface roboto = Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf");
        titulo=(TextView)findViewById(R.id.textView);
        titulo.setText("¿Qué talla es "+ Confirmacion.getNombre()+"?");
        titulo.setTypeface(multicolore);
        adapter = new AnimalesAdapter(this, animales);
        lvAnimales = (ListView) findViewById(R.id.lvItems);
        // Asignamos el Adapter al ListView, en este punto hacemos que el
        // ListView muestre los datos que queremos.
        lvAnimales.setAdapter(adapter);
        // Asignamos el Listener al ListView para cuando pulsamos sobre uno de
        // sus items.
        lvAnimales.setOnItemClickListener(this);
        Toast.makeText(this, nombre + " " + cumpleaños, Toast.LENGTH_SHORT).show();
    }



    /**
     * Método que rellena el ArrayList con los animales que queremos mostrar en
     * el ListView.
     */
    private void rellenarArrayList() {

        animales.add(new Animal("Pequeño","hasta 25cm", R.drawable.talla_chico));
        animales.add(new Animal("Mediano","de 27cm hasta 50cm", R.drawable.talla_mediano));
        animales.add(new Animal("Grande","de 53cm hasta  70cm", R.drawable.talla_grande));
        animales.add(new Animal("¡Gigante!","más de 76cm", R.drawable.talla_gigante));
    }

    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position,
                            long ID) {
        Intent intent = new Intent(this,ActividadRaza.class);
        Confirmacion.setTalla(animales.get(position).getNombre());
        startActivity(intent);
    }
}
