package com.maite.org.maite;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.maite.org.maite.utils.MaiteBD;
import com.maite.org.maite.utils.TOTP;

public class MostrarOtpOff extends AppCompatActivity {

    TextView Clave;
    TextView Period;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_otp_off);
        Clave = (TextView)findViewById(R.id.txtSemilla);
        Period = (TextView)findViewById(R.id.txtPeriodo);



        Bundle b = this.getIntent().getExtras();
        Clave.setText("Clave: "+b.getString("CLAVE"));
        Period.setText("Periodo: "+b.getString("PERIODO"));

        MaiteBD DatosBD = new MaiteBD(this, "maiteBD", null,1);

        SQLiteDatabase db = DatosBD.getWritableDatabase();

        if (db != null){

                db.execSQL("INSERT INTO mt_semilla(id,clave,periodo,estado) values (1,'b.getString(\"CLAVE\")','b.getString(\"PERIODO\")','A')");

            db.close();


        }









    }





}
