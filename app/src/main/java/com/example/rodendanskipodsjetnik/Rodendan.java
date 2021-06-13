package com.example.rodendanskipodsjetnik;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class Rodendan {
    //-------------------------------------------------------------------------------------------- > konstruktor
    public Rodendan(Context c) {
        db = new RodendanBaza(c);
    }
    //-------------------------------------------------------------------------------------------- > objekti
    public RodendanBaza db; // objekt na klasu RodendanBaza
    //-------------------------------------------------------------------------------------------- > ArrayListe
    // u njih spremamo podatke o osobama
    public ArrayList<String> nazivi_osoba = new ArrayList<String>();
    public ArrayList<String> datumi_rodenja = new ArrayList<String>();
    public ArrayList<String> komentari = new ArrayList<String>();
    public ArrayList<Integer> idovi_osoba = new ArrayList<Integer>();

    //-------------------------------------------------------------------------------------------- > metode
    public void dodajRodendan(String naziv_osobe, String datum, String komentar)
    {
        db.dodajRodendanUBazu(naziv_osobe, datum, komentar);
    }

    public void ucitajRodendane()
    {
        nazivi_osoba.clear();
        datumi_rodenja.clear();
        komentari.clear();
        idovi_osoba.clear();
        Cursor podaci = db.ucitajRodendanBazu();
        while(podaci.moveToNext())
        {
            idovi_osoba.add(podaci.getInt(0));
            nazivi_osoba.add(podaci.getString(1));
            datumi_rodenja.add(podaci.getString(2));
            komentari.add(podaci.getString(3));
        }
    }

    public void obrisiRodendan(int id)
    {
        db.obrisiRodendanUBazi(id);
    }
}
