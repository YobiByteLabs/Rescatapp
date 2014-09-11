package mx.yobibytelabs.rescatapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
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


public class RazaAdapter extends RecyclerView.Adapter<RazaAdapter.ViewHolder>{


    private ArrayList<Perro> datos;
    private Typeface roboto;
    private Context context;
    OnItemClickListener mItemClickListener;

    public RazaAdapter(ArrayList<Perro> datos,Context context) {
        this.datos = datos; this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView lbl_raza_adapter;
        public ImageView img_raza_adapter;
        public ViewHolder(View v) {
            super(v);
            lbl_raza_adapter =(TextView) v.findViewById(R.id.lbl_raza_adapter);
            img_raza_adapter = (ImageView) v.findViewById(R.id.img_raza_adapter);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mItemClickListener != null){
                mItemClickListener.onItemClick(view,getPosition());
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_raza, parent, false);
        roboto = Typeface.createFromAsset(context.getAssets(), "Roboto-Regular.ttf");
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.img_raza_adapter.setImageResource(datos.get(position).getImagen());

        viewHolder.lbl_raza_adapter.setText(datos.get(position).getNombre());
        viewHolder.lbl_raza_adapter.setTypeface(roboto);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }


    public interface OnItemClickListener {
        public void onItemClick(View view , int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }


}


