package com.diegopereira.cartolafc.league;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;

import com.diegopereira.cartolafc.teste.TimePontos;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "TIME";
    public static final String TABLE_NAME2 = "PARCIAIS";

    // Table columns
    public static final String _ID = "_id";
    public static final String TIME_ID = "time_id";
    public static final String ATLETA_ID = "atleta_id";
    public static final String CAP_ID = "capitao_id";
    public static final String APELIDO = "apelido";
    public static final String CLUBEID = "clubeid";
    public static final String POSICAO = "posicao";

    // Table2 columns
    public static final String ID = "id";
    public static final String TIME2_ID = "time_id";
    public static final String SYMBOL2 = "symbol";
    public static final String NOME2 = "apelido";
    public static final String PATRIMONIO2 = "patrimonio";
    public static final String PONTOS_2_PARCIAIS = "parciais";
    public static final String PONTOS_2_TOTAL = "total";


    // Database Information
    static final String DB_NAME = "TESTE.DB";

    // database version
    static final int DB_VERSION = 4;

    // Creating table query
    private static final String CREATE_TABLE = "create table if not exists " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TIME_ID + " INTEGER, " + ATLETA_ID + " INTEGER, " + CAP_ID
            + " INTEGER, " + APELIDO + " TEXT NOT NULL, " + CLUBEID + " INTEGER, " + POSICAO + " INTEGER);";

    private static final String CREATE2_TABLE = "create table if not exists " + TABLE_NAME2 + "(" + ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TIME2_ID + " INTEGER, " + SYMBOL2 + " TEXT NOT NULL, " + NOME2
            + " TEXT NOT NULL, " + PATRIMONIO2 + " REAL, " + PONTOS_2_PARCIAIS + " REAL, " + PONTOS_2_TOTAL + " REAL);";

    private static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE2_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //onCreate(db);
    }


    public void insert(int time_id, int atleta_id, int cap_id, String apelido, int clubeid, int posicao) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(TIME_ID, time_id);
        contentValue.put(ATLETA_ID, atleta_id);
        contentValue.put(CAP_ID, cap_id);
        contentValue.put(APELIDO, apelido);
        contentValue.put(CLUBEID, clubeid);
        contentValue.put(POSICAO, posicao);

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, contentValue);
        db.close();
    }

    public void insert_parciais(int time2_id, String symbol, String nome, double patrimonio, double parciais, double total) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(TIME2_ID, time2_id);
        contentValue.put(SYMBOL2, symbol);
        contentValue.put(NOME2, nome);
        contentValue.put(PATRIMONIO2, patrimonio);
        contentValue.put(PONTOS_2_PARCIAIS, parciais);
        contentValue.put(PONTOS_2_TOTAL, total);

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME2, null, contentValue);
        db.close();
    }

    public void update_parciais(long id, int time2_id, String symbol, String nome, double patrimonio, double parciais, double total) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(TIME2_ID, time2_id);
        contentValue.put(SYMBOL2, symbol);
        contentValue.put(NOME2, nome);
        contentValue.put(PATRIMONIO2, patrimonio);
        contentValue.put(PONTOS_2_PARCIAIS, parciais);
        contentValue.put(PONTOS_2_TOTAL, total);

        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_NAME2, contentValue, ID + " = " + id, null);
        db.close();
    }

    public void update(long _id, int time_id, int atleta_id, int cap_id,  String apelido, int clubeid, int posicao) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TIME_ID, time_id);
        contentValues.put(ATLETA_ID, atleta_id);
        contentValues.put(CAP_ID, cap_id);
        contentValues.put(APELIDO, apelido);
        contentValues.put(CLUBEID, clubeid);
        contentValues.put(POSICAO, posicao);

        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_NAME, contentValues, _ID + " = " + _id, null);
        db.close();
    }

    public List<Teste> getList() {
        List<Teste> array_list = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Teste newTeste = new Teste();
                newTeste.setTime_id(cursor.getInt(1));
                newTeste.setAtleta_id(cursor.getInt(2));
                newTeste.setCapitaoId(cursor.getInt(3));
                newTeste.setApelido(cursor.getString(4));
                newTeste.setClube_id(cursor.getInt(5));
                newTeste.setPosicao_id(cursor.getInt(6));

                array_list.add(0, newTeste);
            } while (cursor.moveToNext());
        }

        db.close();
        return array_list;
    }

    public List<Times> getParciaisList() {
        List<Times> array_list = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_NAME2 + " ORDER BY cast(total AS REAL) ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Times times = new Times();
                times.setTime_id(cursor.getInt(1));
                times.setUrl_escudo_png(cursor.getString(2));
                times.setNome(cursor.getString(3));
                times.setPatrimonio(cursor.getDouble(4));
                times.setPontosParciais(cursor.getDouble(5));
                times.setTotal(cursor.getDouble(6));

                array_list.add(0, times);
            } while (cursor.moveToNext());
        }

        db.close();
        return array_list;
    }

    public Cursor fetch() {
        String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY cast(time_id AS INTEGER) DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                cursor.getInt(1);
                cursor.getInt(2);
                cursor.getInt(3);
                cursor.getString(4);
                cursor.getInt(5);
                cursor.getInt(6);


            } while (cursor.moveToNext());
        }
        db.close();
        return cursor;
    }

    public boolean columnExists(String value) {
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT EXISTS (SELECT * FROM TIME WHERE time_id='" + value + "' LIMIT 1)";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();

        // cursor.getInt(0) is 1 if column with value exists
        if (cursor.getInt(0) == 1) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    public void delete() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "NOME " + "LIKE ?", new String[]{"nome"});
    }


    public void deleteTable() {
        SQLiteDatabase db = this.getWritableDatabase();

        if (db == null || !db.isOpen())
            db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS TIME");
    }

}
