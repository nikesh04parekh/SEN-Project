package com.practice.senproject.agroplus;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class adapteragroshop extends RecyclerView.Adapter<adapteragroshop.myViewHolder>
{
    private static final String TAG = "adapteragroshop";
    private Context mContext;
    private ArrayList<pojo_agroshop> foo = new ArrayList<>();
    public class myViewHolder extends RecyclerView.ViewHolder
    {
        TextView name , contact;
        public myViewHolder(@NonNull View itemView)
        {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            contact = itemView.findViewById(R.id.contact);
            itemView.setClickable(true);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String call = foo.get(getPosition()).getContact().toString();
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + call));
                        mContext.startActivity(intent);
                }
            });
        }
    }

    adapteragroshop(Context c , ArrayList<pojo_agroshop> temp)
    {
        mContext = c;
        foo = temp;
    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_agroshop, viewGroup , false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myviewHolder, int i)
    {
        myviewHolder.name.setText(foo.get(i).getName());
        myviewHolder.contact.setText(String.format(foo.get(i).getContact().toString()));
    }

    @Override
    public int getItemCount() {
        return foo.size();
    }
}
