package com.practice.senproject.agroplus;

//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.widget.TextView;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//
//public class HomePage extends AppCompatActivity {
//
//    TextView mtext;
//   public ArrayList<mydata> mylistdata;
//    public mydata[] mylist1;
//    public long i;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_homepage);
//        mtext= findViewById(R.id.textView);
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        final DatabaseReference myRef = database.getReference();
//        mylistdata=new ArrayList<mydata>();
//
//       // myRef.setValue("Hello, World!");
//      //  myRef.child("hii").setValue("Abhi");
//
//
//
//
//        myRef.child("hii").addValueEventListener(new ValueEventListener() {
//            private static final String TAG ="Boss" ;
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//              //  mydata value = dataSnapshot.getValue(mydata.class);
//              //  Log.d(TAG, "Value is: " + value);
//              //  mtext.setText(value.getS());
//                 i= dataSnapshot.getChildrenCount();
//                int j= (int) i;
//           final   mydata[]  mylist= new mydata[j];
//                Log.i("boss",j+"");
//
//                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
//            String post = postSnapshot.getValue(String.class);
//            mylistdata.add(new mydata(post));
//            if(i-j>=0 &&i-j<i) {
//                mylist[(int) (i - j)] = new mydata(post);
//                Log.i("data",mylist[(int) (i-j)].getS());
//                j--;
//            }
//                mylist1 =new mydata[(int) i];
//                mylist1=mylist;
//            Log.i("data",post);
//           // mylistdata[(int) i]=post;
//                }
//
//                for(int k=0;k<mylist.length;k++) {
//                    Log.i("data", mylistdata.get(k).getS());
//                    Log.i("data2", mylist[k].getS());
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
//
//        mydata[] myListData = new mydata[] {
//                new mydata("Email"),
//                new mydata("Info"),
//                new mydata("Delete"),
//                new mydata("Dialer"),
//
//        };
//
//
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        myadapter adapter = new myadapter(mylist1);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
////        for(int k=0;k<mylist1.length;k++) {
//  //          Log.i("data", mylistdata.get(k).getS());
//    //        Log.i("data2", mylist1[k].getS());
//      //  }
//
//    }
//
//
//}







import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    DatabaseReference reference;
    ArrayList<mydata> list;
    Button profile, mysubsidy;
    TextView textview;
    Button market;
    pojo_farmer f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        profile = findViewById(R.id.profile);
        mysubsidy = findViewById(R.id.mysubsidy);
        textview = findViewById(R.id.textView2);
        //      market = findViewById(R.id.market);


//        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager( new LinearLayoutManager(this));
//
//
//
       reference = FirebaseDatabase.getInstance().getReference("/");
//     //   reference.child("hii").setValue(new mydata("boss"));
//
//       // for(int i=0;i<100;i++){
//        //        reference.child(i+"").setValue(new mydata(i+""));
//        //}
//
//
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                list = new ArrayList<mydata>();
//                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
//                {
//                    mydata p = dataSnapshot1.getValue(mydata.class);
//                    list.add(p);
//                }
//                adapter = new myadapter(HomePage.this,list);
//                recyclerView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(HomePage.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
//            }
//        });

        final  String  s = getIntent().getStringExtra("username");
        final String w;

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reference.child("farmer").child(s).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        f = dataSnapshot.getValue(pojo_farmer.class);

                        Intent i = new Intent(getBaseContext(),profile.class);
                        i.putExtra("username",f.username);


                        reference.removeEventListener(this);
                        startActivity(i);
                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        Button farmerConnect = findViewById(R.id.farmerConnect);
        farmerConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext() , FarmerConnect.class);
                startActivity(intent);
            }
        });

        Button agroshop = findViewById(R.id.agroshops);
        agroshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext() , Agroshop.class);
                startActivity(intent);
            }
        });
    }
}