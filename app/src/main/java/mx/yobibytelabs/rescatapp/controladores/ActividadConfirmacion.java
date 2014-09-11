package mx.yobibytelabs.rescatapp.controladores;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import mx.yobibytelabs.rescatapp.R;
import mx.yobibytelabs.rescatapp.objetos.Confirmacion;

public class ActividadConfirmacion extends ActionBarActivity implements View.OnClickListener {
    
    private Button confirmar;
    private TextView datos,tnombre,tcumpleaños,traza,tsexo;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_confirma_datos);
        tnombre = (TextView)findViewById(R.id.textview_nombre);
        tcumpleaños = (TextView)findViewById(R.id.textView_cumpleanos);
        traza = (TextView)findViewById(R.id.textView_raza);
        tsexo = (TextView)findViewById(R.id.textView_genero);
        datos = (TextView)findViewById(R.id.textconfirmadatos);

        confirmar = (Button)findViewById(R.id.button_confirmar);
        confirmar.setOnClickListener(this);

        imageView = (ImageView)findViewById(R.id.imageView2);
        imageView.setImageBitmap(Confirmacion.getBitmap());

        Typeface multicolore = Typeface.createFromAsset(getAssets(), "multicolore-webfont.ttf");
        Typeface roboto = Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf");
        datos.setTypeface(multicolore);
        tnombre.setText(tnombre.getText().toString()+"\n"+ Confirmacion.getNombre());
        tcumpleaños.setText(tcumpleaños.getText().toString()+"\n"+ Confirmacion.getCumpleaños());
        tsexo.setText(tsexo.getText().toString()+" "+ Confirmacion.getSexo());
        traza.setText(traza.getText().toString()+" "+Confirmacion.getRaza());

        tnombre.setTypeface(roboto);
        tsexo.setTypeface(roboto);
        traza.setTypeface(roboto);
        tcumpleaños.setTypeface(roboto);
        confirmar.setTypeface(multicolore);
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,ActividadCompartir.class);
        startActivity(intent);
    }
}
