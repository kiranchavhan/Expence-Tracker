package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class allTransaction extends AppCompatActivity {
    public RecyclerView recyclerView;
    myDataBaseHelper mydb;
    ArrayList<String>transaction_id,transaction_time,transaction_date,transaction_operation,transaction_amount;
    customAdaptor customAdaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_transaction);
        mydb=new myDataBaseHelper(allTransaction.this);
        transaction_id=new ArrayList<>();
        transaction_time=new ArrayList<>();
        transaction_date=new ArrayList<>();
        transaction_operation=new ArrayList<>();
        transaction_amount=new ArrayList<>();
        storeDataInArrays();
        customAdaptor =new customAdaptor(allTransaction.this,transaction_id,transaction_date,transaction_time,transaction_operation,transaction_amount);
        recyclerView.setAdapter(customAdaptor);
//        recyclerView.setLayoutManager(new LinearLayoutManager(allTransaction.this));
    }
    void storeDataInArrays(){
        Cursor cursor=mydb.getAllData();
        if(cursor.getCount()==0)
            Toast.makeText(this,"NO DATA",Toast.LENGTH_LONG).show();
        else
            while(cursor.moveToNext())
            {
                transaction_id.add(cursor.getString(0));
                transaction_date.add(cursor.getString(1));
                transaction_time.add(cursor.getString(2));
                transaction_operation.add(cursor.getString(3));
                transaction_amount.add(cursor.getString(4));
            }
    }
}