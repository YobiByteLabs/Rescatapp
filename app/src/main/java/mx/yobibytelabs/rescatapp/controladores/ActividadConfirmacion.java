package mx.yobibytelabs.rescatapp.controladores;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import mx.yobibytelabs.rescatapp.R;
import mx.yobibytelabs.rescatapp.objetos.Confirmacion;
import mx.yobibytelabs.rescatapp.util.Constants;

public class ActividadConfirmacion extends ActionBarActivity implements View.OnClickListener {

    private Button confirmar;
    private TextView tnombre,tcumpleaños,traza;
    private RadioButton tsexo;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_confirma_datos);
        tnombre = (TextView)findViewById(R.id.textview_nombre);
        tcumpleaños = (TextView)findViewById(R.id.textView_cumpleanos);
        traza = (TextView)findViewById(R.id.textView_raza);
        tsexo = (RadioButton)findViewById(R.id.radio_sexo);

        imageView = (ImageView)findViewById(R.id.imageView2);

        confirmar = (Button)findViewById(R.id.button_confirmar);
        confirmar.setOnClickListener(this);
        imageView.setImageBitmap(Confirmacion.getBitmap());


        Typeface multicolore = Typeface.createFromAsset(getAssets(), "multicolore-webfont.ttf");
        Typeface roboto = Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf");
        tnombre.setText(Confirmacion.getNombre());
        tcumpleaños.setText(Confirmacion.getCumpleaños());
        tsexo.setText(Confirmacion.getSexo());
        traza.setText(Confirmacion.getRaza());

        tnombre.setTypeface(roboto);
        tsexo.setTypeface(roboto);
        traza.setTypeface(roboto);
        tcumpleaños.setTypeface(roboto);
        confirmar.setTypeface(multicolore);
    }

    private void procesarBitmapContorno(Bitmap bitMap) {

        bitMap = bitMap.copy(bitMap.getConfig(), true);     //lets bmp to be mutable
        Canvas canvas = new Canvas(bitMap);                 //draw a canvas in defined bmp
        Paint paint = new Paint();                          //define paint and paint color
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(0.5f);
        paint.setAntiAlias(true);                           //smooth edges

        imageView.setBackgroundResource(R.drawable.background_edits);
        canvas.drawCircle(bitMap.getWidth()/2, bitMap.getHeight()/2, (bitMap.getHeight()/2) +5, paint);
        //invalidate to update bitmap in imageview
        //imageView.setImageBitmap(Confirmacion.getBitmap());

        imageView.invalidate();
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,ActividadCompartir.class);
        startActivity(intent);
    }
}
