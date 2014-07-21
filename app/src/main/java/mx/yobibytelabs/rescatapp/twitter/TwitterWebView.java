package mx.yobibytelabs.rescatapp.twitter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import mx.yobibytelabs.rescatapp.R;
import mx.yobibytelabs.rescatapp.util.Constants;

/**
 * Created by jagspage2013 on 21/07/14.
 */
public class TwitterWebView extends Activity {

    WebView twitterWebView;
    String TAG = Constants.DEBUG_TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitterwebview);
        Uri uri = getIntent().getData();

        twitterWebView = (WebView)findViewById(R.id.webview);

        try{
            setUpWebView(uri);
        }catch (Exception e){
            e.printStackTrace();
            dofinish("Error "+ e.getMessage());
        }

    }


    private void dofinish(String msg){ // manda el resultado al momento de terminar el logeo
        setResult(RESULT_OK, new Intent().putExtra(TwitterConstants.TWITTER_CALLBACK_REPLY, msg));
        finish();
    }
    private void setUpWebView(Uri uri){
        twitterWebView.setVerticalScrollBarEnabled(false);
        twitterWebView.setHorizontalScrollBarEnabled(false);
        twitterWebView.setWebViewClient(new TwitterWebViewClient());
        WebSettings webSettings = twitterWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        twitterWebView.loadUrl(uri.toString());

    }

    private class TwitterWebViewClient extends WebViewClient {
        boolean closed= false;

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i(TAG, "OVERRIDE "+closed+" "+url);
            if(!closed && url.startsWith(TwitterConstants.TWITTER_CALLBACK_URL)){
                Intent i= new Intent();
                i.setData(Uri.parse(url));
                setResult(url.contains("?denied=")? RESULT_CANCELED : RESULT_OK, i);
                finish();
                return true;
            }
            return false;
        }
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            Log.e(TAG, "ONRECEIVEDERROR "+failingUrl + " " +description);
            view.loadData("  ", "text/plain", "utf-8");
            dofinish(description);
        }
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon){
            super.onPageStarted(view, url, favicon);
            Log.d(TAG, "PageStarted " + url);
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.d(TAG, "PageFinished " + url);

        }
    }
}
