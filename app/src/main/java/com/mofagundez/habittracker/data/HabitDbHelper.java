package com.mofagundez.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mofagundez.habittracker.data.HabitContract.HabitEntry;


/**
 * Habit Tracker
 * Created by Mauricio on June 12, 2017
 * <p>
 * Udacity Android Basics Nanodegree
 * Project 9: Habit Tracker App
 */
public class HabitDbHelper extends SQLiteOpenHelper {

    /**
     * TABLE CREATION deliverable from the project rubric:
     * There exists a subclass of SQLiteOpenHelper that overrides onCreate() and onUpgrade().
     */

    // Define database name
    private static final String DATABASE_NAME = "tracker.db";
    // Current database version
    private static final int DATABASE_VERSION = 1;
    // SQL command do delete (drop) table
    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + HabitEntry.TABLE_NAME;
    // SQL command to create table
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + HabitEntry.TABLE_NAME + " ( " +
            HabitEntry._ID + HabitEntry.TYPE_INTEGER + HabitEntry.PRIMARY_KEY + ", " +
            HabitEntry.COLUMN_TITLE + HabitEntry.TYPE_TEXT + HabitEntry.NOT_NULL + ", " +
            HabitEntry.COLUMN_DESCRIPTION + HabitEntry.TYPE_TEXT + ", " +
            HabitEntry.COLUMN_PRIORITY + HabitEntry.TYPE_INTEGER + HabitEntry.NOT_NULL + ", " +
            HabitEntry.COLUMN_DATE + HabitEntry.TYPE_DATETIME + " )" +
            ";";


    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Execute SQL code and create table if does not exist in the devide
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop current table
        db.execSQL(SQL_DELETE_ENTRIES);
        // Create new table
        db.execSQL(SQL_CREATE_ENTRIES);
    }
}
