package com.maite.org.maite.utils;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
/**
 * Created by Stalyn on 14/07/2016.
 */
@DatabaseTable(tableName="mt_user")
public class Mt_user {

    public static final String ID_USER = "id_user";
    public static final String USUARIO = "usuario";
    public static final String PASSWORD = "password";
    public static final String SEMILLA = "semilla";
    public static final String FECHA_REGISTRO = "fecha_registro";
    public static final String ESTADO = "estado";

    @DatabaseField(generatedId=true)
    public int id_user;
    @DatabaseField
    private String usuario;
    @DatabaseField(canBeNull = false)
    private String password;
    @DatabaseField
    private String semilla;
    @DatabaseField
    private Date fecha_registro;
    @DatabaseField
    private String estado;

     Mt_user(){}

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSemilla() {
        return semilla;
    }

    public void setSemilla(String semilla) {
        this.semilla = semilla;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }





}
