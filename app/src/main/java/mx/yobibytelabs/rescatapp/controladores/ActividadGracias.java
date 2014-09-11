package mx.yobibytelabs.rescatapp.controladores;

import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

import mx.yobibytelabs.rescatapp.R;

public class ActividadGracias extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_gracias);
        TextView gracias,estamos,consejo1,consejo2,consejo3,consejo4,mantente;
        Typeface multicolore = Typeface.createFromAsset(getAssets(), "multicolore-webfont.ttf");
        Typeface roboto = Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf");
        
        gracias= (TextView)findViewById(R.id.textViewGraciasPorParticipar);
        estamos = (TextView)findViewById(R.id.textViewEstamos);
        consejo1 = (TextView)findViewById(R.id.textViewObtener);
        consejo2=(TextView)findViewById(R.id.textRecordar);
        consejo3=(TextView)findViewById(R.id.textViewPlanear);
        consejo4=(TextView)findViewById(R.id.textViewAdquirir);
        mantente=(TextView)findViewById(R.id.textMantente);

        gracias.setTypeface(multicolore);
        estamos.setTypeface(multicolore);
        consejo1.setTypeface(multicolore);
        consejo2.setTypeface(multicolore);
        consejo3.setTypeface(multicolore);
        consejo4.setTypeface(multicolore);
        mantente.setTypeface(multicolore);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actividad_gracias, menu);
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
}
