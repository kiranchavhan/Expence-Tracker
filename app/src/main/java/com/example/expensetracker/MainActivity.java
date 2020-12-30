package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
   public static int total;
    myDataBaseHelper db=new myDataBaseHelper(MainActivity.this);
   protected String scanEditText(EditText edt){
       String s=edt.getText().toString().trim();
       return s;
    }
    protected int debit(int amount ){
        return amount=-amount;
    }
    protected  int credit(int amount){
        return amount;
    }
    public void display(String value,TextView txtV){
        txtV.setText(value);
    }
    public void gettotal(){
        Cursor cursor=db.readTotal();
        if(cursor.getCount()==0)
            Toast.makeText(this,"NO DATA",Toast.LENGTH_LONG).show();
        else {
            while (cursor.moveToNext()) {
                total = Integer.parseInt(cursor.getString(0));
            }
        }
    }
    protected int processTotal(int amount){
            gettotal();
            total += amount;

            return total;

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
        gettotal();
        String value="Total:- ";
        value+=String.valueOf(total);
        display(value,tx5);

        //onclick
        creditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameOfTransaction[0]=scanEditText(ed1);
                amount[0] =scanEditText(ed2);
                String value="Total:- ";
                int temp=credit(Integer.parseInt(amount[0]));
                db.addTransaction(currentTime,currentDate,nameOfTransaction[0],"+"+amount[0]);

                total=processTotal(temp);
                db.updateTotal(total);
                value+=String.valueOf(total);
                display(value,tx5);

            }
        });
        debitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameOfTransaction[0]=scanEditText(ed1);
                amount[0] =scanEditText(ed2);
                String value="Total:- ";
                int temp=debit(Integer.parseInt(amount[0]));
                db.addTransaction(currentTime,currentDate,nameOfTransaction[0],"-"+amount[0]);
                total=processTotal(temp);
                db.updateTotal(total);
                value+=String.valueOf(total);
                display(value,tx5);

            }
        });
        ImageView viewTransaction=findViewById(R.id.viewTransaction);
        viewTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,allTransaction.class);
                startActivity(intent);
            }
        });




    }
}