package mx.yobibytelabs.rescatapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Raza extends Activity implements AdapterView.OnItemClickListener {
    private ArrayList<Animal> animales;
    private ListView lvAnimales;
    private AnimalesAdapter adapter;
    private String nombre,cumpleaños,talla,sexo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raza);
        nombre = getIntent().getStringExtra("nombre");
        cumpleaños = getIntent().getStringExtra("cumpleaños");
        talla = getIntent().getStringExtra("talla");
        sexo = getIntent().getStringExtra("sexo");
        animales = new ArrayList<Animal>();

        rellenarArrayList();

        adapter = new AnimalesAdapter(this, animales);

        lvAnimales = (ListView) findViewById(R.id.lvraza);
        // Asignamos el Adapter al ListView, en este punto hacemos que el
        // ListView muestre los datos que queremos.
        lvAnimales.setAdapter(adapter);
        // Asignamos el Listener al ListView para cuando pulsamos sobre uno de
        // sus items.
        Toast.makeText(this,nombre+" "+cumpleaños+" "+talla,Toast.LENGTH_SHORT).show();
        lvAnimales.setOnItemClickListener(this);
    }
    private void rellenarArrayList() {
        animales.add(new Animal("Border Terrier", R.drawable.perro));
        animales.add(new Animal("Bolognese", R.drawable.perro));
        animales.add(new Animal("Pug", R.drawable.perro));
        animales.add(new Animal("Bull terrier mini", R.drawable.perro));
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this,ConfirmaDatos.class);
        intent.putExtra("nombre",nombre);
        intent.putExtra("cumpleaños",cumpleaños);
        intent.putExtra("talla",talla);
        intent.putExtra("sexo",sexo);
        intent.putExtra("raza",animales.get(i).getNombre());
        startActivity(intent);
    }
}
