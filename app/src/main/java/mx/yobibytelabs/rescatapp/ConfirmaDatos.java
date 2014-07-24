package mx.yobibytelabs.rescatapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmaDatos extends Activity implements View.OnClickListener {
    private Button confirmar;
    private TextView datos;
    private String nombre,cumpleaños,talla,raza,sexo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirma_datos);
        datos = (TextView)findViewById(R.id.textconfirmadatos);
        confirmar = (Button)findViewById(R.id.btn_continuar);
        confirmar.setOnClickListener(this);
        nombre = getIntent().getStringExtra("nombre");
        cumpleaños = getIntent().getStringExtra("cumpleaños");
        talla = getIntent().getStringExtra("talla");
        raza = getIntent().getStringExtra("raza");
        sexo = getIntent().getStringExtra("sexo");
        datos.setText(nombre+" " + sexo+" "+cumpleaños+" "+talla+" "+raza);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.confirma_datos, menu);
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
        Intent intent = new Intent(this,Genial.class);
        intent.putExtra("nombre",nombre);
        startActivity(intent);
    }
}
