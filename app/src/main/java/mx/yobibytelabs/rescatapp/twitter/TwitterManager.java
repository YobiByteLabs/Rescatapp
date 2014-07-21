package mx.yobibytelabs.rescatapp.twitter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by jagspage2013 on 21/07/14.
 */
public class TwitterManager {

    ProgressDialog progressDialog;
    Twitter twitter;
    RequestToken requestToken;
    SharedPreferences sharedPreferences;
    Activity activity;


    public TwitterManager(Activity act,SharedPreferences sharedPreferences){
        this.activity = act;
        this.sharedPreferences = sharedPreferences;
    }

    private ProgressDialog progressor(String title){
        ProgressDialog p= new ProgressDialog(activity);
        p.setTitle(title);
        p.setIndeterminate(true);
        p.setCancelable(false);
        p.show();
        return p;
    }

    public void login(){
        new TwitterLoginTask("").execute();
    }

    public void logout(){
        SharedPreferences.Editor e= sharedPreferences.edit();
        e.putString(TwitterConstants.PREF_KEY_OAUTH_TOKEN, null);
        e.putString(TwitterConstants.PREF_KEY_OAUTH_SECRET, null);
        e.commit();
    }

    public boolean isloggedin(){
        String access_token= sharedPreferences.getString(TwitterConstants.PREF_KEY_OAUTH_TOKEN, null);
        String access_token_secret= sharedPreferences.getString(TwitterConstants.PREF_KEY_OAUTH_SECRET, null);
        return access_token!=null && access_token_secret!=null;
    }

    public void sendtweet(String msg){
        if(msg!=null && msg.trim().length() > 0)
            new updateTwitterStatus().execute(msg);
    }
    public void logincallback(Intent i, Runnable r){
        final Runnable postloginrunnable= r;
        Uri uri= i.getData();
        if (uri != null && uri.toString().startsWith(TwitterConstants.TWITTER_CALLBACK_URL)){

            new AsyncTask<Uri, Void, String>(){
                String errmsg= null;
                String access_token= null, access_token_secret= null;
                ProgressDialog progress;

                @Override
                protected void onPreExecute(){
                    super.onPreExecute();
                    progress= progressor("Logging in...");
                }
                @Override
                protected String doInBackground(Uri... uris){
                    String verifier= uris[0].getQueryParameter(TwitterConstants.URL_TWITTER_OAUTH_VERIFIER);
                    try{
                        AccessToken accessToken= twitter.getOAuthAccessToken(requestToken, verifier);
                        // Shared Preferences
                        SharedPreferences.Editor ed= sharedPreferences.edit();
                        ed.putString(TwitterConstants.PREF_KEY_OAUTH_TOKEN, access_token= accessToken.getToken());
                        ed.putString(TwitterConstants.PREF_KEY_OAUTH_SECRET, access_token_secret= accessToken.getTokenSecret());

                        Log.e("Twitter OAuth Token", "> " + accessToken.getToken());

                        long userID= accessToken.getUserId();
                        User user= twitter.showUser(userID);
                        String username= user.getName();
                        ed.putString("twitter_name", username);
                        ed.commit();
                        Log.e("UserID: ", "userID: " + userID + "" + username);
                        Log.v("Welcome:", "Thanks:" + Html.fromHtml("<b>Welcome " + username + "</b>"));
                    }catch(Exception e){
                        errmsg= e.getMessage();
                        Log.e("Twitter Login Error", "> " +errmsg);
                    }
                    return null;
                }
                @Override
                protected void onPostExecute(String result){
                    if(errmsg!=null)
                        Toast.makeText(activity, "Twitter Login Error: "+errmsg, Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                    if(postloginrunnable != null)
                        postloginrunnable.run();
                }
                @Override
                protected void onCancelled(String result){
                    if(errmsg==null)
                        errmsg= "Cancelled";
                    onPostExecute(result);
                }
            }.execute(uri);
        }
    }

    private class TwitterLoginTask extends AsyncTask<String, Void, String> {
        ConfigurationBuilder builder= new ConfigurationBuilder();
        ProgressDialog progress;
        String errmsg= null;

        public TwitterLoginTask(String bmImage) {
            builder.setOAuthConsumerKey(TwitterConstants.CONSUMER_KEY);
            builder.setOAuthConsumerSecret(TwitterConstants.CONSUMER_SECRET);
            Configuration configuration= builder.build();

            TwitterFactory factory= new TwitterFactory(configuration);
            twitter= factory.getInstance();
        }
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progress= progressor("Login...");
        }
        protected String doInBackground(String... urls){
            try{
                requestToken= twitter.getOAuthRequestToken(TwitterConstants.TWITTER_CALLBACK_URL);
                Uri uri= Uri.parse(requestToken.getAuthenticationURL() + "&force_login=true");
                Intent intent= new Intent(activity, TwitterWebView.class)
                        .setData(uri);
                activity.startActivityForResult(intent, TwitterConstants.TWITTER_CALLBACK);
            }catch(TwitterException e){
                errmsg= e.getMessage();
                Log.e("Twitter Login Error", "> " +errmsg);
            }
            return "result";
        }
        @Override
        protected void onPostExecute(String result){
            progress.dismiss();
            if(errmsg!=null)
                Toast.makeText(activity, "Twitter Login Error: " + errmsg, Toast.LENGTH_SHORT).show();
        }
        @Override
        protected void onCancelled(String result){
            if(errmsg==null)
                errmsg= "Cancelled";
            progress.dismiss();
        }
    }


    private class updateTwitterStatus extends AsyncTask<String, String, String>{
        String errmsg= null;
        ProgressDialog progress;
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progress= progressor("Tuiteando...");
        }
        protected String doInBackground(String... args){
            Log.d("Tweet Text", "> " + args[0]);
            String status= args[0];
            try{
                ConfigurationBuilder builder= new ConfigurationBuilder();
                builder.setOAuthConsumerKey(TwitterConstants.CONSUMER_KEY);
                builder.setOAuthConsumerSecret(TwitterConstants.CONSUMER_SECRET);
                String access_token= sharedPreferences.getString(TwitterConstants.PREF_KEY_OAUTH_TOKEN, "");
                String access_token_secret= sharedPreferences.getString(TwitterConstants.PREF_KEY_OAUTH_SECRET, "");

                AccessToken accessToken= new AccessToken(access_token, access_token_secret);
                Twitter twitter= new TwitterFactory(builder.build()).getInstance(accessToken);

                // Update status
                twitter4j.Status response= twitter.updateStatus(status);
                Log.d("Status", "> " + response.getText());
            }catch(TwitterException e){
                errmsg= e.getMessage();
                Log.d("Twitter Update Error", errmsg);
            }
            return null;
        }
        @Override
        protected void onPostExecute(String file_url){
            progress.dismiss();
            if(errmsg==null)
                errmsg= "Tu tuit fue publicado";
            Toast.makeText(activity, errmsg, Toast.LENGTH_SHORT).show();
        }
        @Override
        protected void onCancelled(String result){
            if(errmsg==null)
                errmsg= "Cancelado";
            onPostExecute(result);
        }
    }


}
