package com.project.appstions.yourchallenge.util;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Felipe on 29/11/2015.
 */
public class ClientWebService {

    public ClientWebService() {

    }

    public static JSONObject consumePostWS(String serviceURL,JSONObject jsonRequest, int timeOut){
        HttpURLConnection con = null;
        JSONObject respuesta=new JSONObject();
        URL url;


        try {

            url = new URL(serviceURL);

            String data =  jsonRequest.toString();

            con = (HttpURLConnection)url.openConnection();

            // Activar método POST
            con.setDoOutput(true);

            //Pasar timeout
            con.setConnectTimeout(timeOut);

            // Tamaño previamente conocido
            con.setFixedLengthStreamingMode(data.getBytes().length);

            // Establecer application/x-www-form-urlencoded debido a la simplicidad de los datos
            con.setRequestProperty("Content-Type", "application/json");

            OutputStream out = new BufferedOutputStream(con.getOutputStream());


            out.write(data.getBytes());

            out.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (con.getInputStream())));

            String output;
            if ((output = br.readLine()) != null) {
                respuesta= new JSONObject(output);
                //Log.d("Salida Json", output);
            }
            Log.e("salida", respuesta.toString());
            out.close();

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            if(con!=null)

                con.disconnect();
        }

        return respuesta;
    }//método

}
