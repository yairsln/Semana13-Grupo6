package com.jovanovic.stefan.Semana_13_Grupo6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.service.autofill.FillEventHistory;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String BDNAME="login.db";
    public DBHelper(Context context) {
        super(context, "login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
  db.execSQL("Create Table usuarios(usuario TEXT PRIMARY KEY, contrasena TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
db.execSQL("Drop Table if  exists usuarios");
    }

    public boolean insertarDatos(String usuario, String contrasena){
SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("usuario",usuario);
        values.put("contrasena",contrasena);
        long result = db.insert("usuarios",null,values);
        if(result==1) return false;
        else
            return  true;

    }
    public boolean checkusuario(String usuario){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from usuarios where usuario=?",new String[]{usuario});
        if(cursor.getCount()>0){
        return  true;
        }else
            return  false;



    }
    public boolean checkusernamepassword(String usuario,String contrasena){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from usuarios where usuario=? and contrasena=?  ", new String[]{usuario,contrasena});
        if(cursor.getCount()>0)
            return  true;
        else
            return  false;



    }

}
