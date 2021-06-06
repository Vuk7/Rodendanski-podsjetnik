package com.example.rodendanskipodsjetnik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class ViseActivity extends AppCompatActivity {

    //-------------------------------------------------------------------------------------------- > objekti
    Rodendan rod = new Rodendan(ViseActivity.this);
    //----------------------------------------------------------------------------------------------
    private Button povratak; // tipka koja nam omogucuje povratak u MainActivity (natrag_tipka)
    private TextView naziv_i_datum; // tekst na vrhu ekrana koji prikazuje datum (naziv)
    private Button dodaj_prebaci; // tipka koja nam omogucuje prebacivanje u DodajActivity (dodaj_tipka)

    private ListView lista_danas; // lista današnjih rođendana (vise_lista)
    //-------------------------------------------------------------------------------------------- > liste
    public static ArrayList<Integer> idovi = new ArrayList<Integer>(); // lista s idovima danasnjih rodendana

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vise);

        //---------------------------------------------------------------------------------------- > TIPKA natrag_tipka
        povratak = (Button)findViewById(R.id.natrag_tipka);

        povratak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //---------------------------------------------------------------------------------------- > TEXTVIEW naziv
        naziv_i_datum = (TextView)findViewById(R.id.naziv);
        postaviTrenutniDatum();
        //---------------------------------------------------------------------------------------- > TIPKA dodaj_tipka
        dodaj_prebaci = (Button)findViewById(R.id.dodaj_tipka);

        dodaj_prebaci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViseActivity.this, DodajActivity.class));
                finish();
            }
        });
        //---------------------------------------------------------------------------------------- > ListView vise_lista
        lista_danas = (ListView) findViewById(R.id.vise_lista);
        postaviListuRodendana();

        lista_danas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
        //------------------------------------------------------------------------------------------
    }

    private void postaviTrenutniDatum() // metoda koja postavlja datum na tekst na vrhu ekrana
    {
        Calendar c = Calendar.getInstance();
        int dan = c.get(Calendar.DAY_OF_MONTH);
        int mjesec = c.get(Calendar.MONTH);
        naziv_i_datum.setText("Rođendani danas (" + String.format("%02d",dan) + "." + String.format("%02d",(mjesec+1)) + ".)");
    }

    private void postaviListuRodendana() // dodaje opcije u lista rodendana
    {
        if(!idovi.isEmpty())
        {
            ArrayList<String> lista = new ArrayList<String>();
            rod.ucitajRodendane();
            String str = "";
            int id;
            for(int i=0;i<idovi.size();i++)
            {
                id = idovi.get(i);
                str = (rod.nazivi_osoba.get(id) + " (" + rod.datumi_rodenja.get(id) + ")");
                lista.add(str);
            }
            ArrayAdapter arrayadapter = new ArrayAdapter(this,R.layout.viselista,lista);
            lista_danas.setAdapter(arrayadapter);
        }
    }
}