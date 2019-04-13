package com.practice.senproject.agroplus;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class adapter_farmerConnect extends RecyclerView.Adapter<adapter_farmerConnect.myViewHolder>
{
    private Context mContext;
    private ArrayList<disp_farmerConnect> temp = new ArrayList<>();
    public class myViewHolder extends RecyclerView.ViewHolder
    {
        private TextView name , contact , expertise;
        public myViewHolder(@NonNull View itemView)
        {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            contact = itemView.findViewById(R.id.contact);
            expertise = itemView.findViewById(R.id.expertise);
        }
    }
    adapter_farmerConnect (Context context , ArrayList<disp_farmerConnect> foo)
    {
        mContext = context;
        foo = temp;
    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_farmerconnect , viewGroup , false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i)
    {
        myViewHolder.name.setText(temp.get(i).getFarmername());
        myViewHolder.contact.setText(temp.get(i).getContact());
        myViewHolder.expertise.setText(temp.get(i).getExpertise());
    }

    @Override
    public int getItemCount() {
        return temp.size();
    }
}