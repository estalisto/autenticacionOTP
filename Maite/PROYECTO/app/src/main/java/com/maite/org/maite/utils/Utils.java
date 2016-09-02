package com.maite.org.maite.utils;

import android.app.Activity;
import android.app.AlertDialog;

/**
 * Created by Stalyn on 10/07/2016.
 */
public class Utils {
    public static final String PIN = "PIN";
    public static String NOMBRE_PREFERENCIA = "APP";
    public static String USUARIO = "USUARIO";
    public static String PASSWORD = "PASSWORD";
    public static String CLAVE_SECRETA = "CLAVE_SECRETA";
    public static String PERIODO = "PERIODO";
    public static  void generarAlerta(Activity act , String Title , String msm)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(act);
        alert.setTitle("" + Title);
        alert.setMessage("" + msm);
        alert.setPositiveButton("OK", null);
        alert.show();
    }
}
