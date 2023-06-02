package com.example.pulseoneapp_v2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context,@Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "create table users(username text, email text, password text)";
        sqLiteDatabase.execSQL(qry1);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void register(String username, String email, String password){
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users",null, cv);
        db.close();
    }

    public int login(String username, String email, String password){
        int result=0;
        String str[] = new String[3];
        str[0] = username;
        str[1] = email;
        str[2] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = (Cursor) db.rawQuery("select * from users where username=? and email=? and password=?",str);
        if(c.moveToFirst()){
            result=1;

        }
        return result;
}
    private class Cursor {
        public boolean moveToFirst() {
            return true;
        }
    }
}
