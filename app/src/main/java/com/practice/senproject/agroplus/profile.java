package com.practice.senproject.agroplus;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {

    TextView name,username,birthdate,income,land,city,state;
    int flag=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name=findViewById(R.id.name);
        username=findViewById(R.id.username);
        birthdate=findViewById(R.id.birthdate);
        income=findViewById(R.id.income);
        land=findViewById(R.id.land);
        city=findViewById(R.id.city);
        state=findViewById(R.id.state);

        final  String  u = getIntent().getStringExtra("username");

     final   DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("/");
        final pojo_farmer[] prof = {new pojo_farmer()};
       final AlertDialog.Builder  builder = new AlertDialog.Builder(this);


        reference1.child("farmer").child(u).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             prof[0] = dataSnapshot.getValue(pojo_farmer.class);

                if(prof[0].income==-1.0){
                    builder.setTitle("Important").setMessage("Please verify you account to start availing your subsides")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    if(! profile.this.isFinishing()) {
                        builder.create().show();


                    }
                }
                name.setText(prof[0].name);
                username.setText(prof[0].username);
                birthdate.setText(prof[0].birthdate);
                income.setText(prof[0].income+" ");
                land.setText(prof[0].land+"");
                city.setText(prof[0].city);
                state.setText(prof[0].state);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//
//        if(flag==-1){
//
//            AlertDialog.Builder  builder = new AlertDialog.Builder(this);
//            builder.setTitle("Important").setMessage("Please verify you account to start availing your subsides")
//                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//
//                        }
//                    });
//            builder.create().show();

//        }


    }

    public void onDestroy(){
        finish();
        super.onDestroy();
    }

    public void onStop(){
        finish();
        super.onStop();
    }

}
