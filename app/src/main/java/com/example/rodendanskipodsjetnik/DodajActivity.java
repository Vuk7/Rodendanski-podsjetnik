package com.example.rodendanskipodsjetnik;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class DodajActivity extends AppCompatActivity {

    private Button povratak; // tipka koja nam omogucuje povratak u MainActivity (povratak_nazad_tipka)
    private Button dodaj; // tipka koja nam omogucuje dodavanje rodendana (dodaj_uspjesno_tipka)

    private Button datum; // tipka koja sluzi da biranje datuma rodenja (datum_tipka)
    private DatePickerDialog.OnDateSetListener izaberiDatumDialog;



    private int dan, mjesec, godina; // sluze za spremanje izabranog datuma rodenja unutar izaberiDatum()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj);
        //---------------------------------------------------------------------------------------- >
        dan = mjesec = godina = -1;
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
                dodajRodendan();
            }
        });
        //---------------------------------------------------------------------------------------- > TIPKA datum_tipka
        datum = (Button)findViewById(R.id.datum_tipka);

        datum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                izaberiDatum();
            }
        });
        //---------------------------------------------------------------------------------------- > DIALOG izaberiDatumDialog
        izaberiDatumDialog = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                godina = year;
                mjesec = (month+1);
                dan = dayOfMonth;
                datum.setText(dan + "." + mjesec + "." + godina + ".");
                datum.setTextColor(Color.parseColor("#7B7B7B"));
                System.out.println(dan + " " + mjesec + " "+ godina);
            }
        };
    }

    private void izaberiDatum()
    {
        // inicijaliziramo godinu
        godina = 2000;
        mjesec = dan = 1;
        // kreiramo dialog
        DatePickerDialog dialog = new DatePickerDialog(DodajActivity.this,
                R.style.datepicker,izaberiDatumDialog,godina,mjesec,dan);
        // postavljamo granice godina
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -120);
        dialog.getDatePicker().setMinDate(c.getTimeInMillis());

        c.add(Calendar.YEAR,120);
        dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
        //pokazujemo dialog
        dialog.show();
    }

    private void dodajRodendan()
    {
        //provjerava je li izabran datum
        if(dan == -1 || mjesec == -1 || godina == -1 || datum.getText().equals("Izaberite datum rođenja"))
        {
            datum.setTextColor(Color.parseColor("#FF0000"));
            greska("Niste izabrali datum!");
            return;
        }
    }

    private void greska(String tekst)
    {
        Toast errorToast = Toast.makeText(DodajActivity.this, tekst, Toast.LENGTH_SHORT);
        errorToast.show();
    }
}