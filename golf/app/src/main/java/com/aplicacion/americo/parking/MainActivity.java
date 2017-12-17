package com.aplicacion.americo.parking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.serialization.SoapObject;


public class MainActivity extends AppCompatActivity {
    Button btn_login;
    TextView txt_usuario;
    TextView txt_clave;
    TextView txt_error;


    ProgressDialog cargador;








    MainActivity con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Boton volver atrás


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_logo3);
        setTitle("  Iniciar sesión");

        con = this;

        btn_login = (Button)this.findViewById(R.id.login_button);



        txt_usuario = (TextView)this.findViewById(R.id.txt_usuario);
        txt_clave = (TextView)this.findViewById(R.id.txt_clave);
        txt_error = (TextView)this.findViewById(R.id.txt_error);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mostrarCargado();
                //txt_error.setText("");

                new SoapCliente(con, "Usuarios", "AutentificarUsuario"){
                    @Override
                    protected void onPostExecute(SoapObject resultado) {


                        try {

                            int codUsuario = Integer.parseInt(resultado.getProperty("Codigo").toString());
                            if(codUsuario > 0){
                                Intent myIntent = new Intent(con, SucursalActivity.class);
                                myIntent.putExtra("ID_USUARIO", codUsuario);
                                con.startActivity(myIntent);
                                txt_error.setText("");
                            } else {
                                txt_error.setText(resultado.getProperty("Mensaje").toString());
                            }

                            cargador.dismiss();

                        } catch (Exception e){
                            //Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }


                }.execute("usuario",txt_usuario.getText().toString(),"contrasena",txt_clave.getText().toString());

            }
        });


/*
        new ServicioCliente(this, "DoWork"){
            @Override
            protected void onPostExecute(String resultado) {
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
            }
        }.execute("americo"); //"44444444","88888800000000"
*/


    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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


    public void mostrarCargado(){
        if(cargador == null) cargador = new ProgressDialog(con);
        cargador.setMessage("Cargando...");
        cargador.setCancelable(false);
        cargador.setInverseBackgroundForced(false);
        cargador.show();
    }



}
