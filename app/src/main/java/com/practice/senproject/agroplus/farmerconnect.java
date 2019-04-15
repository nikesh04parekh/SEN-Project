package com.practice.senproject.agroplus;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class farmerconnect extends AppCompatActivity
{
    private static final String TAG = "farmerconnect";
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
                mDatabase.child("farmerconnect").child(state).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        ArrayList<pojo_farmerconnect> temp = new ArrayList<>();
                        if (dataSnapshot.getValue() != null) {
                            for (DataSnapshot ds : dataSnapshot.getChildren())
                            {
                                temp.add(ds.getValue(pojo_farmerconnect.class));
                                //temp.add(ds.getValue(pojo_farmerconnect.class));
                                //Log.d(TAG , foo.getContact() + " " + foo.getExpertise() + " " + foo.getFarmername());
                                //temp.add(new pojo_farmerconnect(foo.getFarmername() , foo.getContact() , foo.getExpertise()));
                            }
//                            for (int i = 0 ; i < temp.size() ; i++)
//                            {
//                                Log.d(TAG , temp.get(i).getFarmername() + " " + temp.get(i).getContact() + " " + temp.get(i).getExpertise());
//                            }
                            //Log.d(TAG , " " + temp.size());
                            RecyclerView recyclerView = findViewById(R.id.recyclerview);
                            adapterfarmerconnect bar = new adapterfarmerconnect(farmerconnect.this, temp);
                            recyclerView.setAdapter(bar);
                            bar.notifyDataSetChanged();
                            recyclerView.setLayoutManager(new LinearLayoutManager(farmerconnect.this));
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
