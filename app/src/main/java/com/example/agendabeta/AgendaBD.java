package com.example.agendabeta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AgendaBD extends SQLiteOpenHelper {

    private static final String NOMBRE_BD = "agendabeta.db";
    private static final int VERSION_BD = 2;
    private static final String TABLA_CONTACTO="CREATE TABLE CONTACTO(ID INTEGER PRIMARY KEY , NOMBRE TEXT, CORREO TEXT, TELEFONO TEXT UNIQUE, DIRECCION TEXT)";



    public AgendaBD(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLA_CONTACTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLA_CONTACTO);
        sqLiteDatabase.execSQL(TABLA_CONTACTO);
        onCreate(sqLiteDatabase);
    }

    public void agregarContacto(Integer id, String nombre,String correo, Integer telefono, String direccion){
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null){
            ContentValues valores = new ContentValues();
            valores.put("ID", id);
            valores.put("NOMBRE", nombre);
            valores.put("CORREO", correo);
            valores.put("TELEFONO", telefono);
            valores.put("DIRECCION", direccion);
            bd.insert("CONTACTO", null, valores);
            bd.close();
        }
    }

    public List<ContactoModelo> mostrarContactos(){
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM CONTACTO", null);
        List<ContactoModelo> contactos = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                contactos.add(new ContactoModelo(Integer.valueOf(cursor.getString(0)),cursor.getString(1),cursor.getString(2),Integer.valueOf(cursor.getString(3)),cursor.getString(4)));
            } while (cursor.moveToNext());
        }
        return contactos;
    }

    public void buscarContacto(ContactoModelo contacto, Integer id){
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM CONTACTO WHERE ID='"+id+"' ", null);
        if (cursor.moveToFirst()){
            do {
                contacto.setNombre(cursor.getString(1));
                contacto.setCorreo(cursor.getString(2));
                contacto.setTelefono(Integer.valueOf(cursor.getString(3)));
                contacto.setDireccion(cursor.getString(4));
            } while (cursor.moveToNext());
        }
    }

    public void editarContacto(Integer id, String nombre, String correo, Integer telefono, String direccion) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            ContentValues valores = new ContentValues();
            valores.put("NOMBRE", nombre);
            valores.put("CORREO", correo);
            valores.put("TELEFONO", telefono);
            valores.put("DIRECCION", direccion);
            String whereClause = "ID = ?";
            String[] whereArgs = {String.valueOf(id)};
            bd.update("CONTACTO", valores, whereClause, whereArgs);
            bd.close();
        }
    }

    public void eliminarContacto(Integer id) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            String whereClause = "ID = ?";
            String[] whereArgs = {String.valueOf(id)};
            bd.delete("CONTACTO", whereClause, whereArgs);
            bd.close();
        }
    }



}
