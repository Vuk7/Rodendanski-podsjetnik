package com.example.rodendanskipodsjetnik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class ViseActivity extends AppCompatActivity {

    private Button povratak; // tipka koja nam omogucuje povratak u MainActivity (natrag_tipka)
    private TextView naziv_i_datum; // tekst na vrhu ekrana koji prikazuje datum (naziv)
    private Button dodaj_prebaci; // tipka koja nam omogucuje prebacivanje u DodajActivity (dodaj_tipka)

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
            }
        });
        //---------------------------------------------------------------------------------------- >
    }

    private void postaviTrenutniDatum() // metoda koja postavlja datum na tekst na vrhu ekrana
    {
        Calendar c = Calendar.getInstance();
        int dan = c.get(Calendar.DAY_OF_MONTH);
        int mjesec = c.get(Calendar.MONTH);
        naziv_i_datum.setText("Rođendani danas (" + dan + "." + (mjesec+1) + ".)");
    }
}