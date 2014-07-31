package mx.yobibytelabs.rescatapp.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mx.yobibytelabs.rescatapp.R;
import mx.yobibytelabs.rescatapp.objetos.Animal;

public class AnimalesAdapter extends ArrayAdapter<Animal> {
    private Context context;
    private ArrayList<Animal> datos;
    private Typeface roboto;

    /**
     * Constructor del Adapter.
     *
     * @param context context de la Activity que hace uso del Adapter.
     * @param datos   data que se desean visualizar en el ListView.
     */
    public AnimalesAdapter(Context context, ArrayList<Animal> datos) {
        super(context, R.layout.listview_item, datos);
        // Guardamos los par�metros en variables de clase.
        this.context = context;
        this.datos = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // En primer lugar "inflamos" una nueva vista, que ser� la que se
        // mostrar� en la celda del ListView.
        View item = LayoutInflater.from(context).inflate(
                R.layout.listview_item, null);

        // A partir de la vista, recogeremos los controles que contiene para
        // poder manipularlos.
        // Recogemos el ImageView y le asignamos una foto.
        ImageView imagen = (ImageView) item.findViewById(R.id.imgAnimal);
        imagen.setImageResource(datos.get(position).getDrawableImageID());

        // Recogemos el TextView para mostrar el nombre y establecemos el
        // nombre.
        TextView nombre = (TextView) item.findViewById(R.id.tvContent);
        nombre.setText(datos.get(position).getNombre());
        roboto = Typeface.createFromAsset(context.getAssets(), "Roboto-Regular.ttf");
        nombre.setTypeface(roboto);
        // Devolvemos la vista para que se muestre en el ListView.
        return item;
    }

}