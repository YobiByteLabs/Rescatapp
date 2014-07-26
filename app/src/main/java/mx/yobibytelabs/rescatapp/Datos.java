package mx.yobibytelabs.rescatapp;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;


public class Datos extends Activity implements View.OnClickListener {
    private static final int  REQUEST_IMAGE_CAPTURE=1;
    private Button continuar;
    private ImageView foto;
    private TextView titulo;
    private EditText nombre,cumpleaños;
    private Bitmap newBitmap;
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
        foto = (ImageView)findViewById(R.id.thumbnail);
        continuar.setOnClickListener(this);
        foto.setOnClickListener(this);
        button1 = (RadioButton) findViewById(R.id.radioButton);
        button2 = (RadioButton) findViewById(R.id.radioButton2);
        titulo = (TextView)findViewById(R.id.textView2);
        //Fuentes
         Typeface typeFace = Typeface.createFromAsset(getAssets(), "multicolore-webfont.ttf");
        Typeface typeFace2 = Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf");
        titulo.setTypeface(typeFace);
        continuar.setTypeface(typeFace);
        nombre.setTypeface(typeFace2);
        cumpleaños.setTypeface(typeFace2);
        button1.setTypeface(typeFace2);
        button2.setTypeface(typeFace2);
       foto.setAdjustViewBounds(true);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap preview = (Bitmap)extras.get("data");
            newBitmap = resizeBitmap(preview);
            foto.setImageBitmap(newBitmap);
        }
    }

    private Bitmap resizeBitmap(Bitmap preview) {
        Bitmap m= null;
        Matrix mx = new Matrix();
        mx.postScale(2,2);

        if (preview.getWidth() >= preview.getHeight()){

            m = Bitmap.createBitmap(
                    preview,
                    preview.getWidth()/2 - preview.getHeight()/2,
                    0,
                    preview.getHeight(),
                    preview.getHeight()

            );

        }else{

            m = Bitmap.createBitmap(
                    preview,
                    0,
                    preview.getHeight()/2 - preview.getWidth()/2,
                    preview.getWidth(),
                    preview.getWidth()

            );
        }
        Canvas canvas = new Canvas();
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        canvas.drawBitmap(m, mx, paint);
        return m;
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.btn_continuar:
                intent = new Intent(this,Talla.class);
                intent.putExtra("nombre",nombre.getText().toString());
                intent.putExtra("cumpleaños",cumpleaños.getText().toString());
                if(button1.isChecked()) {
                    intent.putExtra("sexo","Macho");
                } else if(button2.isChecked()) {
                    intent.putExtra("sexo","Hembra");
                }
                startActivity(intent);
                break;
            case R.id.thumbnail:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(this.getPackageManager())!=null){
                    startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
                }
                break;
        }

    }
}
