package com.aplicacion.americo.parking;


import android.content.Context;
import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class SoapCliente extends AsyncTask<String, SoapObject, SoapObject> {

    private Context context;
    private String nombreServicio;
    private String metodo;

    String URL;
    String SOAP_ACTION;

    private static final String NAMESPACE = "http://tempuri.org/";

    public SoapCliente(Context context, String nombreServicio, String metodo) {
        this.context = context;
        this.nombreServicio = nombreServicio;
        this.metodo = metodo;

        URL = "http://losportales.azurewebsites.net/Servicios/"+this.nombreServicio+".svc?wsdl";
        SOAP_ACTION = "http://tempuri.org/I"+this.nombreServicio+"/"+this.metodo;
    }

    protected SoapObject doInBackground(String... params){
            try {
                return callWebService(params);
            }
            catch (Exception e) {
                return null;
            }
        }


    private SoapObject callWebService(String... params) {

        SoapObject request = new SoapObject(NAMESPACE, this.metodo);

        SoapSerializationEnvelope envelope = new    SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        for(int i = 0; i < (params.length/2); i++){
            request.addProperty(params[i*2],params[i*2 + 1]);
        }

        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        androidHttpTransport.debug = true;

        try {
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapObject response = (SoapObject) envelope.getResponse(); //this code causes the problem

            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return null; 
        }

    }
}
