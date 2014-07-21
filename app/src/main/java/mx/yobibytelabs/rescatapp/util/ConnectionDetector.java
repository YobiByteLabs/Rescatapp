package mx.yobibytelabs.rescatapp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by jagspage2013 on 21/07/14.
 */
public class ConnectionDetector {

    private Context _context;

    public ConnectionDetector(Context _context){
        this._context = _context;
    }

    public boolean isConnectedToInternet(){
        ConnectivityManager connectivityManager = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager !=null){
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if(networkInfo!=null){
                for(int i = 0; i<networkInfo.length;i++){
                    if(networkInfo[i].getState() == NetworkInfo.State.CONNECTED ){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
