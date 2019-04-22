package com.mtg.projectevaluator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import javax.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "PROJECT_NAME";
    public static final String COL3 = "STUDENT_1_NAME";
    public static final String COL4 = "STUDENT_1_USN";
    public static final String COL5 = "STUDENT_2_NAME";
    public static final String COL6 = "STUDENT_2_USN";
    public static final String COL7 = "STUDENT_3_NAME";
    public static final String COL8 = "STUDENT_3_USN";
    public static final String COL9 = "STUDENT_4_NAME";
    public static final String COL10 = "STUDENT_4_USN";
    public static final String COL11 = "IDEA";
    public static final String COL12 = "IMPLEMENTATION";
    public static final String COL13 = "EXTRA";
    public static final String COL14 = "TOTAL";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                COL1 + " INTEGER PRIMARY KEY, " +
                COL2 + " TEXT, " +
                COL3 + " TEXT, " +
                COL4 + " TEXT, " +
                COL5 + " TEXT, " +
                COL6 + " TEXT, " +
                COL7 + " TEXT, " +
                COL8 + " TEXT, " +
                COL9 + " TEXT, " +
                COL10 + " TEXT, " +
                COL11 + " INTEGER, " +
                COL12 + " INTEGER, " +
                COL13 + " INTEGER, " +
                COL14 + " INTEGER" + ");";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
    }

    public boolean insertDataDetails(String col1, String col2, String col3,
                                     String col4, String col5, String col6,
                                     String col7, String col8, String col9,
                                     String col10) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, Integer.parseInt(col1));
        contentValues.put(COL2, col2);
        contentValues.put(COL3, col3);
        contentValues.put(COL4, col4);
        contentValues.put(COL5, col5);
        contentValues.put(COL6, col6);
        contentValues.put(COL7, col7);
        contentValues.put(COL8, col8);
        contentValues.put(COL9, col9);
        contentValues.put(COL10, col10);
        contentValues.put(COL11, 0);
        contentValues.put(COL12, 0);
        contentValues.put(COL13, 0);
        contentValues.put(COL14, 0);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public Cursor execQ(String q) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery(q, null);
    }

    public void updateCV(ContentValues cv, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_NAME, cv, "ID=" + id, null);
    }

    public Cursor getuser() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " ",
                null);
        return res;
    }
}
