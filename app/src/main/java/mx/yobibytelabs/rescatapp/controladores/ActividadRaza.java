package mx.yobibytelabs.rescatapp.controladores;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

import mx.yobibytelabs.rescatapp.R;
import mx.yobibytelabs.rescatapp.adapters.RazaAdapter;
import mx.yobibytelabs.rescatapp.objetos.Confirmacion;
import mx.yobibytelabs.rescatapp.objetos.Perro;
import mx.yobibytelabs.rescatapp.util.Rellenador;

public class ActividadRaza extends ActionBarActivity implements RazaAdapter.OnItemClickListener{
    private ArrayList<Perro> animales;
    private Typeface roboto;

    private RecyclerView my_recycler_view;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raza);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView titulo = (TextView)findViewById(R.id.tvRaza);
        Typeface multicolore = Typeface.createFromAsset(getAssets(), "multicolore-webfont.ttf");
        titulo.setTypeface(multicolore);
        titulo.setText(new String("¿Qué Raza es \n"+ Confirmacion.getNombre()+"?"));


        my_recycler_view = (RecyclerView)findViewById(R.id.my_recycler_view);
        animales = new ArrayList<Perro>();
        my_recycler_view.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        my_recycler_view.setLayoutManager(mLayoutManager);

        Rellenador.rellenarGigantes(animales);
        RazaAdapter adapter  =  new RazaAdapter(animales,this);
        adapter.setOnItemClickListener(this);
        my_recycler_view.setAdapter(adapter);
        my_recycler_view.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void onItemClick(View view, int position) {
        Confirmacion.setRaza(animales.get(position).getNombre());
        Intent intent = new Intent(ActividadRaza.this, ActividadConfirmacion.class);
        startActivity(intent);
    }
}


