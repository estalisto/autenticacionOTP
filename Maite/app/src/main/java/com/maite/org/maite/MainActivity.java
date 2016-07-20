package com.maite.org.maite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.maite.org.maite.activities.CamaraQr;
import com.maite.org.maite.activities.LoginActivities;
import com.maite.org.maite.utils.MaiteBD;
import com.maite.org.maite.utils.SharedPreferencesManager;
import com.maite.org.maite.utils.Utils;

import mehdi.sakout.fancybuttons.FancyButton;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    EditText txtclave, txtconfirmaclave;
    String pin1 = "";
    String pin2 = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {


                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                txtclave = (EditText) findViewById(R.id.txtclave);
                txtconfirmaclave = (EditText) findViewById(R.id.txtconfirmapin);
                FancyButton btn = (FancyButton) findViewById(R.id.btnPin);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //   pin1=txtclave.getText().toString();
                        // pin2=txtconfirmaclave.getText().toString();
                        //Utils.generarAlerta(MainActivity.this, "ALERTA!", pin1.toString()+" "+pin2.toString());

                        if (txtclave.getText().toString().equals("") || txtconfirmaclave.getText().toString().equals("")) {
                            Log.w("<<<<<",txtclave.getText()+"  "+txtconfirmaclave.getText());
                            Utils.generarAlerta(MainActivity.this, "ALERTA!", "Existen campos Vacios");

                        }else if(!txtclave.getText().toString().equals(txtconfirmaclave.getText().toString())) {
                            Log.w("<<<<<", txtclave.getText() + "  " + txtconfirmaclave.getText());
                            Utils.generarAlerta(MainActivity.this, "ALERTA!", "Los valores ingresados de PIN son diferentes");

                        } else if(txtclave.getText().toString().equals(txtconfirmaclave.getText().toString()) ) {

                            Log.w(">>>>", txtclave.getText() + "  " + txtconfirmaclave.getText());
                            savePin();

                            Log.w(">>>>>", "Valor  "+txtclave.getText().toString());

                            intent = new Intent(MainActivity.this, CamaraQr.class);
                            startActivity(intent);
                            finish();

                        }
                    }
                });


        /******************
         MaiteBD DatosBD = new MaiteBD(this, "maiteBD", null,1);

         db = DatosBD.getWritableDatabase();
         String[] args = new String[] {txtclave.getText().toString()};
         Cursor c = db.rawQuery(" SELECT * FROM mt_pin WHERE nombre=? ", args);
         if (c.moveToFirst()) {
         //  textView.setText(""); // Vacio el textview
         //Recorremos el cursor hasta que no haya m&aacute;s registros
         do {
         Integer codigo= c.getInt(0);
         String nombre = c.getString(1);
         String direccion = c.getString(2);
         // textView.append("Codigo: " + codigo + " Nombre: " + nombre + " Direcci&oacute;n: " + direccion + "\n");
         } while(c.moveToNext());
         }



         ***************/
    }

    private void savePin() {
        Log.w(">>>>>", "Valor  "+txtclave.getText().toString());
        SharedPreferencesManager.setValor(this, Utils.NOMBRE_PREFERENCIA, Utils.PIN, txtclave.getText().toString());
    }





}




