package mx.yobibytelabs.rescatapp.controladores;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import mx.yobibytelabs.rescatapp.R;
import mx.yobibytelabs.rescatapp.objetos.Confirmacion;

public class ActividadCompartir extends ActionBarActivity implements View.OnClickListener {
    private  String nombre;
    private TextView genial,parte , imagenNombre;
    private Button boton_continuar;
    private ImageView imagenPerro;
    private Bitmap foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genial);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Typeface multicolore = Typeface.createFromAsset(getAssets(), "multicolore-webfont.ttf");
        Typeface roboto = Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf");

        imagenNombre = (TextView)findViewById(R.id.image_nombre);
        genial =(TextView)findViewById(R.id.tvgenial);
        parte =(TextView)findViewById(R.id.tvparte_de_dogsom);

        genial.setTypeface(multicolore);
        imagenNombre.setTypeface(multicolore);
        parte.setTypeface(multicolore);

        imagenNombre.setText(Confirmacion.getNombre());

        imagenPerro = (ImageView)findViewById(R.id.imagenPerro);
        imagenPerro.setImageBitmap(Confirmacion.getBitmap());

        boton_continuar = (Button)findViewById(R.id.button_continuar);
        boton_continuar.setTypeface(multicolore);
        boton_continuar.setOnClickListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.genial, menu);
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
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.button_continuar):
                break;
        }
    }
}
