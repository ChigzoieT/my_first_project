package com.marketplace.easyaccess;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class admin_control extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_it);

        Button delete_btn = findViewById(R.id.delete);
        EditText confirm_payment = findViewById(R.id.cofirm_payment_button);
        EditText question = findViewById(R.id.question_setter);
        EditText answer = findViewById(R.id.answer_setter);
        Button confirm_payment_button = findViewById(R.id.confirm_payment);
        Button set_it = findViewById(R.id.set_it);
        EditText question_number = findViewById(R.id.question_number_data);
        Spinner spin1 = findViewById(R.id.adminspin1);
        Spinner spin21 = findViewById(R.id.adminspin2);

        ArrayList<String> spint = new ArrayList<>();
        ArrayList<String> spinu = new ArrayList<>();
        ArrayList<String> aa = new ArrayList<>();
        ArrayList<String> ab = new ArrayList<>();



        spint.add("Veterinary medicine");
        spint.add("Chemistry");
        spint.add("Comp sci");
        spint.add("Mathematics");
        spint.add("Statistics");
        spint.add("Biology");
        spint.add("Physics");
        spint.add("Agronomy");
        spint.add("Soil science");
        spint.add("Plant health mgt");
        spint.add("Civil eng");
        spint.add("Chem eng");
        spint.add("Elect elect eng");
        spint.add("Agric Eng");
        spint.add("Mech eng");
        spint.add("Comp Eng");
        spint.add("Biology edu");
        spint.add("Comp sci edu");
        spint.add("Industrial tech edu");
        spint.add("Inter sci edu");
        spint.add("Agric sci & edu");
        spint.add("Maths Edu");
        spint.add("Physics edu");
        spint.add("Chem edu");
        spint.add("Home sci edu");
        spint.add("Library & info sci");
        spint.add("Hotel mgt & tourism");
        spint.add("Home economics");
        spint.add("Human nut & diet");
        spint.add("Food sci & tech");
        spint.add("EMT");
        spint.add("Fishery");
        spint.add("Forestry");
        spint.add("Microbiology");
        spint.add("Biochemistry");
        spint.add("Plant sci & biotech");
        spint.add("Zoology & env bio");
        spint.add("Bus admin");
        spint.add("Accounting");
        spint.add("Entrepreneurial");
        spint.add("Banking & finance");
        spint.add("Animal science");
        spint.add("Agric extension");
        spint.add("Agric bus");
        spint.add("Water res mgt & Agromet");
        spint.add("English");
        spint.add("Practical chem");
        spint.add("Animal husbandry");
        spint.add("Animal production");
        spint.add("Physical & health education");
        spint.add("Nigerian history");
        spint.add("use of library");
        spint.add("Physics lab");
        spint.add("Economics");
        spint.add("Peace & conflict");
        spint.add("Philosophy & logic");
        spint.add("Computer hardware");
        spint.add("French");
        spint.add("German");
        spint.add("Agric bus mgt");
        spint.add("Agric econs");


        spinu.add("100");
        spinu.add("200");
        spinu.add("300");
        spinu.add("400");
        spinu.add("500");


        ArrayAdapter<String> aab = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, spint);
        spin1.setAdapter(aab);


        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                aa.add(0, spint.get(i));
                Toast.makeText(admin_control.this, aa.get(0), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayAdapter<String> aac = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, spinu);
        spin21.setAdapter(aac);

        spin21.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ab.add(0, spinu.get(i));
                Toast.makeText(admin_control.this,  ab.get(0), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        set_it.setOnClickListener(view -> {
            String question1 = question.getText().toString();
            String answer2 = answer.getText().toString();
            String question_number2 = question_number.getText().toString();

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("admin");
            ref.child(aa.get(0)).child(ab.get(0)).child(question_number2).child("question").setValue(question1);
            ref.child(aa.get(0)).child(ab.get(0)).child(question_number2).child("answer").setValue(answer2);

            Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();

        });

        confirm_payment_button.setOnClickListener(view -> {
            String confirm_payment2 = confirm_payment.getText().toString();

            DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("admin");
            ref2.child(confirm_payment2).setValue("yes");
            Toast.makeText(this, "user confirmed", Toast.LENGTH_SHORT).show();

        });

       delete_btn.setOnClickListener(view ->{

           String question1 = question.getText().toString();
           String answer2 = answer.getText().toString();
           String question_number2 = question_number.getText().toString();

            DatabaseReference rep= FirebaseDatabase.getInstance().getReference("admin");
            rep.child(aa.get(0)).child(ab.get(0)).child(question_number2).child("question").removeValue();
            rep.child(aa.get(0)).child(ab.get(0)).child(question_number2).child("answer").removeValue();

        });

    }
}