package com.mofagundez.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mofagundez.habittracker.data.HabitDbHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.mofagundez.habittracker.data.HabitContract.HabitEntry;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create database helper that will provide access to database
        mDbHelper = new HabitDbHelper(this);

        // Call method to insert dummy data into de database
        insertData();

        // Call method to read the previously inserted dummy data
        readData();
    }

    /**
     * DATA READING deliverable from the Project Rubric: single read method
     * that returns a Cursor object.
     */
    private void readData() {
        // Create new or open already existent database
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Create and define a projection of what columns will be read from the database.
        // I chose to declare all of them on purpose for training/practising only.
        String projection[] = {
                HabitEntry._ID,
                HabitEntry.COLUMN_TITLE,
                HabitEntry.COLUMN_DESCRIPTION,
                HabitEntry.COLUMN_PRIORITY,
                HabitEntry.COLUMN_DATE
        };

        // Define sort order by ascending priority
        String sortOrder =
                HabitEntry.COLUMN_PRIORITY + " ASC";

        // Perform a query on the pets table
        Cursor cursor = db.query(HabitEntry.TABLE_NAME, projection, null, null, null, null, sortOrder);

        try {
            // Figure out index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int titleColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_TITLE);
            int descriptionColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_DESCRIPTION);
            int priorityColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_PRIORITY);
            int dateColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_DATE);

            // Iterate through all the returned rows in the cursor object
            while (cursor.moveToNext()) {
                int currentId = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(titleColumnIndex);
                String currentDescription = cursor.getString(descriptionColumnIndex);
                int currentPriority = cursor.getInt(priorityColumnIndex);
                String currentDate = cursor.getString(dateColumnIndex);
            }
        } finally {
            // Close the cursor when we're done with it to release memory and avoid leaks
            cursor.close();
        }

    }

    /**
     * DATA INSERTION deliverable from the Project Rubric: single insert method
     * that adds at least two values of two different datatypes into de database
     */
    private void insertData() {
        // Create new or open already existent database
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create ContentValues object to contain data to be inserted in the database
        ContentValues values = new ContentValues();
        // Put dummy value to COLUMN_TITLE key
        values.put(HabitEntry.COLUMN_TITLE, "Study my Nanodegree");
        // Put dummy value to COLUMN_DESCRIPTION key
        values.put(HabitEntry.COLUMN_DESCRIPTION, "Study hard everyday as much as I can for my new career!");
        // Put dummy (PRIORITY_HIGH) value to COLUMN_PRIORITY key
        values.put(HabitEntry.COLUMN_PRIORITY, HabitEntry.PRIORITY_HIGH);
        // Put current device date and time value to COLUMN_DATE key
        values.put(HabitEntry.COLUMN_DATE, getDateTime());

        // Insert dummy data into the database
        db.insert(HabitEntry.TABLE_NAME, null, values);
    }

    /**
     * This method defines the date and time of creation of the habit to be tracked.
     */
    private String getDateTime() {
        // Define a date format of "yyyy-MM-dd HH:mm:ss" (i.e.: 2017-06-12 11:05:23) and get date & time from local device
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        // Return the formatted date
        return dateFormat.format(date);
    }


}
