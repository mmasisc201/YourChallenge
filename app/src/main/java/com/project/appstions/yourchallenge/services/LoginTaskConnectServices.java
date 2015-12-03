package com.project.appstions.yourchallenge.services;


import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

import com.project.appstions.yourchallenge.R;
import com.project.appstions.yourchallenge.interfaces.OnDataSendToActivity;
import com.project.appstions.yourchallenge.util.ClientWebService;

import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Created by Felipe on 29/11/2015.
 */
public class LoginTaskConnectServices extends AsyncTask<String,Integer,String> {

    private String urlServer;
    private Activity activity;
    private SweetAlertDialog pDialog ;
    private static int timeOut;

    static {
        timeOut = 15000;
    }

    private OnDataSendToActivity onDataSendToActivity;

    public LoginTaskConnectServices(Activity activity) {

        //crear una instancia de la actividad que invoca el asyntask
        this.activity=activity;

        onDataSendToActivity = (OnDataSendToActivity) activity;//instanciar la interface desde el parser de la actividad pricipal

    }

    @Override
    protected String doInBackground(String... params) {

        String respStr;
        try
        {


            JSONObject requestJson= new JSONObject();


            JSONObject headerJson= new JSONObject();
            headerJson.put("country","CRI");


            JSONObject bodyJson= new JSONObject();
            bodyJson.put("password", params[1]);
            bodyJson.put("userName", params[0]);


            requestJson.put("header",headerJson);
            requestJson.put("body", bodyJson);



            Log.e("Trama", requestJson.toString());

            JSONObject responseJSON= ClientWebService.consumePostWS(urlServer, requestJson, timeOut);
            respStr= responseJSON.toString();


        }
        catch(Exception ex)
        {
            Log.e("ServicioRest", "Error!", ex);
            respStr = "";
        }

        return respStr;

    }

    @Override
    protected void onPreExecute() {

        //llenado de recurso de String con la url donde se consume WS, url del server, url del servicio concatenado.
        urlServer= activity.getString(R.string.server_url)+ activity.getString(R.string.login_url);

        //creación de un progressdialog en el hilo principal desde un Asynctask

        pDialog = new SweetAlertDialog(activity, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText(activity.getString(R.string.loading_message_login));
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected void onPostExecute(String result) {
        pDialog.cancel();
        if (result.length()>0)
        {
            //llama la interface de la actividad principal, a la cual se le pasan los datos y hace el llamado al método que muestra un mensaje o manipula los datos retornados
            onDataSendToActivity.sendData(result);
            // Log.d("Login", result.toString());
        }
    }





    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

}
