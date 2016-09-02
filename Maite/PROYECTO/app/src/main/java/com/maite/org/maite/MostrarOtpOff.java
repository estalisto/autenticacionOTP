package com.maite.org.maite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.maite.org.maite.utils.Utils;
import com.maite.org.maite.utils.autenticacionOTP;

public class MostrarOtpOff extends AppCompatActivity {

    TextView Clave;
    TextView Period;
    TextView Otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_otp_off);
        Clave = (TextView)findViewById(R.id.txtSemilla);
        Period = (TextView)findViewById(R.id.txtPeriodo);
        Otp=(TextView)findViewById(R.id.txtOtp);



        Bundle b = this.getIntent().getExtras();
        Clave.setText("Clave: "+b.getString("CLAVE"));
        Period.setText("Periodo: "+b.getString("PERIODO"));


        autenticacionOTP otpG= new autenticacionOTP();

     // String OOtp= otpG.uri("otpauth://totp/TOTP000102AC?secret=YUY47U2R3TSHGXS7XLHXYIS7WLTNC5ZS&period=30");
        Integer NumOTP=otpG.generate2(b.getString("CLAVE"),30);
        Utils.generarAlerta(MostrarOtpOff.this, "ALERTA!", "OTP:  " + NumOTP);
        Otp.setText("OTP: "+NumOTP);








       // Otp.setText(otpG.generateOTP2("YUY47U2R3TSHGXS7XLHXYIS7WLTNC5ZS",30,8,8));

      /*  MaiteBD DatosBD = new MaiteBD(this, "maiteBD", null,1);

        SQLiteDatabase db = DatosBD.getWritableDatabase();

        if (db != null){

                //db.execSQL("INSERT INTO mt_semilla(id,clave,periodo,estado) values (1,'b.getString(\"CLAVE\")','b.getString(\"PERIODO\")','A')");
           // db.execSQL("insert into mt_pin(pin,confirma_pin)values ("+b.getInt("PIN")+","+b.getInt("PIN")+")");


            db.close();


        }*/



/******************

        TOTP otp = new TOTP();

        // Seed for HMAC-SHA1 - 20 bytes
        String seed = "YUY47U2R3TSHGXS7XLHXYIS7WLTNC5ZS";
        // Seed for HMAC-SHA256 - 32 bytes
        String seed32 = "YUY47U2R3TSHGXS7XLHXYIS7WLTNC5ZS" +
                "YUY47U2R3TSHGXS7XLHXYIS7WLTNC5ZS";
        // Seed for HMAC-SHA512 - 64 bytes
        String seed64 = "YUY47U2R3TSHGXS7XLHXYIS7WLTNC5ZS" +
                "YUY47U2R3TSHGXS7XLHXYIS7WLTNC5ZS" +
                "YUY47U2R3TSHGXS7XLHXYIS7WLTNC5ZS" +
                "YUY47U2R3TSHGXS7XLHXYIS7WLTNC5ZS";
        long T0 = 0;
        long X = 30;
        long testTime[] = {59L, 1111111109L, 1111111111L,
                1234567890L, 2000000000L, 20000000000L};

        String steps = "0";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {


            for (int i=0; i<testTime.length; i++) {
                long T = (testTime[i] - T0)/X;
                steps = Long.toHexString(T).toUpperCase();
                while (steps.length() < 16) steps = "0" + steps;
                String fmtTime = String.format("%1$-11s", testTime[i]);
                String utcTime = df.format(new Date(testTime[i]*1000));

                Otp.setText(otp.generateTOTP2(seed, steps, "8","HmacSHA1")+" "+otp.generateTOTP2(seed32, steps, "8","HmacSHA256")+" "+otp.generateTOTP2(seed64, steps, "8","HmacSHA512"));



            }
        }catch (final Exception e){
            Otp.setText("Error : " + e);
        }


*/







    }





}
