package com.practice.senproject.agroplus;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class adaptermarketyard extends RecyclerView.Adapter<adaptermarketyard.myViewHolder> {

    private static final String TAG = "adaptermarketyard";
    ArrayList<pojo_marketyard> foo = new ArrayList<>();
    private Context mContext;
    public adaptermarketyard(Context c , ArrayList<pojo_marketyard> temp)
    {
        mContext = c;
        foo = temp;
    }
    public class myViewHolder extends RecyclerView.ViewHolder{

        TextView name , range , city;
        ImageView imageView;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            city = itemView.findViewById(R.id.city);
            range = itemView.findViewById(R.id.range);
            imageView = itemView.findViewById(R.id.crop_image);
        }
    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_marketyard , viewGroup , false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i)
    {
        String crop_name = foo.get(i).getName();
        myViewHolder.name.setText(foo.get(i).getName());
        String fin = foo.get(i).getMi() + "-" + foo.get(i).getMa();
        myViewHolder.range.setText(fin);
        myViewHolder.city.setText(foo.get(i).getCity());
        if (crop_name.equals("Wheat"))
        {
            myViewHolder.imageView.setImageResource(R.drawable.wheat1);
        }
        else if (crop_name.equals("Rice"))
        {
            myViewHolder.imageView.setImageResource(R.drawable.rice1);
        }
        else
        {
            myViewHolder.imageView.setImageResource(R.drawable.bajra1);
        }
    }

    @Override
    public int getItemCount()
    {
        return foo.size();
    }
}
