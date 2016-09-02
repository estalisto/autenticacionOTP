/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.apps.authenticator.gcmClasses;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.apps.authenticator.activities.InPinActivities;
import com.google.android.apps.authenticator2.R;
import com.google.android.gms.gcm.GcmListenerService;

public class MyGcmListenerService extends GcmListenerService {

    private static final String TAG = "MyGcmListenerService";
    // [START receive_message]
    @Override
    public void onMessageReceived(String from, Bundle data) {
        //String message = data.getString("message");
        //String message = data.getString("mensaje");
        String message = "Autenticación Robusta";

/*        String tipo = data.getString("tipo");
        String encuentroID = data.getString("idPartidoNoticia");*/
        //String titulo = data.getString("titulo");
        String titulo = "Solicitud de Acceso OTP";

        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Message: " + message);

        if (from.startsWith("/topics/")) {
            // message received from some topic.
        } else {
            // normal downstream message.
        }

        // [START_EXCLUDE]
/*        int idEncuentro = 0;
        if(encuentroID!=null && !encuentroID.equals(""))
            idEncuentro = Integer.parseInt(encuentroID);
        if(tipo==null)
            tipo="";*/
        if(titulo==null)
            titulo="";
        if(message==null)
            message="";
        //sendNotification(message, tipo, idEncuentro, titulo);
        sendNotification(message, "", 1, titulo);
        //sendNotification(message, tipo, Integer.parseInt(encuentroID), titulo);

        //data.fail!!!
        // [END_EXCLUDE]
    }
    // [END receive_message]


    private void sendNotification(String message, String tipo, int encuentroID, String titulo) {
        PendingIntent pendingIntent;
        Intent intent;
        //int numeroNotificacion = SharedPreferencesManager.getValorEsperadoInt(getApplicationContext(),"NotificationCount","NotificationNumber");
        //if(numeroNotificacion>100)
        //    numeroNotificacion=0;
        Double d = Math.random() * 1024;
        Integer numeroNotificacion = d.intValue();
        /*if(tipo!=null && tipo.equals("0")) {
            intent = new Intent(this, DetallePartidoPushActivity.class);
            intent.putExtra("encuentroID", encuentroID);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            TaskStackBuilder taskStackBuilder  = TaskStackBuilder.create(this)
                    .addParentStack(DetallePartidoPushActivity.class)
                    .addNextIntent(intent);
            pendingIntent = taskStackBuilder.getPendingIntent(numeroNotificacion, PendingIntent.FLAG_UPDATE_CURRENT);
            //PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        }else{
            intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NEW_TASK);
            pendingIntent = PendingIntent.getActivity(this, numeroNotificacion , intent,
                    PendingIntent.FLAG_ONE_SHOT);
        }*/
        /*****abrir pantalla despues de la notificacion*****/
        intent = new Intent(this, InPinActivities.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NEW_TASK);
        pendingIntent = PendingIntent.getActivity(this, numeroNotificacion , intent,
        PendingIntent.FLAG_ONE_SHOT);

        Uri urlsound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.notification);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_authenticator)
                .setContentTitle(titulo)
                .setContentText(message)
                .setSound(urlsound)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(numeroNotificacion , notificationBuilder.build());
    }
}
