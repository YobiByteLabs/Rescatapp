package mx.yobibytelabs.rescatapp;

import android.app.Activity;
import android.content.Intent;
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

public class ConfirmaDatos extends Activity implements View.OnClickListener {
    private Button confirmar;
    private TextView datos,tnombre,tcumpleaños,traza,tsexo,Ttalla;
    private String nombre,cumpleaños,talla,raza,sexo;
    private Bitmap foto;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirma_datos);
        tnombre = (TextView)findViewById(R.id.textview_nombre);
        tcumpleaños = (TextView)findViewById(R.id.textView_cumpleanos);
        traza = (TextView)findViewById(R.id.textView_raza);
        tsexo = (TextView)findViewById(R.id.textView_genero);
        Ttalla = (TextView)findViewById(R.id.textView_talla);
        datos = (TextView)findViewById(R.id.textconfirmadatos);

        confirmar = (Button)findViewById(R.id.button_confirmar);
        confirmar.setOnClickListener(this);

        nombre = getIntent().getStringExtra("nombre");
        cumpleaños = getIntent().getStringExtra("cumpleaños");
        talla = getIntent().getStringExtra("talla");
        raza = getIntent().getStringExtra("raza");
        sexo = getIntent().getStringExtra("sexo");

        foto = (Bitmap)getIntent().getParcelableExtra("foto");
        imageView = (ImageView)findViewById(R.id.imageView2);
        imageView.setImageBitmap(foto);

        Typeface multicolore = Typeface.createFromAsset(getAssets(), "multicolore-webfont.ttf");
        Typeface roboto = Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf");
        datos.setTypeface(multicolore);
        tnombre.setText(tnombre.getText().toString()+"\n"+ nombre);
        tcumpleaños.setText(tcumpleaños.getText().toString()+"\n"+ cumpleaños);
        tsexo.setText(tsexo.getText().toString()+" "+ sexo);
        traza.setText(traza.getText().toString()+" "+raza);
        Ttalla.setText(Ttalla.getText().toString()+" "+talla);
        tnombre.setTypeface(roboto);
        tsexo.setTypeface(roboto);
        traza.setTypeface(roboto);
        Ttalla.setTypeface(roboto);
        tcumpleaños.setTypeface(roboto);
        confirmar.setTypeface(multicolore);
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
        intent.putExtra("foto",foto);
        startActivity(intent);
    }
}
