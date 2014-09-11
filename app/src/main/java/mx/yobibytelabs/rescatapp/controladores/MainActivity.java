package mx.yobibytelabs.rescatapp.controladores;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import mx.yobibytelabs.rescatapp.R;
import mx.yobibytelabs.rescatapp.fragments.MainFragment;
import mx.yobibytelabs.rescatapp.twitter.TwitterConstants;
import mx.yobibytelabs.rescatapp.twitter.TwitterManager;
import mx.yobibytelabs.rescatapp.util.Constants;


public class MainActivity extends ActionBarActivity {


    private MainFragment mainFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        if(Constants.isFbLogIn(this))
            iniciarDatos();

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


    public void iniciarDatos(){
       Intent intent = new Intent(getBaseContext(),ActividadDatos.class);
       intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
       startActivity(intent);
    }

}
