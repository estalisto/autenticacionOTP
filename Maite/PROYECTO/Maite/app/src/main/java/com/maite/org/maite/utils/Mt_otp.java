package com.maite.org.maite.utils;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
/**
 * Created by Stalyn on 14/07/2016.
 */
@DatabaseTable(tableName="mt_opt")
public class Mt_otp {

    public static final String ID_OTP = "id_otp1";
    public static final String OTP = "otp";
    public static final String FECHA_REGISTRO = "fecha_registro";
    public static final String ESTADO = "estado";

    @DatabaseField(generatedId = true)
    private int id_otp;
    @DatabaseField
    private int otp;
    @DatabaseField
    private Date fecha_registro;
    @DatabaseField
    private String estado;
    Mt_otp(){}
    public int getId_otp() {
        return id_otp;
    }

    public void setId_otp(int id_otp) {
        this.id_otp=id_otp;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp=otp;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro=fecha_registro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado=estado;
    }







}
