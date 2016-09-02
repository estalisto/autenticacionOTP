package com.maite.org.maite.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import com.maite.org.maite.MainActivity;

/**
 * Created by Stalyn on 17/07/2016.
 */
public class MaiteBD extends SQLiteOpenHelper {

    String tb_semilla="CREATE TABLE mt_semilla (id  integer NOT NULL, clave varchar(100), periodo varchar(5),estado  varchar(1))";
    String tb_pin="CREATE TABLE mt_pin (id_pin  INTEGER PRIMARY KEY AUTOINCREMENT, pin int NOT NULL, confirma_pin integer NOT NULL, fecha_registro  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP, estado   varchar(1) NOT NULL DEFAULT 'A', CONSTRAINT mt_pin_Index01UNIQUE (id_pin));";

    public MaiteBD(MainActivity context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tb_semilla);
        db.execSQL(tb_pin);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}