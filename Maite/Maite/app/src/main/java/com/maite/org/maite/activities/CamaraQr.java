package com.maite.org.maite.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;

import com.google.zxing.Result;
import com.maite.org.maite.MainActivity;
import com.maite.org.maite.MostrarOtpOff;
import com.maite.org.maite.R;
import com.maite.org.maite.utils.SharedPreferencesManager;
import com.maite.org.maite.utils.Utils;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Stalyn on 10/07/2016.
 */
public class CamaraQr extends Activity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause

    }




    @Override
    public void handleResult(Result rawResult) {
// Do something with the result here
        Log.v(">>>>", rawResult.getText()); // Prints scan results
        Log.v(">>>>", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)
        if (!rawResult.getText().toString().isEmpty()){
            onPause();

            if (rawResult.getText().contains("secret") && rawResult.getText().contains("period")){
                Utils.generarAlerta(CamaraQr.this, "ALERTA", "El valor ingresado es correcto"+rawResult.getText());
                String cadena=rawResult.getText().toString();
                String cadena1="";
                String cadena2="";
                String Clave="";
                String Periodo="";

                //System.out.println(cadena);
                int separador=cadena.indexOf("=");
                int tamanio=cadena.length();
                cadena=cadena.substring(separador +1 ,tamanio);
                cadena1=cadena;
                int separador2=cadena.indexOf("&");
                cadena=cadena.substring(0 ,separador2);
                Utils.generarAlerta(CamaraQr.this, "ALERTA", "Valor 1: "+cadena);
                Clave=cadena;
                SharedPreferencesManager.setValor(getApplicationContext(), Utils.NOMBRE_PREFERENCIA, Utils.CLAVE_SECRETA, cadena);
                separador=cadena1.indexOf("=");
                tamanio=cadena1.length();
                cadena2=cadena1.substring(separador +1 ,tamanio);
                SharedPreferencesManager.setValor(getApplicationContext(), Utils.NOMBRE_PREFERENCIA, Utils.PERIODO, cadena2);
                Periodo=cadena2;
                Utils.generarAlerta(CamaraQr.this, "ALERTA", "Valor 2: "+cadena2);

                Intent intent=new Intent(CamaraQr.this, MostrarOtpOff.class);

                Bundle b = new Bundle();
                b.putString("CLAVE",Clave.toString());
                b.putString("PERIODO",Periodo.toString());
                intent.putExtras(b);
                startActivity(intent);
                finish();

            }else {

                Utils.generarAlerta(CamaraQr.this, "ALERTA", "El valor ingresado no es correcto");

            }

           // Intent intent = new Intent(CamaraQr.this, mis_accesos.class);
            //startActivity(intent);
//validar Trama
            //Si trama es Ok se guarda eb BD.
            //Si trama capturada no es la esperada... mensaje "Trama no Valida"
        }


        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
    }
}
