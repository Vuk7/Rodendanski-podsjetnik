package com.example.rodendanskipodsjetnik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SviActivity extends AppCompatActivity {

    //-------------------------------------------------------------------------------------------- > objekti
    Rodendan rod = new Rodendan(SviActivity.this);
    //----------------------------------------------------------------------------------------------
    private Button povratak; // tipka koja nam omogucuje povratak u MainActivity (povratak_tipka)
    private Button dodaj_prebaci; // tipka koja nam omogucuje prebacivanje u DodajActivity (dodaj_tipka_svi)

    private ListView lista_svi; // lista današnjih rođendana (svi_lista)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svi);

        //---------------------------------------------------------------------------------------- > TIPKA povratak_tipka
        povratak = (Button)findViewById(R.id.povratak_nazad_tipka);

        povratak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //---------------------------------------------------------------------------------------- > TIPKA dodaj_tipka_svi
        dodaj_prebaci = (Button)findViewById(R.id.dodaj_tipka_svi);

        dodaj_prebaci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SviActivity.this, DodajActivity.class));
                finish();
            }
        });
        //---------------------------------------------------------------------------------------- > ListView svi_lista
        lista_svi = (ListView) findViewById(R.id.svi_lista);
        postaviListuRodendana();

        lista_svi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
        //------------------------------------------------------------------------------------------
    }
    //-------------------------------------------------------------------------------------------- > metode
    private void postaviListuRodendana() // dodaje opcije u lista rodendana
    {
        rod.ucitajRodendane();

        ArrayList<String> lista = new ArrayList<String>();
        String str = "";
        for(int i=0;i<rod.nazivi_osoba.size();i++)
        {
            str = (rod.nazivi_osoba.get(i) + " (" + rod.datumi_rodenja.get(i) + ")");
            lista.add(str);
        }

        ArrayAdapter arrayadapter = new ArrayAdapter(this,R.layout.viselista,lista);
        lista_svi.setAdapter(arrayadapter);
    }
}