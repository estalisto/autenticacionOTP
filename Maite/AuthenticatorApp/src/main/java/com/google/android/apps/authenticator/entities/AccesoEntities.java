package com.google.android.apps.authenticator.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Stalyn on 24/07/2016.
 */
@DatabaseTable(tableName = "pin")
public class AccesoEntities {

    @DatabaseField
    private String otp;
    @DatabaseField
    private String fecha_envio;
    @DatabaseField
    private String usuario;

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getFecha_envio() {
        return fecha_envio;
    }

    public void setFecha_envio(String fecha_envio) {
        this.fecha_envio = fecha_envio;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
