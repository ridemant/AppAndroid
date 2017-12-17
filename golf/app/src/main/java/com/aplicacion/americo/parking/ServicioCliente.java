package com.aplicacion.americo.parking;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.content.Context;
import android.os.AsyncTask;


public class ServicioCliente extends AsyncTask<String, Integer, String> {

    private Context context;
    private String nombreServicio;
    private String metodo;

   //private static final String NAMESPACE = "http://localhost:49757/";
    //public static final String URL_WSDL = "http://192.168.1.40/WSPortales/prueba.svc?wsdl";




    //private static final String METHOD_NAME = "AutentificarUsuario";
    private static final String NAMESPACE = "http://tempuri.org/";




    public ServicioCliente(Context context, String nombreServicio, String metodo) {
        this.context = context;
        this.nombreServicio = nombreServicio;
        this.metodo = metodo;
    }

    @Override
    protected String doInBackground(String... params) {
        String resultado = null;

        try {

            String URL = "http://192.168.1.40/WSPortales13/"+this.nombreServicio+".svc?singleWsdl";
            String SOAP_ACTION = "http://tempuri.org/I"+this.nombreServicio+"/"+this.metodo;


            SoapObject request = new SoapObject(NAMESPACE, this.metodo);



            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true; //true;
            envelope.setOutputSoapObject(request);


            for(int i = 0; i < (params.length/2); i++){
                request.addProperty(params[i*2],params[i*2 + 1]);
            }

            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            //SoapPrimitive result = (SoapPrimitive)envelope.getResponse();
            SoapObject result=(SoapObject)envelope.getResponse();

            for(int i = 0; i < result.getPropertyCount(); i++){
                System.out.println("---"+result.getProperty(i));
            }

            resultado = result.toString();


        } catch (Exception e) {
            resultado = "Exceptando: "+e.getMessage();
        }


        return resultado;


        /*
        String result = null;

        SoapObject request = new SoapObject(NAMESPACE, this.metodo);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);

        envelope.dotNet = false;

        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(URL_WSDL);

        // Enviando un parámetros al web service
        for(int i = 0; i < params.length; i++){
            request.addProperty("v"+(i+1), params[i]);
        }

        try {

            // Enviando la petición al web service
            httpTransport.call(NAMESPACE+this.metodo, envelope);

            // Recibiendo una respuesta del web service
            SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();

            result = resultsRequestSOAP.toString();

            httpTransport.getServiceConnection().disconnect();
        } catch (IOException | XmlPullParserException e) {
            result = e.getMessage();
        }

        return result;
        */



    }

}
