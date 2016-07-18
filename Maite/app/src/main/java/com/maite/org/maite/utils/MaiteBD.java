package com.maite.org.maite.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Stalyn on 17/07/2016.
 */
public class MaiteBD extends SQLiteOpenHelper {

    String tb_semilla="CREATE TABLE mt_semilla (id  integer NOT NULL, clave varchar(100), periodo varchar(5),estado  varchar(1))";


    public MaiteBD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tb_semilla);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}