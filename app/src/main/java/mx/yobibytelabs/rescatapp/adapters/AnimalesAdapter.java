package mx.yobibytelabs.rescatapp.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import mx.yobibytelabs.rescatapp.R;
import mx.yobibytelabs.rescatapp.objetos.Animal;

public class AnimalesAdapter extends ArrayAdapter<Animal> {
    private Context context;
    private ArrayList<Animal> datos;
    private Typeface roboto;


    public static class ViewHolder{
        public ImageView imgAnimal;
        public TextView tvContent;
        public TextView tvtitle;
    }


    /**
     * Constructor del Adapter.
     *
     * @param context context de la Activity que hace uso del Adapter.
     * @param datos   data que se desean visualizar en el ListView.
     */

    public AnimalesAdapter(Context context, ArrayList<Animal> datos) {
        super(context, R.layout.item_talla, datos);
        // Guardamos los par�metros en variables de clase.
        this.context = context;
        this.datos = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // En primer lugar "inflamos" una nueva vista, que ser� la que se
        // mostrar� en la celda del ListView.
        View vi = convertView ;

        ViewHolder viewHolder;
        if(vi ==null) {
            vi = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.item_talla, null);
            viewHolder = new ViewHolder();
            // A partir de la vista, recogeremos los controles que contiene para
            // poder manipularlos.
            // Recogemos el ImageView y le asignamos una foto.
            viewHolder.imgAnimal = (ImageView) vi.findViewById(R.id.imgAnimal);
            viewHolder.imgAnimal.setImageResource(datos.get(position).getDrawableImageID());

            // Recogemos el TextView para mostrar el nombre y establecemos el
            // nombre.
            viewHolder.tvContent = (TextView) vi.findViewById(R.id.tvContent);
            viewHolder.tvtitle = (TextView)vi.findViewById(R.id.tvTitle);

            viewHolder.tvtitle.setText(datos.get(position).getTitulo());
            viewHolder.tvContent.setText(datos.get(position).getNombre());

            roboto = Typeface.createFromAsset(context.getAssets(), "Roboto-Regular.ttf");
            viewHolder.tvContent.setTypeface(roboto);
            viewHolder.tvtitle.setTypeface(roboto);

        }else{
            viewHolder = (ViewHolder)vi.getTag();
        }
        return vi;
    }

}
