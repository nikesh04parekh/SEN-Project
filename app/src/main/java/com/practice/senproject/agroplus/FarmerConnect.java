package com.practice.senproject.agroplus;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FarmerConnect extends AppCompatActivity
{
    private static final String TAG = "FarmerConnect";
    private ArrayList<disp_farmerConnect> temp = new ArrayList<>();

    private DatabaseReference mDatabase;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmerconnect);
        final Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.state_arrays));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String state = mySpinner.getSelectedItem().toString();
                mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("farmerconnect").child(state).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        if (dataSnapshot.getValue() != null) {
                            for (DataSnapshot ds : dataSnapshot.getChildren())
                            {
                                Log.d(TAG , ds.getValue(disp_farmerConnect.class).getFarmername());
                                temp.add(ds.getValue(disp_farmerConnect.class));
                                //temp.add(ds.getValue(disp_farmerConnect.class));
                                //Log.d(TAG , foo.getContact() + " " + foo.getExpertise() + " " + foo.getFarmername());
                                //temp.add(new disp_farmerConnect(foo.getFarmername() , foo.getContact() , foo.getExpertise()));
                            }
                            RecyclerView recyclerView = findViewById(R.id.recyclerview);
                            adapter_farmerConnect bar = new adapter_farmerConnect(FarmerConnect.this, temp);
                            recyclerView.setAdapter(bar);
                            recyclerView.setLayoutManager(new LinearLayoutManager(FarmerConnect.this));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }

}