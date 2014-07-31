package mx.yobibytelabs.rescatapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.LoginButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import mx.yobibytelabs.rescatapp.Datos;
import mx.yobibytelabs.rescatapp.R;
import mx.yobibytelabs.rescatapp.twitter.TwitterConstants;
import mx.yobibytelabs.rescatapp.twitter.TwitterManager;


public class MainFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "MainFragment";

    private Button btn_login;
    private Button formulario;
    private Button btn_tweet;
    private TextView lbl_user;

    private UiLifecycleHelper uiHelper;


    public interface Interfaz_Twitter{
        public boolean isLoggedIn();
        public void logIn();
        public void logOut();
        public void sendTweet();
        public SharedPreferences getSharedPreferences();
    }
    Interfaz_Twitter interfaz;
    public MainFragment() {}

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        interfaz = (Interfaz_Twitter)activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
        authButton.setFragment(this);
        authButton.setReadPermissions(Arrays.asList("email", "public_profile"));
        formulario = (Button)view.findViewById(R.id.formulario);
        btn_login = (Button) view.findViewById(R.id.btn_login);
        btn_tweet = (Button) view.findViewById(R.id.btn_tweet);
        lbl_user = (TextView) view.findViewById(R.id.lbl_user);
        btn_login.setOnClickListener(this);
        btn_tweet.setOnClickListener(this);
        formulario.setOnClickListener(this);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHelper = new UiLifecycleHelper(getActivity(), callback);
        uiHelper.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        updateView();
    }

    @Override
    public void onResume() {
        super.onResume();
        Session session = Session.getActiveSession();
        if (session != null &&
                (session.isOpened() || session.isClosed()) ) {
            onSessionStateChange(session, session.getState(), null);
        }
        uiHelper.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }

    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };

    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            Log.i(TAG, "Logged in...");
        } else if (state.isClosed()) {
            Log.i(TAG, "Logged out...");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);

    }

    public void updateView(){
        if (interfaz.isLoggedIn()){
            btn_tweet.setText("Tweet as " + interfaz.getSharedPreferences().getString("twitter_name", ""));
            btn_tweet.setEnabled(true);
            btn_login.setText("Log Off Twitter");
        }else{
            btn_tweet.setText("Not Logged in");
            btn_tweet.setEnabled(false);
            btn_login.setText("Log In Twitter");
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_login: // btn que nos logea con tuiter
                if (!interfaz.isLoggedIn()){
                    interfaz.logIn();
                }else{
                    interfaz.logOut();
                }
                updateView();
                break;
           /* case R.id.btn_tweet: //btn para enviar un tuit, este tiene solo la fecha
                Log.i(Constants.DEBUG_TAG,"el mensaje se envia ");
                if (interfaz.isLoggedIn()){
                    interfaz.sendTweet();
                }
                break;*/
            case R.id.formulario:
                Intent intent = new Intent(getActivity(),Datos.class);
                startActivity(intent);
                break;
        }
    }

}
