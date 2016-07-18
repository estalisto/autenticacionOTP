package com.maite.org.maite.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.maite.org.maite.R;
import com.maite.org.maite.utils.SharedPreferencesManager;
import com.maite.org.maite.utils.Utils;

import mehdi.sakout.fancybuttons.FancyButton;


/**
 * Created by Stalyn on 10/07/2016.
 */
public class LoginActivities extends Activity {

    EditText txtlogin;
    EditText txtclave;
    FancyButton btnSend;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        txtlogin = (EditText) findViewById(R.id.txtlogin);
        txtclave = (EditText) findViewById(R.id.txtclave);
        btnSend = (FancyButton) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (txtlogin.getText().toString().equals("") || txtclave.getText().toString().equals("")){
                    Utils.generarAlerta(LoginActivities.this, "ALERTA", "Existen campos Vacios");
                }else if (txtlogin.getText().toString().equals("admin") && txtclave.getText().toString().equals("admin")){

                    SharedPreferencesManager.setValor(getApplicationContext(), Utils.NOMBRE_PREFERENCIA, Utils.USUARIO, txtlogin.getText().toString());
                SharedPreferencesManager.setValor(getApplicationContext(), Utils.NOMBRE_PREFERENCIA, Utils.PASSWORD, txtclave.getText().toString());
                intent = new Intent(LoginActivities.this, CamaraQr.class);
                startActivity(intent);
                    finish();
                }else{
                    Utils.generarAlerta(LoginActivities.this, "ALERTA", "Usuarios no Valido");
                }

            }
        });
    }
}
