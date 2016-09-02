package com.google.android.apps.authenticator.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Stalyn on 24/07/2016.
 */
@DatabaseTable(tableName = "pin")
public class PinEntities {

    @DatabaseField
    private String pin;
    @DatabaseField
    private String fecha_registro;

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
}
