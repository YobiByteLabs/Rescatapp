package mx.yobibytelabs.rescatapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
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
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class Datos extends Activity implements View.OnClickListener {
    private static final int  REQUEST_IMAGE_CAPTURE=1;
    private static final int SELECT_PHOTO = 2;
    private Button continuar;
    private ImageView foto;
    private TextView titulo;
    private EditText nombre,cumpleaños;
    private static Bitmap newBitmap;
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
        if(requestCode == REQUEST_IMAGE_CAPTURE&& resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap preview = (Bitmap)extras.get("data");
            newBitmap = getCircleBitmap(preview);
            foto.setImageBitmap(newBitmap);
        }else if((requestCode == SELECT_PHOTO && resultCode == RESULT_OK)){
            Uri selectedImage = data.getData();
            InputStream imageStream = null;
            try {
                imageStream = getContentResolver().openInputStream(selectedImage);
                Bitmap yourSelectedImage = decodeUri(selectedImage);
                if (yourSelectedImage.getHeight()<yourSelectedImage.getWidth()){
                    newBitmap = getCircleBitmap(yourSelectedImage);
                   // newBitmap = rotateBitmap(newBitmap,90);
                }else{
                    newBitmap = getCircleBitmap(yourSelectedImage);
                }
                foto.setImageBitmap(newBitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        }
    public static Bitmap rotateBitmap(final Bitmap source, int mRotation){
        int targetWidth=source.getWidth();
        int targetHeight=source.getHeight();
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth, targetHeight, source.getConfig());
        Canvas canvas = new Canvas(targetBitmap);
        Matrix matrix = new Matrix();
        matrix.setRotate(mRotation,source.getWidth()/2,source.getHeight()/2);
        canvas.drawBitmap(source, matrix, new Paint());
        return  targetBitmap;
    }
    private Bitmap decodeUri(Uri selectedImage) throws FileNotFoundException {

        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 140;

        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE
                    || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);

    }



    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.btn_continuar:
                if(!nombre.getText().toString().equals("")&&!cumpleaños.getText().toString().equals("")
                        &&(button1.isChecked() || button2.isChecked()) && newBitmap!=null){
                intent = new Intent(this,Talla.class);
                intent.putExtra("nombre",nombre.getText().toString());
                intent.putExtra("cumpleaños",cumpleaños.getText().toString());
                intent.putExtra("Bitmap",newBitmap);
                if(button1.isChecked()) {
                    intent.putExtra("sexo","Macho");
                } else if(button2.isChecked()) {
                    intent.putExtra("sexo","Hembra");
                }
                startActivity(intent);
                }else{
                    Toast.makeText(this,"Completa el formulario",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.thumbnail:
                new AlertDialog.Builder(this).setTitle("Selecciona Foto")
                        .setMessage("Seleccciona Opción")
                        .setPositiveButton("Camara", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                            }
                        })
                        .setNegativeButton("Galería", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(intent, 2);
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                break;
        }

    }
    private Bitmap getCircleBitmap(Bitmap bitmap) {
        Bitmap output;
        if(bitmap.getWidth()< bitmap.getHeight()){
            output = Bitmap.createBitmap(bitmap.getWidth(),
                    bitmap.getWidth(), Bitmap.Config.ARGB_8888);
        }else{
            output = Bitmap.createBitmap(bitmap.getHeight(),
                    bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        }

        final Canvas canvas = new Canvas(output);
        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final Rect rect2 = new Rect(0, 0, bitmap.getHeight(), bitmap.getWidth());


        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        //canvas.drawOval(rectF, paint);
        if(bitmap.getWidth()< bitmap.getHeight()) {
            canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getWidth() / 2, bitmap.getWidth() / 2, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect2, rect2, paint);
        }else{
            canvas.drawCircle(bitmap.getHeight() / 2, bitmap.getHeight() / 2, bitmap.getHeight() / 2, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect2, rect2, paint);

        }


        bitmap.recycle();

        return output;
    }
}
