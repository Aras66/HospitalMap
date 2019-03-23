package com.example.arkadiusz.juraszamap.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.arkadiusz.juraszamap.Model.Miejsca;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME = "Jurasza.db";
    private static final int DB_VER = 1;

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public List<Miejsca> getMiejsca() {
        SQLiteDatabase db = this.getReadableDatabase();
        //SQLiteQueryBuilder qd = new SQLiteQueryBuilder();

        String selectQuery = "SELECT Jurasza.Budynek,Pietro,Opis,Uwagi,Opisbud FROM Jurasza INNER JOIN Budynki ON Budynki.Budynek = Jurasza.Budynek";
        Cursor cursor = db.rawQuery(selectQuery, null);

        List<Miejsca> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Miejsca miejsca = new Miejsca();
               // miejsca.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                miejsca.setBudynek(cursor.getString(cursor.getColumnIndex("Budynek")));
                miejsca.setPietro(cursor.getInt(cursor.getColumnIndex("Pietro")));
                miejsca.setOpis(cursor.getString(cursor.getColumnIndex("Opis")));
                miejsca.setUwagi(cursor.getString(cursor.getColumnIndex("Uwagi")));
                miejsca.setOpisBudynku(cursor.getString(cursor.getColumnIndex("Opisbud")));

                result.add(miejsca);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return result;
    }

    public List<String> getOpisy() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qd = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Opis"};
        String tablename = "Jurasza";

        qd.setTables(tablename);
        Cursor cursor = qd.query(db, sqlSelect, null, null, null, null, null);
        List<String> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                result.add(cursor.getString(cursor.getColumnIndex("Opis")));
            } while (cursor.moveToNext());
        }
        return result;
    }

    public List<Miejsca> getMiejscePoOpis(String opis) {

        SQLiteDatabase db = getReadableDatabase();

        String selectQuery = "SELECT Jurasza.Budynek,Pietro,Opis,Uwagi,Opisbud FROM Jurasza INNER JOIN Budynki ON Budynki.Budynek = Jurasza.Budynek and Opis like '%"+opis+"%'";
        Cursor cursor = db.rawQuery(selectQuery, null);

        List<Miejsca> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Miejsca miejsca = new Miejsca();
                //miejsca.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                miejsca.setBudynek(cursor.getString(cursor.getColumnIndex("Budynek")));
                miejsca.setPietro(cursor.getInt(cursor.getColumnIndex("Pietro")));
                miejsca.setOpis(cursor.getString(cursor.getColumnIndex("Opis")));
                miejsca.setUwagi(cursor.getString(cursor.getColumnIndex("Uwagi")));
                miejsca.setOpisBudynku(cursor.getString(cursor.getColumnIndex("Opisbud")));
                result.add(miejsca);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return result;
    }

    public List<String> getAllNamesBuilding(){
        String sqlSelect = "Budynek";
        String tableName = "Jurasza";
        List<String> labels = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  " +sqlSelect+ " FROM " +tableName+ " GROUP BY " +sqlSelect+ " ORDER BY " +sqlSelect+ "";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }
    public List<String> getAllLevelBuilding(String budynek){

        List<String> labels = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT Pietro FROM Jurasza WHERE Budynek = '"+budynek+"' GROUP BY Pietro ORDER BY Pietro";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }
    public  List<Miejsca> getOpisPoBudynku(String budynek, String pietro) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT Jurasza.Budynek,Pietro,Opis,Uwagi,Opisbud FROM Jurasza INNER JOIN Budynki ON Budynki.Budynek = Jurasza.Budynek where Jurasza.Budynek = '"+budynek+"' and Pietro = "+pietro+"";
        Cursor cursor = db.rawQuery(selectQuery, null);

        List<Miejsca> result = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                Miejsca miejsca = new Miejsca();
               // miejsca.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                miejsca.setBudynek(cursor.getString(cursor.getColumnIndex("Budynek")));
                miejsca.setPietro(cursor.getInt(cursor.getColumnIndex("Pietro")));
                miejsca.setOpis(cursor.getString(cursor.getColumnIndex("Opis")));
                miejsca.setUwagi(cursor.getString(cursor.getColumnIndex("Uwagi")));
                miejsca.setOpisBudynku(cursor.getString(cursor.getColumnIndex("Opisbud")));
                result.add(miejsca);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return result;
    }
}
