package mx.yobibytelabs.rescatapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mx.yobibytelabs.rescatapp.R;
import mx.yobibytelabs.rescatapp.controladores.ActividadConfirmacion;
import mx.yobibytelabs.rescatapp.objetos.Confirmacion;
import mx.yobibytelabs.rescatapp.objetos.ListaRazas;
import mx.yobibytelabs.rescatapp.objetos.Perro;


public class RazaAdapter extends BaseAdapter{


    Context context;
    List<Perro> datos;

    public RazaAdapter(Context context, ArrayList<Perro> datos) {
        // Guardamos los par�metros en variables de clase.
        this.context = context;
        this.datos = datos;
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewHolder{
       public ImageView img_raza_adapter;
       public TextView lbl_raza_adapter;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder viewHolder;
        if(vi ==null) {
            vi = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.item_raza, null);
            viewHolder = new ViewHolder();

        }else{
            viewHolder = (ViewHolder)vi.getTag();
        }

        viewHolder.img_raza_adapter = (ImageView) vi.findViewById(R.id.img_raza_adapter);
        viewHolder.lbl_raza_adapter = (TextView) vi.findViewById(R.id.lbl_raza_adapter);

        Typeface roboto = Typeface.createFromAsset(context.getAssets(), "Roboto-Regular.ttf");
        viewHolder.img_raza_adapter.setImageResource(datos.get(position).getImagen());

        viewHolder.img_raza_adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irAConfirmacion(position);
            }
        });
        viewHolder.lbl_raza_adapter.setText(datos.get(position).getNombre());
        viewHolder.lbl_raza_adapter.setTypeface(roboto);
        vi.setTag(viewHolder);
        return vi;
    }


    private void irAConfirmacion(int position) {
        Confirmacion.setRaza(datos.get(position).getNombre());
        Intent intent = new Intent(context, ActividadConfirmacion.class);
        context.startActivity(intent);
    }

}
