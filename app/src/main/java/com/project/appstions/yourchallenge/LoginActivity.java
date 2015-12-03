package com.project.appstions.yourchallenge;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ParseException;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project.appstions.yourchallenge.entities.EntityUser;
import com.project.appstions.yourchallenge.interfaces.OnDataSendToActivity;
import com.project.appstions.yourchallenge.services.LoginTaskConnectServices;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.Manifest.permission.READ_CONTACTS;


public class LoginActivity extends AppCompatActivity implements OnDataSendToActivity {



    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private TextView mTextView;
    private EntityUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }


    public void functionAttemptLogin(View view) {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if ( TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            // mTextView.setText("El usuario es: " + email + "  ||  Contrasena: " + password);
            LoginTaskConnectServices tarea = new LoginTaskConnectServices(this);
            tarea.execute(email,password);
        }
    }


    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 5;
    }


    @Override
    public void sendData(Object dataToParser) {


        String result= (String) dataToParser;
        JSONObject resultado= null;
        try {
            resultado = new JSONObject(result);
            JSONObject headerResponseJson= resultado.getJSONObject("header");

            String status= headerResponseJson.getString("status");


            if(status.equalsIgnoreCase("success")){
                JSONObject jsonUser= resultado.getJSONObject("body");

                try {
                    currentUser=functionConvertJsonToUser(jsonUser);
                    currentUser.setToken(headerResponseJson.getString("token"));
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }
                /**HomeActivity.currentUser= currentUser;

                 Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);

                 LoginActivity.this.startActivity(homeIntent);

                 */
                new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText(getString(R.string.message_sucess_general))
                        .setContentText("Excelente si estas registrado en la aplicación!!")
                        .show();


            }else{
                String errorMessage=headerResponseJson.getString("message");
                new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText(getString(R.string.message_error_general))
                        .setContentText(errorMessage)
                        .show();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }



      /*  try {
           // openAlert(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

    }

    private EntityUser functionConvertJsonToUser(JSONObject jsonObjectUser) throws JSONException, ParseException, java.text.ParseException {
        EntityUser user= new EntityUser();
        JSONObject jsonUser=jsonObjectUser;
        user.setNickname(jsonUser.getString("userName"));
        user.setEmail(jsonUser.getString("email"));
        user.setPlayerId(Integer.parseInt(jsonUser.getString("idPlayer")));

        String dateBirdResult=jsonObjectUser.getString("dateBirth");

        if(!dateBirdResult.equals("null")){
            String dateString=jsonObjectUser.getString("dateBirth");
            Date parsed = new Date();
            SimpleDateFormat format =
                    new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            parsed = format.parse(dateString);
            user.setBirthDate(parsed);
        }

        return user;
    }

}

