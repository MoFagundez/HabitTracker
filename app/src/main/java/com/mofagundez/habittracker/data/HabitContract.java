package com.mofagundez.habittracker.data;

import android.provider.BaseColumns;

/**
 * Habit Tracker
 * Created by Mauricio on June 12, 2017
 * <p>
 * Udacity Android Basics Nanodegree
 * Project 9: Habit Tracker App
 */
public class HabitContract {

    /**
     * TABLE DEFINITION deliverable from the project rubric:
     * There exists a contract class that defines name of table and constants.
     * Inside the contract class, there is an inner class for each table created
     */
    private HabitContract() {
    }

    public static final class HabitEntry implements BaseColumns {

        // Table name
        public static final String TABLE_NAME = "habits";

        // The _id field to index the table contents
        public static final String _ID = BaseColumns._ID;

        // The title of the habit
        public static final String COLUMN_TITLE = "title";

        // The description of the habit
        public static final String COLUMN_DESCRIPTION = "description";

        // The date & time it was created
        public static final String COLUMN_DATE = "date";

        // The priority of the habit
        public static final String COLUMN_PRIORITY = "priority";

        /**
         * Possible values for the priority field of the database.
         */
        public static final int PRIORITY_HIGH = 0;
        public static final int PRIORITY_MEDIUM = 1;
        public static final int PRIORITY_LOW = 2;

    }

}
