package mx.yobibytelabs.rescatapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mx.yobibytelabs.rescatapp.R;
import mx.yobibytelabs.rescatapp.controladores.ActividadConfirmacion;
import mx.yobibytelabs.rescatapp.objetos.Confirmacion;
import mx.yobibytelabs.rescatapp.objetos.ListaRazas;


public class RazaAdapter extends ArrayAdapter<ListaRazas>{


    Context context;
    List<ListaRazas> datos;
        /**
         * Constructor del Adapter.
         * @param context context de la Activity que hace uso del Adapter.
         * @param datos que se desean visualizar en el ListView.
         */
        public RazaAdapter(Context context, ArrayList<ListaRazas> datos) {
            super(context, R.layout.item_raza, datos);
            // Guardamos los par�metros en variables de clase.
            this.context = context;
            this.datos = datos;

        }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Typeface roboto = Typeface.createFromAsset(context.getAssets(), "Roboto-Regular.ttf");
        View item = LayoutInflater.from(context).inflate(R.layout.item_raza, null);


        ImageView imagen = (ImageView) item.findViewById(R.id.img_raza_adapter);
        imagen.setImageResource(datos.get(position).getDrawableImageID());

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irAConfirmacion(position);
            }
        });

        // Recogemos el TextView para mostrar el nombre y establecemos el
        // nombre.
        TextView tv1 = (TextView) item.findViewById(R.id.textView);
        tv1.setText(datos.get(position).getNombre());
        tv1.setTypeface(roboto);


        return item;
    }



    private void irAConfirmacion(int position){
        Confirmacion.setRaza(datos.get(position).getNombre3());
        Intent intent = new Intent(context,ActividadConfirmacion.class);
        context.startActivity(intent);
    }

}
