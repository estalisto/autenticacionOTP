package com.google.android.apps.authenticator.entities;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Stalyn on 24/08/2016.
 */
public interface IOtp {

    @GET("AddPushID.php")
    Call<getOtp> consumerWS(@Query("ws") String usuario,
                            @Query("pushid") String password);
    /*
    @GET("otp.php")
    Call<getOtp> consumerWS(@Query("usuario") String usuario,
                            @Query("password") String password);
*/
    public class getOtp{
        private String respuesta;
        private String codigo;

        public String getRespuesta() {
            return respuesta;
        }

        public void setRespuesta(String respuesta) {
            this.respuesta = respuesta;
        }

        public String getCodigo() {
            return codigo;
        }

        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }
    }
}
