package com.google.android.apps.authenticator.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.android.apps.authenticator.adapter.ViewOTPAdapter;
import com.google.android.apps.authenticator.entities.AccesoEntities;
import com.google.android.apps.authenticator2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stalyn on 24/07/2016.
 */
public class ListadoOTPActivities extends Activity {

    List<AccesoEntities> lstAcceso;
    AccesoEntities dummy;
    ListView lstGeneral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_general_layout);
        lstGeneral = (ListView) findViewById(R.id.lstGeneral);
        lstAcceso = new ArrayList<AccesoEntities>();
        dummy = new AccesoEntities();
        dummy.setOtp("445656");
        dummy.setFecha_envio("20/04/2015");
        dummy.setUsuario("Joao");
        lstAcceso.add(dummy);
        dummy = new AccesoEntities();
        dummy.setOtp("200145");
        dummy.setFecha_envio("19/06/2016");
        dummy.setUsuario("Walther");
        lstAcceso.add(dummy);
        lstGeneral.setAdapter(new ViewOTPAdapter(this,lstAcceso));
    }
}
