package com.diegopereira.cartolafc.groups;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "TIMES";

    // Table columns
    public static final String _ID = "_id";
    public static final String NOME = "nome";
    public static final String NOME_CARTOLA = "nome_cartola";
    public static final String IMG = "img";
    public static final String ID = "id";



    // Database Information
    static final String DB_NAME = "GROUPS.DB";

    // database version
    static final int DB_VERSION = 1;

    /*
    // Creating table query
    private static final String CREATE_TABLE = "create table if not exists " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOME + " TEXT NOT NULL, " + NOME_CARTOLA + " TEXT NOT NULL, " + IMG + " TEXT NOT NULL, " + ID + " INTEGER);";

     */

    private static final String CREATE_TABLE = "create table if not exists " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ID + " INTEGER);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //onCreate(db);
    }

    /*
    public void insert(String nome, String nome_cartola, String img, int id) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(NOME, nome);
        contentValue.put(NOME_CARTOLA, nome_cartola);
        contentValue.put(IMG, img);
        contentValue.put(ID, id);

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, contentValue);
        db.close();
    }

     */

    public void insert(int id) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(ID, id);

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, contentValue);
        db.close();
    }

    public void update(long _id, String nome, String img, int id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOME, nome);
        contentValues.put(IMG, img);
        contentValues.put(ID, id);

        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_NAME, contentValues, _ID + " = " + _id, null);
        db.close();
    }

    public List<Input> getTimes() {
        List<Input> array_list = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_NAME+" ORDER BY CAST(nome AS TEXT) ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Input input = new Input();
                input.setNome(cursor.getString(1));
                input.setNomeCartola(cursor.getString(2));
                input.setUrlEscudoPng(cursor.getString(3));
                input.setTimeId(cursor.getInt(4));

                array_list.add(0, input);
            } while (cursor.moveToNext());
        }

            db.close();
            return array_list;
    }

    public List<Input> getIds() {
        List<Input> array_list = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_NAME+" ORDER BY CAST(id AS NUMBER) ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Input input = new Input();

                input.setTimeId(cursor.getInt(1));

                array_list.add(0, input);
            } while (cursor.moveToNext());
        }

        db.close();
        return array_list;
    }






    public Cursor fetch() {
        String sql = "SELECT * FROM " + TABLE_NAME+" ORDER BY cast(total AS REAL) DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID + " = " + id, null);
    }

}
