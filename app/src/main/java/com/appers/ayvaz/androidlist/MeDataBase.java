package com.appers.ayvaz.androidlist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ayvaz on 10/26/2015.
 */
public class MeDataBase extends SQLiteOpenHelper {

    public static final String CUSTOMERS_COLUMN_ID = "id";
    public static final String CUSTOMER_COLUMN_NAME = "Order_Name";
    public static final String CUSTOMERS_TABLE_NAME = "t_order";

    public MeDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_CUSTOMER_TABLE = String.format
                ("create table if not exists %s(%s INTEGER primary key autoincrement, %s text);"
                        , CUSTOMERS_TABLE_NAME, CUSTOMERS_COLUMN_ID, CUSTOMER_COLUMN_NAME);
        db.execSQL(CREATE_CUSTOMER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}