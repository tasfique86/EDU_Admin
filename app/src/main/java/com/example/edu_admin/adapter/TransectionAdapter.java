package com.example.edu_admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edu_admin.R;
import com.example.edu_admin.model.TransectionReadWrite;

import java.util.ArrayList;

public class TransectionAdapter extends RecyclerView.Adapter<TransectionAdapter.MyViewHolder> {

    @NonNull
    Context context;

    ArrayList<TransectionReadWrite> list;

    public TransectionAdapter(@NonNull Context context, ArrayList<TransectionReadWrite> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.transectionlist,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TransectionReadWrite transectionReadWrite=list.get(position);

        holder.textViewroll.setText(transectionReadWrite.getRollid());
        holder.textViewsessioon.setText(transectionReadWrite.getSession());
        holder.textViewdepartment.setText(transectionReadWrite.getDepartment());
        holder.textViewtransection.setText(transectionReadWrite.getTransectionId());
        holder.textViewmobile.setText(transectionReadWrite.getMobile());
        holder.textViewsemester.setText(transectionReadWrite.getSemester());
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView textViewroll,textViewdepartment,textViewtransection,textViewsessioon,textViewmobile,textViewsemester;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            textViewroll=itemView.findViewById(R.id.transcetion_recyclerView_id);
            textViewdepartment=itemView.findViewById(R.id.transcetion_recyclerView_department);
            textViewtransection=itemView.findViewById(R.id.transcetion_recyclerView_transection);
            textViewsessioon=itemView.findViewById(R.id.transcetion_recyclerView_session);
            textViewmobile=itemView.findViewById(R.id.transcetion_recyclerView_mobile);
            textViewsemester=itemView.findViewById(R.id.transcetion_recyclerView_semester);
        }
    }
}
