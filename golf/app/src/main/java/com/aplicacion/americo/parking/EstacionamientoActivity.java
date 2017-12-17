package com.aplicacion.americo.parking;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Americo on 19/07/2017.
 */

public class EstacionamientoActivity extends AppCompatActivity {

    ProgressDialog cargador;
    EstacionamientoActivity con;
    AdaptadorEstacionamientos AdapterEstacionamiento;
    int ID_USUARIO;
    int ID_ESTACIONAMIENTO;
    int ID_SUCURSAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estacionamientos);
        con = this;
       // cargador = new ProgressDialog(con);

        ID_USUARIO = getIntent().getExtras().getInt("ID_USUARIO");
        ID_SUCURSAL = getIntent().getExtras().getInt("ID_SUCURSAL");

        ID_ESTACIONAMIENTO = getIntent().getExtras().getInt("ID_ESTACIONAMIENTO");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Boton volver atrás
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_logo3);
        setTitle("Lista de Estacionamientos");

        mostrarCargado();

        new SoapCliente(con, "Estacionamientos", "ListaEstacionamientoPor"){
            @Override
            protected void onPostExecute(SoapObject resultado) {


                try {
                    List<ModeloEstacionamiento> estacionamiento = new ArrayList<>(); int total = resultado.getPropertyCount();

                    if(total > 0) {
                        for (int i = 0; i < total; i++) {
                            SoapObject object = (SoapObject) resultado.getProperty(i);
                            estacionamiento.add(new ModeloEstacionamiento(Integer.parseInt(object.getProperty("id").toString()), "Nro: " + object.getProperty("numero").toString(), "Piso: " + object.getProperty("piso").toString(), 0, false));
                        }
                        ListView listView = (ListView) findViewById(R.id.listaEstacionamientos);
                        AdapterEstacionamiento = new AdaptadorEstacionamientos(con, R.layout.lista_estacionamientos_item, estacionamiento, ID_USUARIO);
                        listView.setAdapter(AdapterEstacionamiento);
                    } else Toast.makeText(con, "Lo seentimos, la sucursal no tiene estacionamientos registrados.", Toast.LENGTH_SHORT).show();

                    cargador.dismiss();

                } catch (Exception e){
                    onBackPressed();
                    e.printStackTrace();
                }


                }
            }.execute("sucursal_id", ID_SUCURSAL+"" );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_estacionamientos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        if(item.getTitle() != null && item.getTitle().toString().equals("GUARDAR")) {

            AlertDialog.Builder builder = new AlertDialog.Builder(con);
            builder.setCancelable(true);
            builder.setTitle("Confirmación");
            builder.setMessage("¿Confirma que realmente desea guardar los registros?");
            builder.setPositiveButton("Confirmar",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent myIntent = new Intent(con, ReservarActivity.class);
                            myIntent.putExtra("ID_USUARIO", ID_USUARIO);
                            myIntent.putExtra("ID_ESTACIONAMIENTO", ID_ESTACIONAMIENTO);
                            con.startActivity(myIntent);

                            //Toast.makeText(con, "Se acaba de guardar satisfactoriamente los registros.", Toast.LENGTH_SHORT).show();
                        }
                    });
            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

/*
            int total = AdapterEstacionamiento.getCount();
            for(int i = 0; i< total; i++){
                System.out.println("" +AdapterEstacionamiento.getItem(i).getCantidad());
            }
*/
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //    finish();

    }




    public void mostrarCargado(){
        if(cargador == null) cargador = new ProgressDialog(con);
        cargador.setMessage("Cargando...");
        cargador.setCancelable(false);
        cargador.setInverseBackgroundForced(false);
        cargador.show();
    }

}
