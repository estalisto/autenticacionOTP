package com.google.android.apps.authenticator.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.android.apps.authenticator.entities.PinEntities;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Stalyn on 24/07/2016.
 */
public class Database extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "maite.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase mDb;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            this.mDb = db;
            Log.i(Database.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, PinEntities.class);
        } catch (SQLException e) {
            Log.e(Database.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(Database.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, PinEntities.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(Database.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        super.close();
    }

    public void BeginTransaction() {
        mDb.beginTransaction();
    }

    public void EndTransactionSuccess() {
        mDb.setTransactionSuccessful();
        mDb.endTransaction();
    }
    public void EndTransaction() {
        mDb.endTransaction();
    }
}
