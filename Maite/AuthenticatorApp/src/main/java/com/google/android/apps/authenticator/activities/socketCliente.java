package com.google.android.apps.authenticator.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.apps.authenticator.utils.Utils;
import com.google.android.apps.authenticator2.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class socketCliente extends Activity {

    private static TextView textResponse;
    private EditText editTextAddress, editTextPort;
    private Button buttonConnect;
    private String message = "Conexión Ok!";
    private static String kq = "";
    private ClientTask myClientTask;
    private OnListener listener;
    private static boolean flag = true;
    Socket socket = null;
    String codigoOtp;


    /**Ini. variables para encriptar**/
    private static final String ENCRYPT_KEY = "abcdefghijklmnopqrstuvwxyz012345";
    private static final String TAG = "aes256";

    /*Fin */
    public interface OnListener {
        void listener(String text);
    }

    public void addListener(OnListener listener) {
        this.listener = listener;
    }

    static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (flag) {
                kq += msg.obj.toString() + "\r\n";
                textResponse.setText(kq);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_cliente);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //codigoOtp = extras.getString("codigoOTP");
            try {
                codigoOtp = AES256Cipher.AES_Encode(extras.getString("codigoOTP"), ENCRYPT_KEY).trim()+"|";
               // encryptedText = AES256Cipher.AES_Encode(extras.getString("codigoOTP"), ENCRYPT_KEY);
                // 	encryptedText = URLEncoder.encode(encryptedText, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Log.w("Sin Ecriptar >>>>>>",extras.getString("codigoOTP"));
        Log.w(TAG,"Ecnriptado: "+ codigoOtp);

        ((EditText) findViewById(R.id.editText1)).setText(codigoOtp);

        editTextAddress = (EditText) findViewById(R.id.address);
        editTextPort = (EditText) findViewById(R.id.port);
        buttonConnect = (Button) findViewById(R.id.connect);
        textResponse = (TextView) findViewById(R.id.response);
/*
        myClientTask = new ClientTask(editTextAddress.getText()
                .toString(), Integer.parseInt(editTextPort.getText()
                .toString()));
        myClientTask.execute();*/


        buttonConnect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
myClientTask = new ClientTask(editTextAddress.getText()
                .toString(), Integer.parseInt(editTextPort.getText()
                .toString()));
        myClientTask.execute();
            }
        });

    }

    public class ClientTask extends AsyncTask<String, String, String> implements
            OnListener {

        String dstAddress;
        int dstPort;
        PrintWriter out1;


        ClientTask(String addr, int port) {
            dstAddress = addr;
            dstPort = port;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);

        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub

            try {

                socket = new Socket(dstAddress, dstPort);
                out1 = new PrintWriter(socket.getOutputStream(), true);
                //out1.print("Hello server!");
                out1.flush();

                BufferedReader in1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                do {
                    try {
                        if (!in1.ready()) {
                            if (message != null) {
                                socketCliente.handler.obtainMessage(0, 0, -1,"Server: " + message).sendToTarget();
                                message = "";
                            }
                        }
                        int num = in1.read();
                        message += Character.toString((char) num);
                    } catch (Exception classNot) {
                    }

                } while (!message.equals("bye"));

                try {
                    sendMessage("bye");
                    socket.close();
                    if (socket.isClosed()){
                        Log.w(">>>>>", "lo aniquile el socket");
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    socket.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                if (socket.isClosed()) {
                    flag = false;
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Ups!", Toast.LENGTH_LONG).show();
                Utils.generarAlerta(socketCliente.this, "ALERTA!", "No se pudo iniciar conexión con el Servidor");
            }

            super.onPostExecute(result);
        }

        @Override
        public void listener(String text) {
            // TODO Auto-generated method stub
            sendMessage(text);
        }

        void sendMessage(String msg) {
            try {
                out1.print(msg);
                out1.flush();
                if (!msg.equals("bye")) {
                    socketCliente.handler.obtainMessage(0, 0, -1, msg).sendToTarget();
                    Log.w("<<<<<", msg+"\n"+"ok");
                    desencrypt();

                }else{
                    socketCliente.handler.obtainMessage(0, 0, -1,"Enviado").sendToTarget();}
            } catch (Exception ioException) {
                ioException.printStackTrace();
            }
        }

    }

    public void send(View v) {
        addListener(myClientTask);
        if (listener != null)
            listener.listener(((EditText) findViewById(R.id.editText1))
                    .getText().toString());
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        try {
            if (listener != null)
                listener.listener("bye");
            socket.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        try {
            if (listener != null)
                listener.listener("bye");
            socket.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        super.onStop();
    }

    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), socketCliente.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
        finish();
    }

    public void desencrypt() {

        String plainText2 = ((EditText) findViewById(R.id.editText1)).getText().toString();
        Log.i(TAG, "plainText : " + plainText2);
        String encryptedText = null;

        try {
            encryptedText = AES256Cipher.AES_Decode(plainText2, ENCRYPT_KEY);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i(TAG, "desencryptedText : " + encryptedText);
        Utils.generarAlerta(socketCliente.this, "ALERTA!", "El Codigo OTP enviado exitosamente: "+encryptedText);

        return;
    }



}
