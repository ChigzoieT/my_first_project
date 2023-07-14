package com.marketplace.easyaccess;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        EditText ed4 = findViewById(R.id.usernamef);
        String admin = "adminchidi";
        EditText ed5 = findViewById(R.id.password);





        Button bn1 = findViewById(R.id.clicked);
        bn1.setOnClickListener(view -> {

            String user = ed4.getText().toString();
            String password = ed5.getText().toString();
            if (user.equals(admin)) {
                startActivity(new Intent(getApplicationContext(), admin_control.class));
            } else if (user.isEmpty()) {
                Toast.makeText(this, "username cannot be empty", Toast.LENGTH_SHORT).show();
            } else if(!user.isEmpty()) {
                DatabaseReference rep = FirebaseDatabase.getInstance().getReference("admin");
                rep.child(user).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.getValue() == null) {
                            Toast.makeText(MainActivity.this, "Please input a confirmed username", Toast.LENGTH_LONG).show();
                        } else {
                            startActivity(new Intent(getApplicationContext(), questions_adapter.class));
                            Toast.makeText(MainActivity.this, "For your app Whatsapp:09125877098", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            if(password.isEmpty()){
                Toast.makeText(this, "password cannot be empty", Toast.LENGTH_SHORT).show();
            }



        });

    }


}