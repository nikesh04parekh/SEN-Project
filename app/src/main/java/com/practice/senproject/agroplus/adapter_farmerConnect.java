package com.practice.senproject.agroplus;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class adapter_farmerConnect extends RecyclerView.Adapter<adapter_farmerConnect.myViewHolder>
{
    private static final String TAG = "adapter_farmerConnect";
    private Context mContext;
    private ArrayList<disp_farmerConnect> temp = new ArrayList<>();
    public class myViewHolder extends RecyclerView.ViewHolder
    {
        TextView name , contact , expertise;
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
        temp = foo;
    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_farmerconnect , viewGroup , false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i)
    {
        if (i < temp.size())
        {
            Log.d(TAG , temp.get(i).getFarmername() + " "
            + temp.get(i).getContact() + " " + temp.get(i).getExpertise());
            myViewHolder.name.setText(temp.get(i).getFarmername());
            //Log.d(TAG , "hi");
            myViewHolder.contact.setText(temp.get(i).getContact());
            myViewHolder.expertise.setText(temp.get(i).getExpertise());
        }
        else
            return;
    }

    @Override
    public int getItemCount() {
        //Log.d(TAG , " " + temp.size());
        return temp.size();
    }
}
