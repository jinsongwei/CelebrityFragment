package com.williamjin.celebrityfragment.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static com.williamjin.celeberity.model.DatabaseMeta.*;

/**
 * Created by william on 12/4/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String databaseName = "celebrity_database";
    public static final int version = 1;

    public static final String CREATE_CELEBRITY_TABLE =
            "CREATE TABLE " + CelebrityEntry.TABLE_NAME + "( " +
                    CelebrityEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CelebrityEntry.NAME + " TEXT, " +
                    CelebrityEntry.GENDER + " CHAR(1), " +
                    CelebrityEntry.TYPE + " TEXT, " +
                    CelebrityEntry.FAVORITE + " BOOLEAN );";

    public DatabaseHelper(Context context) {
        super(context, databaseName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_CELEBRITY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long addCelebrity(Celebrity c) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CelebrityEntry.NAME, c.getName());
        cv.put(CelebrityEntry.GENDER, String.valueOf(c.getGender()));
        cv.put(CelebrityEntry.TYPE, c.getType());
        cv.put(CelebrityEntry.FAVORITE, c.getFavorite());
        return db.insert(CelebrityEntry.TABLE_NAME, null, cv);
    }

    public long removeCelebrity(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CelebrityEntry.NAME, name);
        return db.delete(CelebrityEntry.TABLE_NAME, "name = '" + name + "';", null);
    }

    public int modifyCelebrity(Celebrity c) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (c.getId() == -1) {
            return -1;
        }
        cv.put(CelebrityEntry.NAME, c.getName());
        cv.put(CelebrityEntry.GENDER, String.valueOf(c.getGender()));
        cv.put(CelebrityEntry.TYPE, c.getType());
        cv.put(CelebrityEntry.FAVORITE, c.getFavorite());
        String whereClause = "id = " + c.getId();
        return db.update(CelebrityEntry.TABLE_NAME, cv, whereClause, null);
    }

    public Celebrity getCelebrity(Integer id, String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String command = null;
        if (id != null) {
            command = "SELECT * FROM " + CelebrityEntry.TABLE_NAME + " WHERE id = " + id + ";";
        } else if (name != null) {
            command = "SELECT * FROM " + CelebrityEntry.TABLE_NAME + " WHERE name = '" + name + "';";
        } else
            return null;
        Cursor cursor = db.rawQuery(command, null);
        if (cursor.moveToFirst()) {
            Boolean isFavorite = cursor.getInt(4) > 0;
            return new Celebrity(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2).charAt(0),
                    cursor.getString(3),
                    isFavorite
            );
        } else {
            return null;
        }
    }

    public List<Celebrity> listCelebrities() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Celebrity> celebrityList = new ArrayList<>();
        String command = "SELECT * FROM " + CelebrityEntry.TABLE_NAME;
        Cursor cursor = db.rawQuery(command, null);
        if (cursor.moveToFirst()) {
            do {
                Boolean isFavorite = cursor.getInt(4) > 0;
                celebrityList.add(new Celebrity(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2).charAt(0),
                        cursor.getString(3),
                        isFavorite
                ));
            } while (cursor.moveToNext());
        }
        return celebrityList;
    }

    public List<Celebrity> getFavorites() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Celebrity> celebrityList = new ArrayList<>();
        String command = "SELECT * FROM " + CelebrityEntry.TABLE_NAME + " WHERE favorite = " + 1 + ";";
        Cursor cursor = db.rawQuery(command, null);
        if (cursor.moveToFirst()) {
            do {
                Boolean isFavorite = cursor.getInt(4) > 0;
                celebrityList.add(new Celebrity(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2).charAt(0),
                        cursor.getString(3),
                        isFavorite
                ));
            } while (cursor.moveToNext());
        }
        return celebrityList;
    }
}
