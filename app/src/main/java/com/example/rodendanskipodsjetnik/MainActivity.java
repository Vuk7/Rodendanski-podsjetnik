package com.example.rodendanskipodsjetnik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button vise; //tipka koja prebacuje u ViseActivity (povezana sa vise_tipka)
    private Button svi; //tipka koja prebacuje u SviActivity (povezana sa svi_tipka)
    private Button dodaj; //tipka koja prebacuje u DodajActivity (povezana sa dodaj_tipka)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //---------------------------------------------------------------------------------------- > TIPKA vise_tipka
        vise = (Button)findViewById(R.id.vise_tipka);

        vise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViseActivity.class));
            }
        });
        //---------------------------------------------------------------------------------------- > TIPKA svi_tipka
        svi = (Button)findViewById(R.id.svi_tipka);

        svi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SviActivity.class));
            }
        });
        //---------------------------------------------------------------------------------------- > TIPKA dodaj_tipka
        dodaj = (Button)findViewById(R.id.dodaj_tipka);

        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DodajActivity.class));
            }
        });
        //---------------------------------------------------------------------------------------- >
    }
}