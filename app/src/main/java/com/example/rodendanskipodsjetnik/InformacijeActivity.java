package com.example.rodendanskipodsjetnik;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class InformacijeActivity extends AppCompatActivity {

    //-------------------------------------------------------------------------------------------- > objekti
    Rodendan rod = new Rodendan(InformacijeActivity.this);
    //----------------------------------------------------------------------------------------------
    private Button povratak; // tipka koja nam omogucuje povratak (natrag_tipka)
    private Button obrisi; // tipka koja nam omogucuje brisanje rodendana (obrisi_tipka)
    private TextView ime_prezime; // tekst na vrhu ekrana koji prikazuje ime i prezime (naziv)
    private TextView datum; // tekst na vrhu ekrana koji prikazuje datum (datum_rodenja_tekst)
    private TextView komentar; // tekst na vrhu ekrana koji prikazuje komentar (komentar_cijeli_tekst)
    //-------------------------------------------------------------------------------------------- > VARIJABLE
    int id_osobe;
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacije);

        rod.ucitajRodendane(); // ucitava sve rodendane
        id_osobe = Integer.parseInt(getIntent().getStringExtra("EXTRA_SESSION_ID"));
        //---------------------------------------------------------------------------------------- > TIPKA natrag_tipka
        povratak = (Button)findViewById(R.id.natrag_tipka);

        povratak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //---------------------------------------------------------------------------------------- > TIPKA obrisi_tipka
        obrisi = (Button)findViewById(R.id.obrisi_tipka);

        obrisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obrisiTrenutniRodendan();
            }
        });
        //---------------------------------------------------------------------------------------- > TextView naziv
        ime_prezime = (TextView)findViewById(R.id.naziv);
        postaviTrenutnoImePrezime();
        //---------------------------------------------------------------------------------------- > TextView datum_rodenja_tekst
        datum = (TextView) findViewById(R.id.datum_rodenja_tekst);
        postaviTrenutniDatum();
        //---------------------------------------------------------------------------------------- > TextView datum_rodenja_tekst
        komentar = (TextView) findViewById(R.id.komentar_cijeli_tekst);
        postaviTrenutniKomentar();
        //------------------------------------------------------------------------------------------
    }
    //-------------------------------------------------------------------------------------------- > METODE
    private void postaviTrenutnoImePrezime() // postavlja tekst na ime i prezime osobe
    {
        ime_prezime.setText(rod.nazivi_osoba.get(id_osobe));
    }

    private void postaviTrenutniKomentar() // postavlja tekst na komentar
    {
        komentar.setText("Komentar:\n\n" + rod.komentari.get(id_osobe));
    }

    private void postaviTrenutniDatum() // postavlja tekst na datum rodenja osobe
    {
        datum.setText("Datum rođenja: " + rod.datumi_rodenja.get(id_osobe));
    }

    private void obrisiTrenutniRodendan()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Brisanje rođendana!")
                .setMessage("Jeste li sigurni da želite obrisati ovaj rođendan?");
        builder.setPositiveButton("Da", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                rod.obrisiRodendan(rod.idovi_osoba.get(id_osobe));
                startActivity(new Intent(InformacijeActivity.this, SviActivity.class));
                MainActivity maina = new MainActivity();
                maina.postaviTekst();
                finish();
            }
        });
        builder.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}