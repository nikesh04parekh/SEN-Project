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

public class marketyard extends AppCompatActivity
{
    private static final String TAG = "marketyard";
    private ArrayAdapter<String> myAdapter , myAdapter1;
    private DatabaseReference mDatabase;
    private Context mContext;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketyard);
        final Spinner spinner1 = findViewById(R.id.spinner1);
        final Spinner spinner2 = findViewById(R.id.spinner2);

        myAdapter = new ArrayAdapter<>(this , android.R.layout.simple_list_item_1 , getResources().getStringArray(R.array.state_arrays));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(myAdapter);

        myAdapter1 = new ArrayAdapter<>(this , android.R.layout.simple_list_item_1 , getResources().getStringArray(R.array.crop_arrays));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(myAdapter1);

        Button submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final String state = spinner1.getSelectedItem().toString();
                final String crop = spinner2.getSelectedItem().toString();
                mDatabase = FirebaseDatabase.getInstance().getReference("marketyard").child(crop.toLowerCase()).child(state);
                Log.d(TAG , state + " " + crop);
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<pojo_marketyard> temp = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            String city = ds.getKey();
                            String foo = ds.getValue().toString();
                            String[] temp1 = foo.split("-");
                            String mi = temp1[0];
                            String ma = temp1[1];
                            temp.add(new pojo_marketyard(crop , city , state , mi , ma));
                        }
                        RecyclerView recyclerView = findViewById(R.id.recyclerview);
                        adaptermarketyard myAdapter2 = new adaptermarketyard(mContext , temp);
                        recyclerView.setAdapter(myAdapter2);
                        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
