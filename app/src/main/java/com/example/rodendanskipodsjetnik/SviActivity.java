package com.example.rodendanskipodsjetnik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SviActivity extends AppCompatActivity {

    private Button povratak; // tipka koja nam omogucuje povratak u MainActivity (povratak_tipka)
    private Button dodaj_prebaci; // tipka koja nam omogucuje prebacivanje u DodajActivity (dodaj_tipka_svi)

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
            }
        });
        //---------------------------------------------------------------------------------------- >
    }
}