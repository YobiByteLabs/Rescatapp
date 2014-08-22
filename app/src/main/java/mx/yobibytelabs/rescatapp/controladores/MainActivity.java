package mx.yobibytelabs.rescatapp.controladores;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import mx.yobibytelabs.rescatapp.R;
import mx.yobibytelabs.rescatapp.fragments.MainFragment;
import mx.yobibytelabs.rescatapp.twitter.TwitterConstants;
import mx.yobibytelabs.rescatapp.twitter.TwitterManager;
import mx.yobibytelabs.rescatapp.util.Constants;


public class MainActivity extends ActionBarActivity implements MainFragment.Interfaz_Twitter{


    private MainFragment mainFragment;
    private static TwitterManager twitterManager;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        sharedPreferences = getSharedPreferences(TwitterConstants.PREFERENCE_NAME , MODE_PRIVATE);
        twitterManager = new TwitterManager(this,sharedPreferences);

        if (savedInstanceState == null) {
            // Add the fragment on initial activity setup
            mainFragment = new MainFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content,mainFragment)
                    .commit();
        } else {
            // Or set the fragment from restored state info
            mainFragment = (MainFragment) getSupportFragmentManager()
                    .findFragmentById(android.R.id.content);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){ // si el resultado esperado de una actividad es OK
            switch(requestCode){ // comparamos el código de petición
                /*case TwitterConstants.TWITTER_CALLBACK:  // si es la petición de twitter, procesamos los permisos recibidos
                    if(data.getData() != null){
                        twitterManager.logincallback(data, new Runnable() {
                            public void run() {
                                mainFragment.updateView();
                                Log.i(Constants.DEBUG_TAG, "after ActivityResult ");
                                Toast.makeText(getBaseContext(), isTwitterConnected() ? "Logged In" : "Not Logged In", Toast.LENGTH_SHORT).show();
                                if(isTwitterConnected()){
                                    iniciarDatos();
                                }
                            }
                        });
                    }else
                        Toast.makeText(this, data.getExtras().getString(TwitterConstants.TWITTER_CALLBACK_REPLY), Toast.LENGTH_SHORT).show();
                    break;*/
                // otros case dependiendo de las peticiones...
            }
        }else if(resultCode == Activity.RESULT_CANCELED){// si el resultado es la cancelación de la actividad

            if(requestCode==TwitterConstants.TWITTER_CALLBACK) // si la petición era de twitter, pero esta se cancelo.
                Toast.makeText(this, isTwitterConnected() ? "Logged In" : "Logged Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isTwitterConnected(){
        return twitterManager.isloggedin();
    }

    @Override
    public boolean isLoggedIn() {
        return isTwitterConnected();
    }

    @Override
    public void logIn() {
        twitterManager.login();
    }

    @Override
    public void logOut() {
        twitterManager.logout();
    }


    @Override
    public void sendTweet() {
        twitterManager.sendtweet("Pronto podrás probar una nueva experiencia en dogsom.com", R.raw.prueba);
    }

    @Override
    public SharedPreferences getSharedPreferences(){
        return sharedPreferences;
    }

    @Override
    public void iniciarDatos(){
       Intent intent = new Intent(getBaseContext(),ActividadDatos.class);
       intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
       startActivity(intent);
    }

    public void iniciar(View view){
        iniciarDatos();
    }
}
