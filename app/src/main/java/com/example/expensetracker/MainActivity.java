package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
   static int total=0;
   protected String scanEditText(EditText edt){
       String s=edt.getText().toString().trim();
       return s;
    }
    protected void debit(int amount ){
        total-=amount;
    }
    protected  void credit(int amount){
        total+=amount;
    }
    protected void display(String value,TextView txtV){
        txtV.setText(value);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //declaration
        EditText ed1=findViewById(R.id.EditText);
        EditText ed2=findViewById(R.id.EditText1);
        TextView tx5=findViewById(R.id.textView5);
        ImageView creditButton=findViewById(R.id.imageView2);
        ImageView debitButton=findViewById(R.id.imageView3);
        final String[] nameOfTransaction = new String[1];
        final String[] amount = new String[1];
        //saved on this Time and date
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        myDataBaseHelper db=new myDataBaseHelper(MainActivity.this);
        //onclick
        creditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameOfTransaction[0]=scanEditText(ed1);
                amount[0] =scanEditText(ed2);
                String value="Total:- ";
                credit(Integer.parseInt(amount[0]));
                value+=String.valueOf(total);
                display(value,tx5);
                db.addTransaction(currentTime,currentDate,nameOfTransaction[0],"+"+amount[0]);


            }
        });
        debitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameOfTransaction[0]=scanEditText(ed1);
                amount[0] =scanEditText(ed2);
                String value="Total:- ";
                debit(Integer.parseInt(amount[0]));
                value+=String.valueOf(total);
                display(value,tx5);
                db.addTransaction(currentTime,currentDate,nameOfTransaction[0],"-"+amount[0]);
            }
        });
        ImageView viewTransaction=findViewById(R.id.viewTransaction);
        viewTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,allTransaction.class);
//                startActivity(intent);
                db.onUpgrade();

            }
        });




    }
}