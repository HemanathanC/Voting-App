package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Connect extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Voting";
    private static final String VOTERS = "voters";
    private static final String CANDIDATES = "candidates";
    private static final String ADMIN="admin";
    private static final int DATABASE_Version = 1;
    private Context context;

    public Connect(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
        this.context=context;
    }

//create table
    @Override
    public void onCreate(SQLiteDatabase db) {

            String query1="CREATE TABLE "+VOTERS+ "(v_id	INTEGER,name TEXT,sex TEXT,age	INTEGER,status	TEXT,city	TEXT,v_status TEXT, Aadhar INTEGER,phone INTEGER,username TEXT, password TEXT);";
            String query2="CREATE TABLE "+CANDIDATES+"(name TEXT,symbol TEXT,sex TEXT,age INTEGER,city TEXT,count INTEGER);";
            String query3="CREATE TABLE "+ADMIN+"(name TEXT,username TEXT,password TEXT);";
            db.execSQL(query1);
            db.execSQL(query2);
            db.execSQL(query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + VOTERS);
        db.execSQL("DROP TABLE IF EXISTS " + CANDIDATES);
        db.execSQL("DROP TABLE IF EXISTS "+ ADMIN);
        onCreate(db);
    }

//check login
    public boolean check(String e1, String e2) {
        Boolean status=false;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * from " + VOTERS + " where username=\"" + e1 + "\" and password=\"" + e2 + "\"", null);

            if (cursor.moveToNext())
                status = true;
        }catch (Exception e) {e.printStackTrace();}

        return status;
    }
//Set Values
    public void setvalues(String uname, String pass) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from " + VOTERS + " where username=\"" + uname + "\" and password=\"" + pass + "\"", null);

        if (cursor.moveToNext()){

            Values value = new Values();

            value.setV_id(cursor.getString(0));
            value.setName(cursor.getString(1));
            value.setSex(cursor.getString(2));
            value.setAge(cursor.getString(3));
            value.setStatus(cursor.getString(4));
            value.setCity(cursor.getString(5));
            value.setV_status(cursor.getString(6));
            value.setA_no(cursor.getString(7));
            value.setP_no(cursor.getString(8));

        }
    }
//Get Symbols
    public Cursor getSymbols(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from "+CANDIDATES+" ",null);
        return cursor;
    }

//check voted
    public boolean checkvoted(String id, String a_no) {
        Boolean status=false;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from " + VOTERS + " where v_id=\"" + id + "\" and Aadhar=\"" + a_no + "\"", null);
        cursor.moveToNext();
        if (cursor.getString(6).equals("yes"))
            status = true;

            return status;
    }

//update v_status
    public Boolean update_v_status(String id, String sym) {
        Boolean status=false;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("UPDATE "+VOTERS+" SET v_status='yes' where v_id='"+id+"'",null);
        if(cursor.moveToNext())
            status = addcount(sym);
        return status;
    }

//add count of votes
    private Boolean addcount(String sym) {
        Boolean status = false;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("UPDATE "+CANDIDATES+" SET count="+this.getcount(sym)+"where symbol='"+sym+"'",null);
        if(cursor.moveToNext())
            status = true;
        return status;
    }

    @SuppressLint("Range")
    private int getcount(String sym) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from "+CANDIDATES+" where symbol='"+sym+"'",null);
        if(cursor.moveToNext())
            return cursor.getInt(5);
        return 0;
    }

    public Cursor result() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from "+CANDIDATES+" ORDER BY count DESC",null);
        return cursor;
    }

    public boolean check_admin(String uname, String pass) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * from " + ADMIN + " where username=\"" + uname + "\" and password=\"" + pass + "\"", null);

            if (cursor.moveToNext())
                return true;
        }catch (Exception e) {e.printStackTrace();}
        return false;
    }
    public boolean add_admin(){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", "Admin");
        values.put("username", "bittu");
        values.put("password", "hema");

        if(db.insert(ADMIN, null, values)>0)
            return true;
        db.close();

        return false;
    }

    public boolean c_addvalues(String name,String sym,String sex, String age,String city) {
        Boolean status=false;

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("symbol", sym);
        values.put("sex", sex);
        values.put("age", age);
        values.put("city", city);
        values.put("count", 0);

        if(db.insert(CANDIDATES, null, values)>0)
            status = true;
        db.close();

        return status;
    }

    public boolean addvalues(String name, String v_id, String sex, String age, String status, String city, String a_no, String p_no, String v_status, String uname, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("v_id", v_id);
        values.put("name", name);
        values.put("sex", sex);
        values.put("age", age);
        values.put("status", status);
        values.put("city", city);
        values.put("v_status", v_status);
        values.put("Aadhar", a_no);
        values.put("phone", p_no);
        values.put("username", uname);
        values.put("password", pass);

        if(db.insert(VOTERS, null, values)>0)
            return true;
        db.close();

        return false;
    }

    public boolean v_delete(String string) {

        SQLiteDatabase db=this.getWritableDatabase();
        if(db.delete(VOTERS,"v_id=?",new String[]{string})>0)
            return true;
        return false;
    }

    public boolean c_delete(String val) {
        SQLiteDatabase db=this.getWritableDatabase();
        if(db.delete(CANDIDATES,"name=?",new String[]{val})>0)
            return true;
        return false;
    }
}
