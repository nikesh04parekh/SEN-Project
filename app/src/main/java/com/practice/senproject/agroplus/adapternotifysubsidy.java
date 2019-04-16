package com.practice.senproject.agroplus;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;

public class adapternotifysubsidy extends RecyclerView.Adapter<adapternotifysubsidy.MyViewHolder> {
    private DatabaseReference ref;
    Context context;
    ArrayList<pojo_mysubsidy> notifysubsidy = new ArrayList<pojo_mysubsidy>();



    adapternotifysubsidy(Context c, ArrayList<pojo_mysubsidy> p) {
        context = c;
        notifysubsidy = p;
        Log.d("bosss",notifysubsidy.size()+"");
        ref = FirebaseDatabase.getInstance().getReference("/").child("subsidyget").child("abhi_z");
    }

    public adapternotifysubsidy() {

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_notifysubsidy, viewGroup, false);
        return new MyViewHolder(view,context,notifysubsidy);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.nsubsidyname.setText(notifysubsidy.get(position).getSubsidyname());
        holder.nstatus.setText(notifysubsidy.get(position).getStatus()+"");
        holder.namount.setText(notifysubsidy.get(position).getAmount()+"");
        if(holder.nstatus.getText().equals("0.0")){
            holder.nstatus.setText("documentverifcation");
            holder.nregister.setVisibility(View.GONE);
        }else{
            holder.nstatus.setText("registerrequired");
        }
        holder.nregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s= notifysubsidy.get(position).getSubsidyname();
                ref.child(s).child("status").setValue(0);


            }
        });





    }

    @Override
    public int getItemCount() {
        return notifysubsidy.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nsubsidyname;
        TextView nstatus;
        TextView namount;
        Button nregister;




        Context ctx;
        ArrayList<pojo_mysubsidy> mysubsidy = new ArrayList<pojo_mysubsidy>();

        public MyViewHolder(View itemView,Context ctx,ArrayList<pojo_mysubsidy> mysubsidy) {
            super(itemView);
            nsubsidyname = (TextView) itemView.findViewById(R.id.nsubsidyname);
            nstatus = (TextView) itemView.findViewById(R.id.nstatus);
            namount = (TextView) itemView.findViewById(R.id.namount);
            //nregister= itemView.findViewById(R.id.registersubsidy);

            itemView.setOnClickListener(this);
            this.ctx=ctx;
            this.mysubsidy=mysubsidy;





        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            pojo_mysubsidy p= this.mysubsidy.get(position);
            Log.d("bosssss",p.getAmount()+"");
        }
//        public void onClick(final int position)
//        {
//            btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(context, position+" is clicked", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
    }
}
