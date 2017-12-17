package com.aplicacion.americo.parking;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class AdaptadorEstacionamientos extends ArrayAdapter<ModeloEstacionamiento> {

    private int layoutResource;
    final Context con;

    int ID_USUARIO;

    public AdaptadorEstacionamientos(Context context, int layoutResource, List<ModeloEstacionamiento> threeStringsList, int ID_USUARIO) {
        super(context, layoutResource, threeStringsList);
        con = context;
        this.ID_USUARIO = ID_USUARIO;

        this.layoutResource = layoutResource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource, null);
        }

        final ModeloEstacionamiento posElemento = getItem(position);

        TextView nombre = (TextView) view.findViewById(R.id.producto_nombre);
        TextView numpiso = (TextView) view.findViewById(R.id.producto_precio);

        Button btnReservar = (Button) view.findViewById(R.id.btnReservar);



        if (nombre != null) {
            nombre.setText(posElemento.getNombre());
        }
        if (numpiso != null) {
            numpiso.setText(posElemento.getPrecio());
        }


        if (posElemento != null) {

            if (btnReservar != null) {

                btnReservar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        Intent i = new Intent(con, ReservarActivity.class);
                        i.putExtra("ID_USUARIO", ID_USUARIO);
                        i.putExtra("ID_ESTACIONAMIENTO", posElemento.getID());
                        con.startActivity(i);
                    }
                });
            }


            /*


            final TextView txt_cantidad = (TextView) view.findViewById(R.id.txt_cantidad);
            ImageButton btn_sumar = (ImageButton) view.findViewById(R.id.btn_sumar);
            ImageButton btn_restar = (ImageButton) view.findViewById(R.id.btn_restar);
            final CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkbox);



            if (txt_cantidad != null) {

                txt_cantidad.setText(posElemento.getCantidad().toString());

                //if (checkbox != null) {
                  //  checkbox.setChecked(posElemento.getCantidad() > 0);
                //}
            }
            if (btn_sumar != null) {
                btn_sumar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int cantidad = Integer.parseInt( txt_cantidad.getText().toString())+1;
                    posElemento.setCantidad( cantidad  );
                    txt_cantidad.setText(posElemento.getCantidad().toString());

                    //checkbox.setChecked(cantidad > 0);
                }
            });
            }

            if (btn_restar != null) {
                btn_restar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int cantidad = Integer.parseInt( txt_cantidad.getText().toString()) - 1;
                        if(cantidad > -1) {
                            posElemento.setCantidad(cantidad);
                            txt_cantidad.setText(posElemento.getCantidad().toString());
                        }
                      // checkbox.setChecked(cantidad > 0);
                    }
                });
            }



            checkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((CheckBox) v).isChecked()) {
                        if(posElemento.getCantidad() == 0){
                            txt_cantidad.setText("1");
                            posElemento.setCantidad(1);
                        }
                    } else {
                        txt_cantidad.setText("0");
                        posElemento.setCantidad(0);
                    }

                }
            });*/


        }

        return view;
    }
}