package com.aplicacion.americo.parking;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.serialization.SoapObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;


public class ReservarActivity extends AppCompatActivity {

    Button btnReservar;
    TimePicker tiempo_desde;
    TimePicker tiempo_hasta;
    TextView txt_estacionamiento;

    int ID_USUARIO;
    int ID_ESTACIONAMIENTO;
    ProgressDialog cargador;

    ReservarActivity con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_logo3);
        setTitle(" Reserva de estacionamiento");

        con = this;


        ID_USUARIO = getIntent().getExtras().getInt("ID_USUARIO");
        ID_ESTACIONAMIENTO = getIntent().getExtras().getInt("ID_ESTACIONAMIENTO");

        btnReservar = (Button)this.findViewById(R.id.btnReservar);
        txt_estacionamiento = (TextView)this.findViewById(R.id.txt_estacionamiento);


        txt_estacionamiento.setText(""+ID_ESTACIONAMIENTO);

       // tiempo_desde = (TimePicker) this.findViewById(R.id.tiempo_desde);
        //tiempo_hasta = (TimePicker) this.findViewById(R.id.tiempo_hasta);

        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(con);
                builder.setCancelable(true);
                builder.setTitle("Confirmación");
                builder.setMessage("¿Confirma que dese realizar la reserva?"); // +tiempo_desde.getCurrentHour()+":"+tiempo_desde.getCurrentMinute());
                builder.setPositiveButton("Confirmar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
/*
                                    Intent i = new Intent(con, SucursalActivity.class);
                                    i.putExtra("ID_USUARIO", ID_USUARIO);
                                    con.startActivity(i);

*/


                            }
                        });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });



/*
        new SoapCliente(con, "Reservas", "Dame"){
            @Override
            protected void onPostExecute(SoapObject resultado) {

                try {

                        System.out.println("resultado---------------------->" +resultado);


                } catch (Exception e){
                    e.printStackTrace();
                }
            }


        }.execute("id", "","estacionamiento_id","", "fecha","", "estado","");
*/



    }




}
