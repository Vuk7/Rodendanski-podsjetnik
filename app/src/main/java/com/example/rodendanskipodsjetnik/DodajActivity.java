package com.example.rodendanskipodsjetnik;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DodajActivity extends AppCompatActivity {

    private Button povratak; // tipka koja nam omogucuje povratak u MainActivity (povratak_nazad_tipka)
    private Button dodaj; // tipka koja nam omogucuje dodavanje rodendana (dodaj_uspjesno_tipka)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj);
        //---------------------------------------------------------------------------------------- > TIPKA povratak_nazad_tipka
        povratak = (Button)findViewById(R.id.povratak_nazad_tipka);

        povratak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //---------------------------------------------------------------------------------------- > TIPKA dodaj_uspjesno_tipka
        dodaj = (Button)findViewById(R.id.dodaj_uspjesno_tipka);

        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        //---------------------------------------------------------------------------------------- >
    }
}