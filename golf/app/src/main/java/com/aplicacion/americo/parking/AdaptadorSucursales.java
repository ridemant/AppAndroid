package com.aplicacion.americo.parking;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.List;

public class AdaptadorSucursales extends ArrayAdapter<ModeloSucursal> {

    private int layoutResource;
   final Context con;
        int ID_USUARIO;
    public AdaptadorSucursales(Context context, int layoutResource, List<ModeloSucursal> threeStringsList, int IDUSUARIO) {
        super(context, layoutResource, threeStringsList);
        con = context;
        ID_USUARIO = IDUSUARIO;
        this.layoutResource = layoutResource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource, null);
        }

        final ModeloSucursal posElemento = getItem(position);

        if (posElemento != null) {
            Button titulo = (Button) view.findViewById(R.id.titulo);


            if (titulo != null) {
                titulo.setText(posElemento.getTitulo());
                titulo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(con, EstacionamientoActivity.class);
                        myIntent.putExtra("ID_USUARIO", ID_USUARIO);
                        myIntent.putExtra("ID_SUCURSAL",  posElemento.getCodSucursal());
                        myIntent.putExtra("NOM_SUCURCUSAL",  posElemento.getTitulo().toString());

                        con.startActivity(myIntent);
                       // Toast.makeText(getContext(), "asadsadsa" + posElemento.getTitulo().toString(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        }

        return view;
    }
}