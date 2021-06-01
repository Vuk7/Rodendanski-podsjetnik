package com.example.rodendanskipodsjetnik;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.Xml;
import android.view.View;

import androidx.annotation.Nullable;

import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RodendanBaza extends SQLiteOpenHelper
{
    private static final String TAG = "RodendaniBaza";

    private static final String TABLE_NAME = "rodendani_tablica";
    private static final String COL0 = "ID";
    private static final String COL1 = "NAZIV_OSOBE";
    private static final String COL2 = "DATUM_RODENJA";
    private static final String COL3 = "KOMENTAR";

    public RodendanBaza(Context context) {
        super(context, TABLE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String napraviTablicu =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COL0 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COL1 + " TEXT,"
                        + COL2 + " TEXT,"
                        + COL3 + " TEXT)";
        db.execSQL(napraviTablicu);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean dodajRodendanUBazu(String naziv_osobe, String datum, String komentar)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, naziv_osobe);
        contentValues.put(COL2, datum);
        contentValues.put(COL3, komentar);
        long rezultat = db.insert(TABLE_NAME, null, contentValues);

        if(rezultat == -1) return false;
        return true;
    }

    public Cursor ucitajRodendanBazu()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor podaci = db.rawQuery(query, null);
        return podaci;
    }
}
