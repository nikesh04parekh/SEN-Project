package com.practice.senproject.agroplus;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class adapter_agroshop extends RecyclerView.Adapter<adapter_agroshop.myViewHolder>
{
    private static final String TAG = "adapter_agroshop";
    private Context mContext;
    private ArrayList<disp_AgroShop> foo = new ArrayList<>();
    public class myViewHolder extends RecyclerView.ViewHolder
    {
        TextView name , contact;
        public myViewHolder(@NonNull View itemView)
        {
            super(itemView);
            name = itemView.findViewById(R.id.shopname);
            contact = itemView.findViewById(R.id.shopcontact);
        }
    }

    adapter_agroshop(Context c , ArrayList<disp_AgroShop> temp)
    {
        mContext = c;
        foo = temp;
    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_agroshop , viewGroup , false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myviewHolder, int i)
    {
        myviewHolder.name.setText(foo.get(i).getShopName());
        myviewHolder.contact.setText(String.format(foo.get(i).getContact().toString()));
    }

    @Override
    public int getItemCount() {
        return foo.size();
    }
}
