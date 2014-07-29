package mx.yobibytelabs.rescatapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Raza extends Activity  {
    private ArrayList<ListaRazas> animales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raza);
        TextView titulo = (TextView)findViewById(R.id.tvRaza);
        Typeface multicolore = Typeface.createFromAsset(getAssets(), "multicolore-webfont.ttf");
        titulo.setTypeface(multicolore);
        animales = new ArrayList<ListaRazas>();

        rellenarArrayList();

        RazaAdapter adapter = new RazaAdapter(this, animales);

        ListView lvAnimales = (ListView) findViewById(R.id.lvraza);
        // Asignamos el Adapter al ListView, en este punto hacemos que el
        // ListView muestre los datos que queremos.
        lvAnimales.setAdapter(adapter);
        // Asignamos el Listener al ListView para cuando pulsamos sobre uno de
        // sus items.
        lvAnimales.setItemsCanFocus(true);

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

    class RazaAdapter extends ArrayAdapter<ListaRazas>  {
        private Context context;
        private ArrayList<ListaRazas> datos;
        private Typeface roboto;
        private String nombre,cumpleaños,talla,sexo;
        private Bitmap foto;
        /**
         * Constructor del Adapter.
         *
         * @param context
         *            context de la Activity que hace uso del Adapter.
         * @param datos
         *            Datos que se desean visualizar en el ListView.
         */
        public RazaAdapter(Context context, ArrayList<ListaRazas> datos) {
            super(context, R.layout.item_raza, datos);
            // Guardamos los par�metros en variables de clase.
            this.context = context;
            this.datos = datos;

        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Intent intent = new Intent(context,ConfirmaDatos.class);
            nombre = getIntent().getStringExtra("nombre");
            cumpleaños = getIntent().getStringExtra("cumpleaños");
            talla = getIntent().getStringExtra("talla");
            sexo = getIntent().getStringExtra("sexo");
            foto = getIntent().getParcelableExtra("foto");


            // En primer lugar "inflamos" una nueva vista, que ser� la que se
            // mostrar� en la celda del ListView.

            View item = LayoutInflater.from(context).inflate(
                    R.layout.item_raza, null);
            roboto = Typeface.createFromAsset(context.getAssets(), "Roboto-Regular.ttf");
            // A partir de la vista, recogeremos los controles que contiene para
            // poder manipularlos.
            // Recogemos el ImageView y le asignamos una foto.
            ImageView imagen = (ImageView) item.findViewById(R.id.imageView);
            imagen.setImageResource(datos.get(position).getDrawableImageID());

            imagen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,ConfirmaDatos.class);
                    intent.putExtra("nombre",nombre);
                    intent.putExtra("cumpleaños",cumpleaños);
                    intent.putExtra("talla",talla);
                    intent.putExtra("sexo",sexo);
                    intent.putExtra("foto",foto);
                    intent.putExtra("raza", datos.get(position).getNombre());
                    startActivity(intent);

                }
            });

            ImageView imagen2 = (ImageView) item.findViewById(R.id.imageView2);
            imagen2.setImageResource(datos.get(position).getDrawableImageID2());
            imagen2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,ConfirmaDatos.class);
                    intent.putExtra("nombre",nombre);
                    intent.putExtra("cumpleaños",cumpleaños);
                    intent.putExtra("talla",talla);
                    intent.putExtra("sexo",sexo);
                    intent.putExtra("foto",foto);
                    intent.putExtra("raza",animales.get(position).getNombre2());
                    startActivity(intent);
                }
            });

            ImageView imagen3 = (ImageView) item.findViewById(R.id.imageView3);
            imagen3.setImageResource(datos.get(position).getDrawableImageID3());
            imagen3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,ConfirmaDatos.class);
                    intent.putExtra("nombre",nombre);
                    intent.putExtra("cumpleaños",cumpleaños);
                    intent.putExtra("talla",talla);
                    intent.putExtra("sexo",sexo);
                    intent.putExtra("foto",foto);
                    intent.putExtra("raza",animales.get(position).getNombre3());
                    startActivity(intent);
                }
            });
            // Recogemos el TextView para mostrar el nombre y establecemos el
            // nombre.
            TextView tv1 = (TextView) item.findViewById(R.id.textView);
            tv1.setText(datos.get(position).getNombre());
            tv1.setTypeface(roboto);

            TextView tv2 = (TextView) item.findViewById(R.id.textView2);
            tv2.setText(datos.get(position).getNombre2());
            tv2.setTypeface(roboto);

            TextView tv3 = (TextView) item.findViewById(R.id.textView3);
            tv3.setText(datos.get(position).getNombre3());
            tv3.setTypeface(roboto);

            // Devolvemos la vista para que se muestre en el ListView.
            return item;
        }



    }}


