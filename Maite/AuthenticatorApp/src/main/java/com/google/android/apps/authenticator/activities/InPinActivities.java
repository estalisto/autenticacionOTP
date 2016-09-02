package com.google.android.apps.authenticator.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.apps.authenticator.AddOtherAccountActivity;
import com.google.android.apps.authenticator.AuthenticatorActivity;
import com.google.android.apps.authenticator.AuthenticatorApplication;
import com.google.android.apps.authenticator.entities.IOtp;
import com.google.android.apps.authenticator.entities.PinEntities;
import com.google.android.apps.authenticator.gcmClasses.QuickstartPreferences;
import com.google.android.apps.authenticator.gcmClasses.RegistrationIntentService;
import com.google.android.apps.authenticator.utils.SharedPreferencesManager;
import com.google.android.apps.authenticator.utils.Utils;
import com.google.android.apps.authenticator2.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.sql.SQLException;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Stalyn on 24/07/2016.
 */
public class InPinActivities extends Activity {

    Intent intent;
    EditText txtclave, txtconfirmaclave;
    String PREFERENCIA_INICIO = "MaitePreferences";
    String KEY_PIN = "pin";
    TextView texto2;
    TextView texto1;
   // private String bandera;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private String TAG = "InPinActivities";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    private String PREFERENCES_INICIO_TOKEN  = "inicioPreferences";
    private String GCM_TOKEN = "gcmToken";

    private Call<IOtp.getOtp> callOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_pin_layout);
        txtclave = (EditText) findViewById(R.id.txtclave);
        txtconfirmaclave = (EditText) findViewById(R.id.txtconfirmapin);
        texto2 = (TextView) findViewById(R.id.texto2);
        texto1 = (TextView) findViewById(R.id.texto1);
        FancyButton btn = (FancyButton) findViewById(R.id.btnPin);
        if (SharedPreferencesManager.getValorEsperado(getApplicationContext(),PREFERENCES_INICIO_TOKEN,GCM_TOKEN) == null){
            registrarGCM();
        }
        else
        {
            callWS();
            Log.w(" >>>>> ",SharedPreferencesManager.getValorEsperado(getApplicationContext(),PREFERENCES_INICIO_TOKEN,GCM_TOKEN)+"");
        }

        if (SharedPreferencesManager.getValorEsperado(this,PREFERENCIA_INICIO,KEY_PIN) != null){
            texto2.setVisibility(View.GONE);
            txtconfirmaclave.setVisibility(View.GONE);
            texto1.setText("Ingrese PIN");
            btn.setText("Ingresar");
            //bandera="S";

        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // if ()) {

                    validaPin();
               // }else {
                   /* if (txtclave.getText().toString().equals("") || txtconfirmaclave.getText().toString().equals("")) {
                        Log.w("<<<<<", txtclave.getText() + "  " + txtconfirmaclave.getText());
                        Utils.generarAlerta(InPinActivities.this, "ALERTA!", "Existen campos Vacios");

                    } else if (SharedPreferencesManager.getValorEsperado(InPinActivities.this, PREFERENCIA_INICIO, KEY_PIN) != null) {
                        if (SharedPreferencesManager.getValorEsperado(InPinActivities.this, PREFERENCIA_INICIO, KEY_PIN).equals(txtclave.getText().toString().equals(""))) {
                            intent = new Intent(InPinActivities.this, AddOtherAccountActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Utils.generarAlerta(InPinActivities.this, "ALERTA!", "El pin ingresado es incorrecto");
                        }
                    } else if (!txtclave.getText().toString().equals(txtconfirmaclave.getText().toString())) {
                        Log.w("<<<<<", txtclave.getText() + "  " + txtconfirmaclave.getText());
                        Utils.generarAlerta(InPinActivities.this, "ALERTA!", "Los valores ingresados de PIN son diferentes");

                    } else if (txtclave.getText().toString().equals(txtconfirmaclave.getText().toString())) {

                        Log.w(">>>>", txtclave.getText() + "  " + txtconfirmaclave.getText());
                        savePin();

                        Log.w(">>>>>", "Valor  " + txtclave.getText().toString());

                    /*try {
                        List<PinEntities> pines = AuthenticatorApplication.getApplication().getmPinDao().queryBuilder().query();
                        for (PinEntities pi : pines)
                            Log.w(">>>> Base Dato", pi.getPin());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }\*

                        intent = new Intent(InPinActivities.this, AddOtherAccountActivity.class);
                        startActivity(intent);
                        finish();

                    }*/
                }

                /****/


//            }
        });
    }

    private void callWS() {
        IOtp iOtp = AuthenticatorApplication.getApplication().getRestAdapter().create(IOtp.class);
        callOTP = iOtp.consumerWS("dnvkjdgfndj",SharedPreferencesManager.getValorEsperado(getApplicationContext(),PREFERENCES_INICIO_TOKEN,GCM_TOKEN)+"");


        callOTP.enqueue(new Callback<IOtp.getOtp>() {
            @Override
            public void onResponse(Call<IOtp.getOtp> call, Response<IOtp.getOtp> response) {
                String err = "";
                try {
                    err = response.errorBody().toString();
                } catch (Exception e) {
                    err = "";
                }
                if (err.equalsIgnoreCase("")) {
                    if (response.body() != null) {
                        if (response.isSuccess()) {
                            IOtp.getOtp otp = response.body();
                            Log.w("-----", otp.getRespuesta() + "");
                        } else {
                            Log.e(TAG, "Error en el webservice, success false");
                        }
                    } else {
                        Log.e(TAG, "Error de web service, no viene json");
                    }
                } else {
                    Log.e(TAG, "Error en el webservice " + err);
                }
            }

            @Override
            public void onFailure(Call<IOtp.getOtp> call, Throwable t) {
                Log.w("111111", t.getMessage());
            }
        });

    }

    private void savePin() {
       // Utils.savePin(txtclave.getText().toString(), "24/07/2016");
        SharedPreferencesManager.setValor(this,PREFERENCIA_INICIO, txtclave.getText().toString(), KEY_PIN);
    }



    private void validaPin(){
        /********/
        if (SharedPreferencesManager.getValorEsperado(this, PREFERENCIA_INICIO, KEY_PIN) != null) {
            try {
                // List<PinEntities> pines = AuthenticatorApplication.getApplication().getmPinDao().queryBuilder().query();
                //for (PinEntities pi : pines) {
                //  Log.w(">>>> Base Dato", pi.getPin());
                //if (txtclave.getText().toString().equals(pi.getPin().toString())) {
                if (SharedPreferencesManager.getValorEsperado(this, PREFERENCIA_INICIO, KEY_PIN).toString().equals(txtclave.getText().toString())) {

                    intent = new Intent(InPinActivities.this, AuthenticatorActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Utils.generarAlerta(InPinActivities.this, "ALERTA!", "El pin ingresado es incorrecto");
                }
                //  }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (txtclave.getText().toString().equals("") || txtconfirmaclave.getText().toString().equals("")) {
            Log.w("<<<<<", txtclave.getText() + "  " + txtconfirmaclave.getText());
            Utils.generarAlerta(InPinActivities.this, "ALERTA!", "Existen campos Vacios");

        } else if (SharedPreferencesManager.getValorEsperado(InPinActivities.this, PREFERENCIA_INICIO, KEY_PIN) != null) {
            if (SharedPreferencesManager.getValorEsperado(InPinActivities.this, PREFERENCIA_INICIO, KEY_PIN).equals(txtclave.getText().toString().equals(""))) {
                intent = new Intent(InPinActivities.this, AddOtherAccountActivity.class);
                startActivity(intent);
                finish();
            } else {
                Utils.generarAlerta(InPinActivities.this, "ALERTA!", "El pin ingresado es incorrecto");
            }
        } else if (!txtclave.getText().toString().equals(txtconfirmaclave.getText().toString())) {
            Log.w("<<<<<", txtclave.getText() + "  " + txtconfirmaclave.getText());
            Utils.generarAlerta(InPinActivities.this, "ALERTA!", "Los valores ingresados de PIN son diferentes");

        } else if (txtclave.getText().toString().equals(txtconfirmaclave.getText().toString())) {

            Log.w(">>>>", txtclave.getText() + "  " + txtconfirmaclave.getText());
            savePin();

            Log.w(">>>>>", "Valor  " + txtclave.getText().toString());

                    /*try {
                        List<PinEntities> pines = AuthenticatorApplication.getApplication().getmPinDao().queryBuilder().query();
                        for (PinEntities pi : pines)
                            Log.w(">>>> Base Dato", pi.getPin());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }*/

            intent = new Intent(InPinActivities.this, AddOtherAccountActivity.class);
            startActivity(intent);
            finish();

        }




    }
    /**
     * Método para realizar el registro del Google CLoud Messaging
     */
    private void registrarGCM(){
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //mRegistrationProgressBar.setVisibility(ProgressBar.GONE);
                SharedPreferences sharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(context);
                boolean sentToken = sharedPreferences
                        .getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false);
                if (sentToken) {
                    Log.d(TAG,getString(R.string.gcm_send_message));
                } else {
                    Log.d(TAG,getString(R.string.token_error_message));
                }
            }
        };

        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }
    }

    /**
     * Método que valida si se tiene instalado y con la versión requerida el Google Play Service
     * ya que sin ello, la nueva forma de implementación del GCM no funcionaría
     * TODO: Descomentar cuando se obtengan el APIKey y el archivo json requeridos para implementar...
     * TODO...el GCM en la aplicación
     */
    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }
}
