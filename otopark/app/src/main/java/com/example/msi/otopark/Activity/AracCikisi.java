package com.example.msi.otopark.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.msi.otopark.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class AracCikisi extends AppCompatActivity {
    EditText plaka,cikissaati;
    Button cikisonayla;
    DatabaseReference dbRef;
    DateFormat df;
    EditText ucret;
    Integer sonuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arac_cikisi);

        Date tarih = new Date();
        final SimpleDateFormat dakika = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        final String strdatecikis = dakika.format(tarih);

        cikisonayla = (Button) findViewById(R.id.cikisionayla);
        plaka = (EditText) findViewById(R.id.plaka);
        ucret=(EditText)findViewById(R.id.ucret);
        cikissaati = (EditText) findViewById(R.id.cikissaati);

        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final AracGirisi aracgiris=new AracGirisi();

        cikisonayla.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                DatabaseReference newReferance = FirebaseDatabase.getInstance().getReference();
                dbRef = firebaseDatabase.getReference();
                System.out.println("tost" + ServerValue.TIMESTAMP + "     ");
                newReferance.addListenerForSingleValueEvent(new ValueEventListener() {
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.child("users").getChildren()) {
                            HashMap<String, String> plakalar = (HashMap<String, String>) ds.getValue();
                            if (plakalar.containsValue(plaka.getText().toString())) {
                                dbRef.child("aracGiris").child(plaka.getText().toString()).removeValue();
                                Toast.makeText(getApplicationContext(), "Araç Çıkışı Yapıldı!", Toast.LENGTH_LONG).show();
                                /*
                                ucret.setText(ucret.toString());
                                int sayi1f=Integer.parseInt(aracgiris.girissaati.getText().toString());
                                int sayi2f=Integer.parseInt(cikissaati.getText().toString());
                                ucret.setText(String.valueOf((sayi2f-sayi1f)*5));
                                */


                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Araç Plakası Bulunamadı!!", Toast.LENGTH_LONG).show();

                            }

                        }

                    }
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

        });

    }
}