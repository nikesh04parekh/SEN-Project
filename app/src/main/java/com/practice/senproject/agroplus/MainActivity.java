package com.practice.senproject.agroplus;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

        boolean flag;
    Button register;
    Button login;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    final   TextView username = findViewById(R.id.username);
    final   TextView password = findViewById(R.id.password);
       login = findViewById(R.id.login);
         register= findViewById(R.id.register);

        reference= FirebaseDatabase.getInstance().getReference();

       // reference.child("login");


        //String s=reference.push().getKey();
        //reference.child(s).setValue(new farmer("09/09/1998",-1,"boss bhai"));


        reference.child("subsidyget").child("abhi_z").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot user: dataSnapshot.getChildren()){
                    Log.d("boss" , dataSnapshot.getKey() + dataSnapshot.getValue());


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



     register.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent i = new Intent(getBaseContext(),signup.class);
             startActivity(i);
             finish();

         }
     });




    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final String s= username.getText().toString();
            final  String p = password.getText().toString();

            flag=false;
            try {
                reference.child("login").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       for(DataSnapshot users: dataSnapshot.getChildren())
                       {
                           String username = users.getKey().toString();

                           if(username.equals(s))
                           {
                               flag = true;
                               String pass = dataSnapshot.child(username).getValue(String.class);

                               if(pass.equals(p))
                               {
                                   Toast.makeText(getApplicationContext(),"good",Toast.LENGTH_SHORT).show();
                                   Intent i = new Intent(getBaseContext(), HomePage.class);
                                   i.putExtra("username",s);
                                   startActivity(i);
                                   finish();


                               }
                               else
                               {
                                   Toast.makeText(getApplicationContext(),"wrong",Toast.LENGTH_SHORT).show();
                                   break;

                               }
                           }
                       }
                       if(!flag)
                            Toast.makeText(getApplicationContext(),"wrong ",Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
            catch (Exception e)
            {
                Toast.makeText(getApplicationContext(), "error "+e.toString(), Toast.LENGTH_SHORT).show();

            }

        }
    });
    }

}
