package mx.yobibytelabs.rescatapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Date;

import mx.yobibytelabs.rescatapp.twitter.TwitterConstants;
import mx.yobibytelabs.rescatapp.twitter.TwitterManager;
import mx.yobibytelabs.rescatapp.util.Constants;


public class MainActivity extends Activity implements View.OnClickListener{


    private Button btn_login;
    private Button formulario;
    private Button btn_tweet;
    private TextView lbl_user;
    private File photo;

    private static TwitterManager twitterManager;

    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(TwitterConstants.PREFERENCE_NAME , MODE_PRIVATE);

        twitterManager = new TwitterManager(this,sharedPreferences);
        formulario = (Button)findViewById(R.id.formulario);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_tweet = (Button) findViewById(R.id.btn_tweet);
        lbl_user = (TextView) findViewById(R.id.lbl_user);
        btn_login.setOnClickListener(this);
        btn_tweet.setOnClickListener(this);
        formulario.setOnClickListener(this);
        updateView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

        switch (view.getId()) {
            case R.id.btn_login: // btn que nos logea con tuiter
                if (!isTwitterConnected()){
                    twitterManager.login();
                }else{
                    twitterManager.logout();
                }
                updateView();
            break;
            case R.id.btn_tweet: //btn para enviar un tuit, este tiene solo la fecha
                String msg="dogsom.com";
                Log.i(Constants.DEBUG_TAG, msg);

                photo  = getFileFromInput(getResources().openRawResource(R.raw.prueba));
                if (isTwitterConnected()){
                    twitterManager.sendtweet(msg,photo);
                }
            break;
            case R.id.formulario:
                Intent intent = new Intent(this,Datos.class);
                startActivity(intent);
                break;
        }
    }

    private File getFileFromInput(InputStream inputStream) {
        File photo = null;
        int read = 0;
        byte[] bytes = new byte[1024];
        OutputStream outputStream =null;

        try{
            outputStream  = new FileOutputStream("/Dogsom/tmp.jpg");

            while((read = inputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,read);
            }

        }catch (IOException E){
            E.printStackTrace();
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    // outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return photo;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){ // si el resultado esperado de una actividad es OK
            switch(requestCode){ // comparamos el código de petición
                case TwitterConstants.TWITTER_CALLBACK:  // si es la petición de twitter, procesamos los permisos recibidos
                    if(data.getData() != null){
                        twitterManager.logincallback(data, new Runnable() {
                            public void run() {
                                updateView();
                                Log.i(Constants.DEBUG_TAG, "after ActivityResult ");
                                Toast.makeText(MainActivity.this, isTwitterConnected() ? "Logged In" : "Not Logged In", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else
                        Toast.makeText(this, data.getExtras().getString(TwitterConstants.TWITTER_CALLBACK_REPLY), Toast.LENGTH_SHORT).show();
                break;
                // otros case dependiendo de las peticiones...
            }
        }else if(resultCode == RESULT_CANCELED){// si el resultado es la cancelación de la actividad

            if(requestCode==TwitterConstants.TWITTER_CALLBACK) // si la petición era de twitter, pero esta se cancelo.
                Toast.makeText(MainActivity.this, isTwitterConnected()? "Logged In" : "Logged Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Verifica que la app tenga las llaves necesarias para hacer el uso de la apo
     * @return true si estamos loggeados, false si no estamos logeados.
     */
    public boolean isTwitterConnected(){
       return twitterManager.isloggedin();
    }

    /**
    * Metodo que sirve para actualizar la vista de los botones.
    * Depende si estamos o no conectados a twitter;
    * */
    public void updateView(){
        if (isTwitterConnected()){
            btn_tweet.setText("Tweet as " + sharedPreferences.getString("twitter_name", ""));
            btn_tweet.setEnabled(true);
            btn_login.setText("Log Off Twitter");
        }else{
            btn_tweet.setText("Not Logged in");
            btn_tweet.setEnabled(false);
            btn_login.setText("Log In Twitter");
        }
    }
}
