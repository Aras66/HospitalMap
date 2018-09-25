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
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qd = new SQLiteQueryBuilder();

        String[] sqlSelect = {"ID", "Budynek", "Pietro", "Opis", "Uwagi"};
        String tablename = "Jurasza";

        qd.setTables(tablename);
        Cursor cursor = qd.query(db, sqlSelect, null, null, null, null, null);
        List<Miejsca> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Miejsca miejsca = new Miejsca();
                miejsca.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                miejsca.setBudynek(cursor.getString(cursor.getColumnIndex("Budynek")));
                miejsca.setPietro(cursor.getInt(cursor.getColumnIndex("Pietro")));
                miejsca.setOpis(cursor.getString(cursor.getColumnIndex("Opis")));
                miejsca.setUwagi(cursor.getString(cursor.getColumnIndex("Uwagi")));

                result.add(miejsca);
            } while (cursor.moveToNext());
        }
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
        SQLiteQueryBuilder qd = new SQLiteQueryBuilder();

        String[] sqlSelect = {"ID", "Budynek", "Pietro", "Opis", "Uwagi"};
        String tableName = "Jurasza";

        qd.setTables(tableName);

        Cursor cursor = qd.query(db, sqlSelect, "Opis LIKE ?", new String[]{"%" + opis + "%"}, null, null, null);
        List<Miejsca> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Miejsca miejsca = new Miejsca();
                miejsca.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                miejsca.setBudynek(cursor.getString(cursor.getColumnIndex("Budynek")));
                miejsca.setPietro(cursor.getInt(cursor.getColumnIndex("Pietro")));
                miejsca.setOpis(cursor.getString(cursor.getColumnIndex("Opis")));
                miejsca.setUwagi(cursor.getString(cursor.getColumnIndex("Uwagi")));

                result.add(miejsca);
            } while (cursor.moveToNext());
        }
        return result;
    }
    /*    public getOpisBud(String budynek) {
            String opisBud

        ;
            SQLiteDatabase db = getReadableDatabase();
            SQLiteQueryBuilder qd = new SQLiteQueryBuilder();

            String[] sqlSelect = {"opisBudynku"};
            String tableName = "opisybud";

            qd.setTables(tableName);

            Cursor cursor = qd.query(db, sqlSelect, "nazwa = ?", new String[]{"%" + budynek + "%"}, null, null, null);
             result = new ArrayList<>();
            if (cursor.moveToFirst()) {
                do {
                    Miejsca miejsca = new Miejsca();
                    miejsca.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                    miejsca.setBudynek(cursor.getString(cursor.getColumnIndex("Budynek")));
                    miejsca.setPietro(cursor.getInt(cursor.getColumnIndex("Pietro")));
                    miejsca.setOpis(cursor.getString(cursor.getColumnIndex("Opis")));
                    miejsca.setUwagi(cursor.getString(cursor.getColumnIndex("Uwagi")));

                    result.add(miejsca);
                } while (cursor.moveToNext());
            }
            return result;
        }*/
}
