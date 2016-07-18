package com.maite.org.maite.utils;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
/**
 * Created by Stalyn on 14/07/2016.
 */
@DatabaseTable(tableName="mt_solicitud_acceso")
public class Mt_solicitud_acceso {

    public static final String ID_SOLICITUD = "id_solicitud";
    public static final String ID_USER = "id_user";
    public static final String CODIGO = "codigo";
    public static final String TRAMA_REQUERIMIENTO = "trama_req";
    public static final String TRAMA_RESPUESTA = "trama_resp";
    public static final String CODIGO_RESPUESTA = "cod_resp";
    public static final String FECHA_SOLICITUD = "fecha_solicitud";
    public static final String ESTADO = "estado";




    @DatabaseField(generatedId = true)
    private int id_solicitud;
    @DatabaseField
    private int id_user;



    @DatabaseField
    private String codigo;
    @DatabaseField
    private String trama_req;
    @DatabaseField
    private String trama_resp;
    @DatabaseField
    private Date fecha_solicitud;
    @DatabaseField
    private String estado;

    Mt_solicitud_acceso(){}

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_solicitud() {
        return id_solicitud;
    }

    public void setId_solicitud(int id_solicitud) {
        this.id_solicitud = id_solicitud;
    }
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTrama_req() {
        return trama_req;
    }

    public void setTrama_req(String trama_req) {
        this.trama_req = trama_req;
    }

    public String getTrama_resp() {
        return trama_resp;
    }

    public void setTrama_resp(String trama_resp) {
        this.trama_resp = trama_resp;
    }

    public Date getFecha_solicitud() {
        return fecha_solicitud;
    }

    public void setFecha_solicitud(Date fecha_solicitud) {
        this.fecha_solicitud = fecha_solicitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


}
