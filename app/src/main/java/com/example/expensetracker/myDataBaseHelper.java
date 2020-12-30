package com.example.expensetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.security.Key;

public class myDataBaseHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME="EXPENSE.db";
    public static final int DATABASE_VERSION=1;
    public static final String TABLE_NAME="ExpenseTable";
    public static final String TABLE_NAME1="TotalExpenditure";
    public static final String Coloumn_id="id";
    public static final String Coloumn_time="Time";
    public static final String Coloumn_date="Date";
    public static final String Coloumn_Transaction_Name="Transaction_Name";
    public static final String Coloumn_amount="Amount";
    public static final String Colomn_operation="operation";
    public static final String key="Total_key";
    public static final String T_total="total";

    public myDataBaseHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+TABLE_NAME+" ("+Coloumn_id +" INTEGER PRIMARY KEY AUTOINCREMENT, "+Coloumn_date+" Text, "+Coloumn_time+" Text, "+
                Coloumn_Transaction_Name+" Text, "+Coloumn_amount+" Text, "+Colomn_operation+"Text);";
        db.execSQL(query);
        query="CREATE TABLE "+TABLE_NAME1+" ("+key+" TEXT, "+T_total+" INT );";
        db.execSQL(query);
        query= "INSERT INTO "+TABLE_NAME1+" ("+key+", "+T_total+" ) VALUES ('KEY',"+"0"+");";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE  IF EXISTS "+TABLE_NAME);

        db.execSQL("DROP TABLE  IF EXISTS "+TABLE_NAME1);

    }
    void addTransaction(String Time,String Date,String Transaction_Name,String Amount){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Coloumn_time,Time);
        cv.put(Coloumn_date,Date);
        cv.put(Coloumn_Transaction_Name,Transaction_Name);
        cv.put(Coloumn_amount,Amount);
        //cv.put(Colomn_operation,operation);
        Toast.makeText(context,"Successfully added",Toast.LENGTH_LONG).show();
        long flag;
        flag = db.insert(TABLE_NAME,null,cv);
        if(flag==-1){
            Toast.makeText(context,"Failed",Toast.LENGTH_LONG).show();
        }

        else Toast.makeText(context,"Successfully added",Toast.LENGTH_LONG).show();
    }
    void updateTotal(int amount){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(T_total,amount);
        db.update(TABLE_NAME1,cv,key+" = 'KEY'",null);
        System.out.println("hi");

    }
    Cursor readTotal(){
        String query="SELECT "+T_total+" FROM "+TABLE_NAME1+" WHERE "+key+" = "+"'KEY';";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=null;
        if(db!=null)
            cursor=db.rawQuery(query,null);
        return cursor;
    }
    Cursor getAllData(){
        String query="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=null;

        if(db!=null){
            cursor=db.rawQuery(query,null);
        }
        return cursor;
    }
}
