package com.marketplace.easyaccess;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class questions_adapter extends AppCompatActivity {

    private InterstitialAd minterstitialAd;

    public Handler accler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions_adapters);

        TextView tv = findViewById(R.id.question);
        TextView tv2 = findViewById(R.id.answers);
        Button prev = findViewById(R.id.prev);
        Button next = findViewById(R.id.next);
        String reply = "No more data";
        String reply1 = "No data";

        MobileAds.initialize(this, initializationStatus -> Toast.makeText(questions_adapter.this, "Please wait while ad is been loaded...", Toast.LENGTH_SHORT).show());

        MobileAds.setRequestConfiguration(new RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("TEST_DEVICEPID!", "TEST_DEVICEID_N")).build());

        loadinterstitial();



        rundata();

        Spinner sp1 = findViewById(R.id.sp1);

        final int[] num = {1};
        Button start = findViewById(R.id.start);

        Spinner sp2 = findViewById(R.id.sp2);

        ArrayList<String> spin = new ArrayList<>();
        ArrayList<String> get_data = new ArrayList<>();
        ArrayList<String> spin2 = new ArrayList<>();
        ArrayList<String> get_data2 = new ArrayList<>();


        spin.add("Veterinary medicine");
        spin.add("Chemistry");
        spin.add("Comp sci");
        spin.add("Mathematics");
        spin.add("Statistics");
        spin.add("Biology");
        spin.add("Physics");
        spin.add("Agronomy");
        spin.add("Soil science");
        spin.add("Plant health mgt");
        spin.add("Civil eng");
        spin.add("Chem eng");
        spin.add("Elect elect eng");
        spin.add("Agric Eng");
        spin.add("Mech eng");
        spin.add("Comp Eng");
        spin.add("Biology edu");
        spin.add("Comp sci edu");
        spin.add("Industrial tech edu");
        spin.add("Inter sci edu");
        spin.add("Agric sci & edu");
        spin.add("Maths Edu");
        spin.add("Physics edu");
        spin.add("Chem edu");
        spin.add("Home sci edu");
        spin.add("Library & info sci");
        spin.add("Hotel mgt & tourism");
        spin.add("Home economics");
        spin.add("Human nut & diet");
        spin.add("Food sci & tech");
        spin.add("EMT");
        spin.add("Fishery");
        spin.add("Forestry");
        spin.add("Microbiology");
        spin.add("Biochemistry");
        spin.add("Plant sci & biotech");
        spin.add("Zoology & env bio");
        spin.add("Bus admin");
        spin.add("Accounting");
        spin.add("Entrepreneurial");
        spin.add("Banking & finance");
        spin.add("Animal science");
        spin.add("Agric extension");
        spin.add("Agric bus");
        spin.add("Water res mgt & Agromet");
        spin.add("English");
        spin.add("Practical chem");
        spin.add("Animal husbandry");
        spin.add("Animal production");
        spin.add("Physical & health education");
        spin.add("Nigerian history");
        spin.add("use of library");
        spin.add("Physics lab");
        spin.add("Economics");
        spin.add("Peace & conflict");
        spin.add("Philosophy & logic");
        spin.add("Computer hardware");
        spin.add("French");
        spin.add("German");
        spin.add("Agric bus mgt");
        spin.add("Agric econs");



        ArrayAdapter<String>adapt = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, spin);
        sp1.setAdapter(adapt);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                get_data.add(0, spin.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spin2.add("100");
        spin2.add("200");
        spin2.add("300");
        spin2.add("400");
        spin2.add("500");



        ArrayAdapter<String>adapt2 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, spin2);
        sp2.setAdapter(adapt2);

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
              get_data2.add(0, spin2.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        start.setOnClickListener(view -> {
            DatabaseReference datafree = FirebaseDatabase.getInstance().getReference("admin");
            datafree.child(get_data.get(0)).child(get_data2.get(0)).child(String.valueOf(num[0])).child("question").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        String info = Objects.requireNonNull(snapshot.getValue()).toString();
                        tv.setText(info);
                    }else {
                        Toast.makeText(questions_adapter.this, reply1, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            DatabaseReference dataunfree = FirebaseDatabase.getInstance().getReference("admin");
            dataunfree.child(get_data.get(0)).child(get_data2.get(0)).child(String.valueOf(num[0])).child("answer").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        String startanswer = Objects.requireNonNull(snapshot.getValue()).toString();
                        tv2.setText(startanswer);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });

        prev.setOnClickListener(view -> {
            num[0]--;
            DatabaseReference questionprev = FirebaseDatabase.getInstance().getReference("admin");
            questionprev.child(get_data.get(0)).child(get_data2.get(0)).child(String.valueOf(num[0])).child("question").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        String prevquestion = Objects.requireNonNull(snapshot.getValue()).toString();
                        tv.setText(prevquestion);
                    }else{
                        Toast.makeText(questions_adapter.this, reply, Toast.LENGTH_SHORT).show();
                        num[0]++;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            DatabaseReference answerprev = FirebaseDatabase.getInstance().getReference("admin");
            answerprev.child(get_data.get(0)).child(get_data2.get(0)).child(String.valueOf(num[0])).child("answer").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        String prevanswer = Objects.requireNonNull(snapshot.getValue()).toString();
                        tv2.setText(prevanswer);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });

        next.setOnClickListener(view -> {
            num[0]++;
            DatabaseReference questionnext = FirebaseDatabase.getInstance().getReference("admin");
            questionnext.child(get_data.get(0)).child(get_data2.get(0)).child(String.valueOf(num[0])).child("question").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        String nextquestion = Objects.requireNonNull(snapshot.getValue()).toString();
                        tv.setText(nextquestion);
                    }else {
                        Toast.makeText(questions_adapter.this, reply, Toast.LENGTH_SHORT).show();
                        num[0]--;
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            DatabaseReference answernext = FirebaseDatabase.getInstance().getReference("admin");
            answernext.child(get_data.get(0)).child(get_data2.get(0)).child(String.valueOf(num[0])).child("answer").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        String nextanswer = Objects.requireNonNull(snapshot.getValue()).toString();
                        tv2.setText(nextanswer);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        });

    }

    private void loadinterstitial(){
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this, getResources().getString(R.string.test_ad), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                minterstitialAd = null;
            }

            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                super.onAdLoaded(interstitialAd);
                minterstitialAd = interstitialAd;
            }
        });
    }

    private void showinterstitialad(){
        if(minterstitialAd != null){
            minterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdClicked() {
                    super.onAdClicked();
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                    Toast.makeText(questions_adapter.this, "Thanks!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    super.onAdFailedToShowFullScreenContent(adError);
                }

                @Override
                public void onAdImpression() {
                    super.onAdImpression();
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent();
                }
            });
            minterstitialAd.show(questions_adapter.this);
        }else {
            Toast.makeText(this, "please wait while ad is been loaded...", Toast.LENGTH_SHORT).show();
        }
    }

    public  void rundata(){
        adloader adl = new adloader(1000);
        new Thread(adl).start();


    }


    class adloader implements Runnable{

        int data;

        adloader(int data){
            this.data=data;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            accler.post(questions_adapter.this::showinterstitialad);
        }
    }

}