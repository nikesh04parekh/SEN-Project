package com.practice.senproject.agroplus;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class notifysubsidy extends AppCompatActivity {
    private DatabaseReference reference;
    private RecyclerView notifysubsidyrecycler;
    private ArrayList<pojo_mysubsidy> list;
    adapternotifysubsidy adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifysubsidy);

        reference = FirebaseDatabase.getInstance().getReference("/").child("subsidyget").child("abhi_z");

        notifysubsidyrecycler=findViewById(R.id.notifysubsidyrecycler);




        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list  = new ArrayList<pojo_mysubsidy>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    pojo_mysubsidy p = dataSnapshot1.getValue(pojo_mysubsidy.class);
                    if(p.getStatus()!=1)
                        list.add(p);
                    Log.d("bo",list.size()+"");
                }
                final LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
                notifysubsidyrecycler.setLayoutManager(layoutManager);
                adapter = new adapternotifysubsidy(notifysubsidy.this,list);
                notifysubsidyrecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getBaseContext(), "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
