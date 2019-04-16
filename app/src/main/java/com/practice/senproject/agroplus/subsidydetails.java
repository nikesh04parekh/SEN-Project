package com.practice.senproject.agroplus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class subsidydetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subsidydetails);

        TextView introduction= findViewById(R.id.introduction);
        TextView details= findViewById(R.id.details);
        TextView documents=findViewById(R.id.document);

        String intro=getIntent().getStringExtra("mSubsidy");
        String detail=getIntent().getStringExtra("mDetails");
        String document=getIntent().getStringExtra("mDocuments");

        detail= detail.replace("\\n","\n");
        document= document.replace("\\n","\n");

        introduction.setText(intro);
        details.setText(detail);
        documents.setText(document);
    }
}
