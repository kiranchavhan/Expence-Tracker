package com.example.expensetracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class customAdaptor extends RecyclerView.Adapter<customAdaptor.MyViewHolder> {

    private  Context context;
    private  ArrayList t_id,t_date,t_time,t_operation,t_amount;
    customAdaptor(Context context,ArrayList t_id,
                  ArrayList t_date,ArrayList
                  t_time,ArrayList t_operation,ArrayList t_amount){
        this.context=context;
        this.t_id=t_id;
        this.t_date=t_date;
        this.t_time=t_time;
        this.t_operation=t_operation;
        this.t_amount=t_amount;
    }

    @NonNull
    @Override
    public customAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull customAdaptor.MyViewHolder holder, int position) {
        holder.t_id_txt.setText(String.valueOf(t_id.get(position)));
        holder.t_date_txt.setText(String.valueOf(t_date.get(position)));
        holder.t_time_txt.setText(String.valueOf(t_time.get(position)));
        holder.t_operation_txt.setText(String.valueOf(t_operation.get(position)));
        holder.t_amount_txt.setText(String.valueOf(t_amount.get(position)));

    }

    @Override
    public int getItemCount() {
        return t_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView t_id_txt,t_date_txt,t_time_txt,t_operation_txt,t_amount_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            t_id_txt=itemView.findViewById(R.id.tid);
            t_date_txt=itemView.findViewById(R.id.tdate);
            t_time_txt=itemView.findViewById(R.id.ttime);
            t_operation_txt=itemView.findViewById(R.id.toperation);
            t_amount_txt=itemView.findViewById(R.id.tamount);

        }
    }
}
