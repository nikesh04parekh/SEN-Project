package com.practice.senproject.agroplus;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class signup extends AppCompatActivity {

    private static final String TAG = "signup";
    private EditText name, username, password, birthdate, city, state , expertise , contact;
    private CheckBox checkBox;
    Button register;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        birthdate = findViewById(R.id.birthdate);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        register = findViewById(R.id.register);
        checkBox = findViewById(R.id.checkbox);
        expertise = findViewById(R.id.expertise);
        contact = findViewById(R.id.contact);
        reference = FirebaseDatabase.getInstance().getReference();
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (checkBox.isChecked())
                {
                    expertise.setVisibility(View.VISIBLE);
                }
                else
                    expertise.setVisibility(View.GONE);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(username.getText())
                        || TextUtils.isEmpty(password.getText()) || TextUtils.isEmpty(birthdate.getText()) || TextUtils.isEmpty(city.getText()) ||
                        TextUtils.isEmpty(state.getText())) {
                    Toast.makeText(getApplicationContext(), "empty fields", Toast.LENGTH_LONG).show();
                }
                else if (checkBox.isChecked() && TextUtils.isEmpty(expertise.getText()))
                {
                    Toast.makeText(getApplicationContext() , "enter expertise" , Toast.LENGTH_SHORT).show();
                }
                else {

                    final String n = name.getText().toString().trim();
                    final String u = username.getText().toString().trim();
                    final String p = password.getText().toString().trim();
                    final String b = birthdate.getText().toString().trim();
                    final String c = city.getText().toString().trim();
                    final String s = state.getText().toString().trim();
                    final String con = contact.getText().toString().trim();
                    final String exp = expertise.getText().toString().trim();
                    Toast.makeText(getApplicationContext(), n + u + p + b + c + s + "hii", Toast.LENGTH_LONG).show();
                    Log.d(TAG , con + " " + exp);
                    Toast.makeText(getApplicationContext() , con + " " + exp , Toast.LENGTH_SHORT).show();
                    Query query = reference.child("login").orderByKey().equalTo(u);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (!dataSnapshot.exists()) {
                                reference.child("farmer").child(u).setValue(new pojo_farmer(n, u, p, b, c, s,-1,-1));
                                reference.child("login").child(u).setValue(p);
                                reference.child("farmerconnect").child(s).child(u).setValue(new pojo_farmerconnect(n , con , exp));
                                Intent i = new Intent(getBaseContext(), MainActivity.class);
                                startActivity(i);
                                finish();
                            } else {

                                Toast.makeText(getApplicationContext(), "username exists", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            }
        });


    }

    }

