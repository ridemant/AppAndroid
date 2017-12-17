package com.aplicacion.americo.parking;

import android.app.ProgressDialog;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;


public class SucursalActivity extends AppCompatActivity {
    ProgressDialog cargador;
    int ID_USUARIO;
    SucursalActivity con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habitacion);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_logo3);
        setTitle(" Sucursales");

        ID_USUARIO = getIntent().getExtras().getInt("ID_USUARIO");

        con = this;

       mostrarCargado();

        new SoapCliente(con, "Sucursales", "ListaSucursal"){
            @Override
            protected void onPostExecute(SoapObject resultado) {

                try {

                    List<ModeloSucursal> habitacion = new ArrayList<>();
                    for(int i= 0; i< resultado.getPropertyCount(); i++){
                        SoapObject object = (SoapObject)resultado.getProperty(i);
                        habitacion.add(new ModeloSucursal( Integer.parseInt(object.getProperty("id").toString()) ,   object.getProperty("nombre").toString()    ));
                    }

                    GridView listView = (GridView)findViewById(R.id.gridView);
                    AdaptadorSucursales AdapterHabitacion = new AdaptadorSucursales(con, R.layout.lista_sucursales_item, habitacion, ID_USUARIO);
                    listView.setAdapter(AdapterHabitacion);
                cargador.dismiss();

                } catch (Exception e){
                    e.printStackTrace();
                }
            }


        }.execute(ID_USUARIO+"");



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finishAffinity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public int getID_USUARIO(){
        return ID_USUARIO;
    }

    public void mostrarCargado(){
       if(cargador == null) cargador = new ProgressDialog(con);
        cargador.setMessage("Cargando...");
        cargador.setCancelable(false);
        cargador.setInverseBackgroundForced(false);
        cargador.show();
    }

}
