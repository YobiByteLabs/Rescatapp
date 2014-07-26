package mx.yobibytelabs.rescatapp;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Genial extends Activity implements View.OnClickListener {
    private  String nombre;
    private TextView genial,parte;
    private Button boton_continuar,boton_agregar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genial);
        Typeface multicolore = Typeface.createFromAsset(getAssets(), "multicolore-webfont.ttf");
        Typeface roboto = Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf");

        genial =(TextView)findViewById(R.id.tvgenial);
        genial.setTypeface(multicolore);

        parte =(TextView)findViewById(R.id.tvparte_de_dogsom);
        parte.setTypeface(multicolore);

        boton_continuar = (Button)findViewById(R.id.button_continuar);
        boton_continuar.setTypeface(multicolore);
        boton_continuar.setOnClickListener(this);

        boton_agregar = (Button)findViewById(R.id.button_agregar_otra);
        boton_agregar.setTypeface(multicolore);
        boton_agregar.setOnClickListener(this);
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
            case (R.id.button_agregar_otra):
                break;
        }
    }
}
