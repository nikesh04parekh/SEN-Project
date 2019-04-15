package com.practice.senproject.agroplus;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class agroshop extends AppCompatActivity
{
    private static final String TAG = "agroshop";
    private Context mContext;
    private ArrayAdapter<String> myAdapter , myAdapter1;
    private DatabaseReference mDatabase;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agroshop);
        mContext = agroshop.this;
        final Spinner spinner1 = findViewById(R.id.spinner1);
        final Spinner spinner2 = findViewById(R.id.spinner2);
        myAdapter = new ArrayAdapter<>(this , android.R.layout.simple_list_item_1 , getResources().getStringArray(R.array.state_arrays));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(myAdapter);

        myAdapter1 = new ArrayAdapter<>(this , android.R.layout.simple_list_item_1 , getResources().getStringArray(R.array.Gujarat_city));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(myAdapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                final ArrayList<String> temp = new ArrayList<>();
                String foo = spinner1.getSelectedItem().toString();
                mDatabase = FirebaseDatabase.getInstance().getReference("state").child(foo);
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null) {
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                //Log.d(TAG, ds.getKey() + " " + ds.getValue());
                                Object mObject = ds.getValue();
                                String mString = mObject.toString();
                                temp.add(mString);
                            }
                        }

                        //Log.d(TAG , " " + temp.size());
                        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<>(mContext , android.R.layout.simple_list_item_1 , temp);
                        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(myAdapter2);
                        spinner2.setSelection(0);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {
                // your code here
            }

        });
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String sp1 = spinner1.getSelectedItem().toString();
                final String sp2 = spinner2.getSelectedItem().toString();
                //Log.d(TAG , sp2);
                mDatabase = FirebaseDatabase.getInstance().getReference("agroshop").child(sp1);
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<pojo_agroshop> mList = new ArrayList<>();
                        ArrayList<pojo_agroshop> mList1 = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            //Log.d(TAG , " " + ds.getValue());
                            mList1.add(ds.getValue(pojo_agroshop.class));
                        }
                        for (int i = 0 ; i < mList1.size() ; i++)
                        {
                            if (mList1.get(i).getCity().equals(sp2))
                            {
                                mList.add(mList1.get(i));
                            }
                        }
                        RecyclerView recyclerView = findViewById(R.id.recyclerview);
                        adapteragroshop aas = new adapteragroshop(mContext , mList);
                        recyclerView.setAdapter(aas);
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
