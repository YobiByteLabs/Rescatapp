package mx.yobibytelabs.rescatapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


public class Datos extends Activity implements View.OnClickListener {
    private Button continuar;
    private TextView titulo;
    private EditText nombre,cumpleaños;
    private RadioButton button1 = null;
    private RadioButton button2 = null;
    private String sexo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(
                R.layout.activity_datos);
        nombre = (EditText)findViewById(R.id.input_nombre);
        cumpleaños = (EditText)findViewById(R.id.input_cumpleanos);
        continuar = (Button)findViewById(R.id.btn_continuar);
        continuar.setOnClickListener(this);
        button1 = (RadioButton) findViewById(R.id.radioButton);
        button2 = (RadioButton) findViewById(R.id.radioButton2);
        titulo = (TextView)findViewById(R.id.textView2);
    //  Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Multicolore.otf");
     //   titulo.setTypeface(typeFace);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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
        Intent intent = new Intent(this,Talla.class);
        intent.putExtra("nombre",nombre.getText().toString());
        intent.putExtra("cumpleaños",cumpleaños.getText().toString());
        if(button1.isChecked()) {
            intent.putExtra("sexo","Macho");
        } else if(button2.isChecked()) {
            intent.putExtra("sexo","Hembra");
        }
        startActivity(intent);
    }
}
