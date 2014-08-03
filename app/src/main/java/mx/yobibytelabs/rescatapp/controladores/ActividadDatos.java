package mx.yobibytelabs.rescatapp.controladores;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
        setContentView(
                R.layout.activity_datos);

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
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap preview = (Bitmap) extras.get("data");
            newBitmap = BitmapManager.getCircleBitmap(preview);
            foto.setImageBitmap(newBitmap);
        } else if ((requestCode == SELECT_PHOTO && resultCode == RESULT_OK)) {
            Uri selectedImage = data.getData();
            //InputStream imageStream = null;
            try {
                // imageStream = getContentResolver().openInputStream(selectedImage);
                Bitmap yourSelectedImage = BitmapManager.decodeUri(this, selectedImage);
                if (yourSelectedImage.getHeight() < yourSelectedImage.getWidth()) {
                    newBitmap = BitmapManager.getCircleBitmap(yourSelectedImage);
                    newBitmap = BitmapManager.rotateBitmap(newBitmap, 90);
                } else {
                    newBitmap = BitmapManager.getCircleBitmap(yourSelectedImage);
                }
                foto.setImageBitmap(newBitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_continuar:
                if (!nombre.getText().toString().equals("") && !cumpleaños.getText().toString().equals("")
                        && (button1.isChecked() || button2.isChecked()) && newBitmap != null) {
                    intent = new Intent(this, ActividadTalla.class);
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
                                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(intent, 2);
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                break;
        }

    }
}
