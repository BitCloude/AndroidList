package com.appers.ayvaz.androidlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by Ayvaz on 10/26/2015.
 */
public class DBUtility{
    MeDataBase mydb;
    public static final String DATABASE_NAME = "my.db";
    public static final int DATABASE_VER = 1;
    SQLiteDatabase db;

    public DBUtility(Context context) {
        super();
        mydb = new MeDataBase(context, DATABASE_NAME, null, DATABASE_VER);
        db = mydb.getWritableDatabase();
    }

    public long insertData(Bitmap image) {
        long id = 0;

       // Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.thumbnail);

        /*ByteArrayOutputStream out = new ByteArrayOutputStream();

        image.compress(Bitmap.CompressFormat.PNG, 100, out);
*/
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        // write this to database as blob
        ContentValues cv = new ContentValues();
        cv.put(mydb.CUSTOMER_COLUMN_NAME, outputStream.toByteArray());

        id = db.insert(MeDataBase.CUSTOMERS_TABLE_NAME, null, cv);
        return id;
    }

    public Cursor retreive(int id) {
        String where = mydb.CUSTOMERS_COLUMN_ID + "=?";
        String[] where_args = {"" + id};
        String columns[] = {mydb.CUSTOMER_COLUMN_NAME};
        Cursor cursor = db.query(MeDataBase.CUSTOMERS_TABLE_NAME, columns, where, where_args, null, null, null);
        return cursor;
    }

   /* public int delete(int id) {
        int rows = 0;
        String where = mydb.CUSTOMERS_COLUMN_ID + "=?";
        String[] where_args = {"" + id};
        rows = db.delete(MeDataBase.CUSTOMERS_TABLE_NAME, where, where_args);
        return rows;
    }*/
}
