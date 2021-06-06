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

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Date;

public class DodajActivity extends AppCompatActivity {

    //-------------------------------------------------------------------------------------------- > konstante

    public static final int maxDuljinaNazivaOsobe = 37; // konstanta sa maksimalnom duljinom karaktera u nazivu osobe
    public static final int maxDuljinaKomentara = 250; // konstanta sa maksimalnom duljinom karaktera u komentaru

    //----------------------------------------------------------------------------------------------

    private Button povratak; // tipka koja nam omogucuje povratak u MainActivity (povratak_nazad_tipka)
    private Button dodaj; // tipka koja nam omogucuje dodavanje rodendana (dodaj_uspjesno_tipka)

    private Button datum; // tipka koja sluzi za biranje datuma rodenja (datum_tipka)
    private DatePickerDialog.OnDateSetListener izaberiDatumDialog;

    private TextInputLayout ime_prezime; // sadrzi upisano ime i prezime (ime_prezime_iza)
    private TextInputEditText ime_prezime_upis; // (ime_prezime_ispred)

    private TextInputLayout komentar; // sadrzi upisano ime i prezime (komentar_iza)
    private TextInputEditText komentar_upis; // (komentar_ispred)

    //-------------------------------------------------------------------------------------------- > varijable
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
                postaviDatum(year,(month+1),dayOfMonth);
            }
        };
        //---------------------------------------------------------------------------------------- > TextInput ime_prezime
        ime_prezime = (TextInputLayout) findViewById(R.id.ime_prezime_iza);
        ime_prezime_upis = (TextInputEditText) findViewById(R.id.ime_prezime_ispred);
        //---------------------------------------------------------------------------------------- > TextInput komentar
        komentar = (TextInputLayout) findViewById(R.id.komentar_iza);
        komentar_upis = (TextInputEditText) findViewById(R.id.komentar_ispred);
    }
    //-------------------------------------------------------------------------------------------- > naziv osobe (ime i prezime)
    private String dohvatiImePrezime() // cita i vraca naziv osobe
    {
        String txt = ime_prezime.getEditText().getText().toString().trim();
        return txt;
    }
    private boolean provjeriImePrezime() // provjerava je li upisano ime i prezime
    {
        String naziv = dohvatiImePrezime();
        if(naziv.isEmpty())
        {
            poruka("Niste upisali naziv osobe!");
            return false;
        }
        else if(naziv.length() > maxDuljinaNazivaOsobe)
        {
            poruka("Upisali ste predugi naziv osobe!");
            return false;
        }
        return true;
    }
    //-------------------------------------------------------------------------------------------- > datum
    private void izaberiDatum() // otvara dialog za izbor datuma
    {
        // inicijaliziramo godinu
        if(dan == -1 || mjesec == -1 || godina == -1 || datum.getText().equals("Izaberite datum rođenja")) {
            godina = 2000;
            mjesec = dan = 1;
        }
        // kreiramo dialog
        DatePickerDialog dialog = new DatePickerDialog(DodajActivity.this,
                R.style.datepicker,izaberiDatumDialog,godina,(mjesec-1),dan);
        // postavljamo granice godina
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -120);
        dialog.getDatePicker().setMinDate(c.getTimeInMillis());

        c.add(Calendar.YEAR,120);
        dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
        //pokazujemo dialog
        dialog.show();
    }

    private void postaviDatum(int year, int month, int day) // postavljamo datum rodenja
    {
        godina = year; mjesec = month; dan = day;
        datum.setText(String.format("%02d",dan) + "." + String.format("%02d",mjesec) + "." + godina + ".");
        datum.setTextColor(Color.parseColor("#7B7B7B"));
    }

    private boolean provjeriDatum() // provjerava je li izabran datum
    {
        if(dan == -1 || mjesec == -1 || godina == -1 || datum.getText().equals("Izaberite datum rođenja"))
        {
            datum.setTextColor(Color.parseColor("#FF0000"));
            poruka("Niste izabrali datum!");
            return false;
        }
        return true;
    }

    public String dohvatiDatum() // vraca datum u obliku stringa
    {
        String str = String.format("%02d",dan) + "." + String.format("%02d",mjesec) + "." + godina + ".";
        return str;
    }
    //-------------------------------------------------------------------------------------------- > komentar
    private String dohvatiKomentar() // cita i vraca komentar
    {
        String txt = komentar.getEditText().getText().toString().trim();
        return txt;
    }

    private boolean provjeriKomentar() // provjerava je li upisan komentar
    {
        String komentar_str = dohvatiKomentar();
        if(komentar_str.isEmpty())
        {
            poruka("Niste upisali komentar!");
            return false;
        }
        else if(komentar_str.length() > maxDuljinaKomentara)
        {
            poruka("Upisali ste predugi komentar!");
            return false;
        }
        return true;
    }
    //-------------------------------------------------------------------------------------------- > dodavanje rodendana
    private void dodajRodendan() {
        if (!provjeriImePrezime() || !provjeriDatum() || !provjeriKomentar()) return;
        //dodajemo rodendan u bazu
        Rodendan rod = new Rodendan(DodajActivity.this);
        rod.dodajRodendan(dohvatiImePrezime(), dohvatiDatum(), dohvatiKomentar());
        poruka("Rođendan uspješno dodan!");
        //mijenjamo tekst u pokrenutim activityima
        MainActivity maina = new MainActivity();
        maina.postaviTekst();
        finish();
    }

    private void poruka(String tekst)
    {
        Toast errorToast = Toast.makeText(DodajActivity.this, tekst, Toast.LENGTH_SHORT);
        errorToast.show();
    }
}