package com.example.msi.otopark.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.msi.otopark.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class KayitEkrani extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ArrayList<User> users;
    Button kayitolustur;
    EditText ad,soyad,plaka,eposta,telefon,aracmodel,aracmarka;
    Spinner aracmarka1;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ekrani);

        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.marka, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        kayitolustur =(Button) findViewById(R.id.kayitolustur);
        ad=(EditText)findViewById(R.id.ad);
        soyad=(EditText)findViewById(R.id.soyad);
        plaka=(EditText)findViewById(R.id.plaka);
        eposta=(EditText)findViewById(R.id.eposta);
        telefon=(EditText)findViewById(R.id.telefon);
        aracmodel=(EditText)findViewById(R.id.aracmodel);
       aracmarka=(EditText)findViewById(R.id.aracmarka);
        //aracmarka1=(Spinner)findViewById(R.id.spinner1);

        kayitolustur.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                if((ad.getText().length()> 0)&&(soyad.getText().length()> 0)&&(plaka.getText().length()> 0)&&(eposta.getText().length()> 0)&&(telefon.getText().length()> 0)&&(aracmodel.getText().length()> 0)) {
                    Intent intent = new Intent(getApplicationContext(), KayitEkrani.class);
                    startActivity(intent);

                    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("users");

                    dbRef.push().setValue(
                            new User(
                                    ad.getText().toString(), soyad.getText().toString(), plaka.getText().toString(),
                                    eposta.getText().toString(), telefon.getText().toString(), aracmodel.getText().toString(),aracmarka.getText().toString()

                            )

                    );
                    Toast.makeText(getApplicationContext(), "Yeni Kayıt Eklendi", Toast.LENGTH_LONG).show();

                }else{

                    Toast.makeText(getApplicationContext(), "Kayıt Eklenemedi, Bilgilerinizi Tam Giriniz", Toast.LENGTH_LONG).show();

                }

            }

        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String text = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

