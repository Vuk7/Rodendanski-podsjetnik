package com.example.rodendanskipodsjetnik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    //-------------------------------------------------------------------------------------------- > konstante
    public static final int BROJ_PRIKAZANIH_RODENDANA_DANAS = 4; // koliko osoba je prikazano na prvom ekranu
    //-------------------------------------------------------------------------------------------- > objekti
    Rodendan rod = new Rodendan(MainActivity.this);
    //----------------------------------------------------------------------------------------------
    private Button vise; // tipka koja prebacuje u ViseActivity (povezana sa vise_tipka)
    private Button svi; // tipka koja prebacuje u SviActivity (povezana sa svi_tipka)
    private Button dodaj; // tipka koja prebacuje u DodajActivity (povezana sa dodaj_tipka)

    private TextView danas_rodendani; // tekst s listom danasnjig rodendana (ima_rodendan)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //---------------------------------------------------------------------------------------- > TIPKA vise_tipka
        vise = (Button) findViewById(R.id.vise_tipka);

        vise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViseActivity.class));
            }
        });
        //---------------------------------------------------------------------------------------- > TIPKA svi_tipka
        svi = (Button) findViewById(R.id.svi_tipka);

        svi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SviActivity.class));
            }
        });
        //---------------------------------------------------------------------------------------- > TIPKA dodaj_tipka
        dodaj = (Button) findViewById(R.id.dodaj_tipka);

        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DodajActivity.class));
            }
        });
        //---------------------------------------------------------------------------------------- > TextView ima_rodendan
        danas_rodendani = (TextView) findViewById(R.id.ima_rodendan);
        //---------------------------------------------------------------------------------------- > postavljanje teksta
        postaviTekst(); // mijenja tekstove s listom rodendana
        //---------------------------------------------------------------------------------------- >
    }

    //-------------------------------------------------------------------------------------------- > metode
    public void postaviTekst() // ucitava rodendan iz baze i pokrece metode za mijenjanje teksta
    {
        rod.ucitajRodendane();
        postaviRodendaneDanas();
        postaviNadolazeceRodendane();
    }

    public String vratiDanasnjiDatum() // vraca string s danasnjim datuomom (pr. "4.6.")
    {
        Calendar c = Calendar.getInstance();
        int dan = c.get(Calendar.DAY_OF_MONTH);
        int mjesec = c.get(Calendar.MONTH); mjesec++;
        String str = dan + "." + mjesec + ".";
        return str;
    }

    public String stringListaRodendana() // vraca listu rodendana
    {
        String str = "";
        String datum = vratiDanasnjiDatum();
        String rodendan;
        int brojac = 0;

        for(int i=0;i<rod.datumi_rodenja.size();i++)
        {
            rodendan = rod.datumi_rodenja.get(i);
            if(rodendan.substring(0,rodendan.length()-5).equals(datum))
            {
                brojac++;
                if(brojac <= BROJ_PRIKAZANIH_RODENDANA_DANAS)
                {
                    str += (rod.nazivi_osoba.get(i) + "\n\n");
                }
            }
        }
        if(brojac == 0) str += "Danas nema rođendana.";
        else if(brojac == 1) str += "danas ima rođendan.";
        else if(brojac > BROJ_PRIKAZANIH_RODENDANA_DANAS) str += ("i " + (brojac-BROJ_PRIKAZANIH_RODENDANA_DANAS) + " drugih imaju rođendan.");
        else str += "danas imaju rođendan.";
        return str;
    }

    private void postaviRodendaneDanas() // mijenja tekst s listom rodendana
    {
        danas_rodendani.setText(stringListaRodendana());
    }

    private void postaviNadolazeceRodendane()
    {

    }
}