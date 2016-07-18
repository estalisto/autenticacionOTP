package com.maite.org.maite.utils;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
/**
 * Created by Stalyn on 14/07/2016.
 */
@DatabaseTable
public class accesosDB {

    public static final String ID = "_id";
    public static final String FECHA_SOLICITUD = "FECHA_SOLICITUD";
    public static final String CODIGO = "CODIGO";
    public static final String TRAMA_REQUERIMIENTO = "TRAMA_REQUERIMIENTO";
    public static final String TRAMA_RESPUESTA = "TRAMA_RESPUESTA";
    public static final String CODIGO_RESPUESTA = "CODIGO_RESPUESTA";
    public static final String ESTADO = "ESTADO";


}
