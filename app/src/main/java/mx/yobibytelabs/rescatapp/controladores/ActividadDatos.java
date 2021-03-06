package mx.yobibytelabs.rescatapp.controladores;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import mx.yobibytelabs.rescatapp.R;
import mx.yobibytelabs.rescatapp.objetos.Confirmacion;
import mx.yobibytelabs.rescatapp.util.BitmapManager;
import mx.yobibytelabs.rescatapp.util.Constants;


public class ActividadDatos extends ActionBarActivity implements View.OnClickListener {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int SELECT_PHOTO = 2;
    private Button continuar;
    private ImageView foto;
    private TextView titulo;
    private EditText nombre, cumpleaños;
    private static Bitmap newBitmap;
    private RadioButton button1 = null;
    private RadioButton button2 = null;
    private String sexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_datos);
   /*
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setIcon(R.drawable.selector_boton_back);
        */
        nombre = (EditText) findViewById(R.id.input_nombre);
        cumpleaños = (EditText) findViewById(R.id.input_cumpleanos);
        continuar = (Button) findViewById(R.id.btn_continuar);
        foto = (ImageView) findViewById(R.id.thumbnail);
        continuar.setOnClickListener(this);
        foto.setOnClickListener(this);
        button1 = (RadioButton) findViewById(R.id.radioButton);
        button2 = (RadioButton) findViewById(R.id.radioButton2);
        titulo = (TextView) findViewById(R.id.textView2);
        //Fuentes
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "multicolore-webfont.ttf");
        Typeface typeFace2 = Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf");
        titulo.setTypeface(typeFace);
        continuar.setTypeface(typeFace);
        nombre.setTypeface(typeFace2);
        cumpleaños.setTypeface(typeFace2);
        cumpleaños.setOnClickListener(this);
        cumpleaños.setFocusable(false);
        button1.setTypeface(typeFace2);
        button2.setTypeface(typeFace2);
        foto.setAdjustViewBounds(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(Constants.DEBUG_TAG,"RequestCode : "+ requestCode + " resultCode: "+ resultCode);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap preview = (Bitmap) extras.get("data");
            newBitmap = BitmapManager.getCircleBitmap(preview);
            foto.setImageBitmap(newBitmap);
        } else if ((requestCode == SELECT_PHOTO && resultCode == RESULT_OK)) {
            Uri selectedImage = data.getData();
            int orientacion = getOrientation(this,selectedImage);
            //InputStream imageStream = null;
            try {
                // imageStream = getContentResolver().openInputStream(selectedImage);
                Bitmap yourSelectedImage = BitmapManager.decodeUri(this, selectedImage);
                if (orientacion==90) {
                    newBitmap = BitmapManager.getCircleBitmap(yourSelectedImage);
                    newBitmap = BitmapManager.rotateBitmap(newBitmap, 90);
                } else if (orientacion==180){
                    newBitmap = BitmapManager.getCircleBitmap(yourSelectedImage);
                    newBitmap = BitmapManager.rotateBitmap(newBitmap, 180);
                }
                else if (orientacion==270){
                    newBitmap = BitmapManager.getCircleBitmap(yourSelectedImage);
                    newBitmap = BitmapManager.rotateBitmap(newBitmap, 2700);
                }
                else {
                    newBitmap = BitmapManager.getCircleBitmap(yourSelectedImage);
                }
                foto.setImageBitmap(newBitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener(){

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            Intent intent;
            Log.d(Constants.DEBUG_TAG, "la posición es " + i);
            switch (i) {
                case 0:
                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                    break;
                case 1:
                    intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                    break;
            }
        }
    };

    public static int getOrientation(Context context, Uri photoUri) {
        Cursor cursor = context.getContentResolver().query(photoUri,
                new String[] { MediaStore.Images.ImageColumns.ORIENTATION },
                null, null, null);

        try {
            if (cursor.moveToFirst()) {
                return cursor.getInt(0);
            } else {
                return -1;
            }
        } finally {
            cursor.close();
        }
    }
    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_continuar:
                if (!nombre.getText().toString().equals("") && !cumpleaños.getText().toString().equals("")
                        && (button1.isChecked() || button2.isChecked()) && newBitmap != null) {
                    intent = new Intent(this, ActividadRaza.class);
                    Confirmacion.setNombre(nombre.getText().toString());
                    Confirmacion.setCumpleaños(cumpleaños.getText().toString());
                    Confirmacion.setBitmap(newBitmap);

                    if (button1.isChecked()) {
                        Confirmacion.setSexo("Macho");

                    } else if (button2.isChecked()) {
                        Confirmacion.setSexo("Hembra");

                    }
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Completa el formulario", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.input_cumpleanos:
                final Calendar myCalendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String myFormat = "MM/dd/yy"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                        cumpleaños.setText(sdf.format(myCalendar.getTime()));
                    }
                };
                new DatePickerDialog(this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)
                ).show();
                break;
            case R.id.thumbnail:
                new AlertDialog.Builder(this)
                        .setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"Cámara", "Galería"}),
                             listener
                        )
                        .show();
                break;
        }

    }
}
