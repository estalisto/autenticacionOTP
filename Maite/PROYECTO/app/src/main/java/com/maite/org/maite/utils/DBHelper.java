package com.maite.org.maite.utils;
import java.util.Date;



/**
 * Created by Stalyn on 14/07/2016.
 */


import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "androcode_ormlite.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Mt_user, Integer> mt_userIntegerDao;
    private Dao<Mt_otp, Integer> mt_otpIntegerDao;
    private Dao<Mt_solicitud_acceso, Integer> mt_solicitud_accesoIntegerDao;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Mt_otp.class);
            TableUtils.createTable(connectionSource, Mt_user.class);
            TableUtils.createTable(connectionSource, Mt_solicitud_acceso.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        onCreate(db, connectionSource);
    }

    public Dao<Mt_user, Integer> getMt_userIntegerDao() throws SQLException {
        if (mt_userIntegerDao == null) {
            mt_userIntegerDao = getDao(Mt_user.class);
        }
        return mt_userIntegerDao;
    }

    public Dao<Mt_otp, Integer> getMt_otpIntegerDao() throws SQLException {
        if (mt_otpIntegerDao == null) {
            mt_otpIntegerDao = getDao(Mt_otp.class);
        }
        return mt_otpIntegerDao;
    }

    public Dao<Mt_solicitud_acceso, Integer> mt_solicitud_accesoIntegerDao() throws SQLException {
        if (mt_solicitud_accesoIntegerDao == null) {
            mt_solicitud_accesoIntegerDao = getDao(Mt_solicitud_acceso.class);
        }
        return mt_solicitud_accesoIntegerDao;
    }

    @Override
    public void close() {
        super.close();
        mt_otpIntegerDao = null;
        mt_solicitud_accesoIntegerDao = null;
        mt_userIntegerDao = null;

    }

}