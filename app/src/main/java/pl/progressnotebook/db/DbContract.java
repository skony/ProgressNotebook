package pl.progressnotebook.db;

import android.provider.BaseColumns;

/**
 * Created by piotr on 08.03.16.
 */
public final class DbContract {

    public DbContract() {}

    public static abstract class Exercises implements BaseColumns {

        public static final String TABLE_NAME = "exercises";
        public static final String COLUMN_NAME_NAME = "name";
        /*public static final String COLUMN_NAME_LOAD = "load";
        public static final String COLUMN_NAME_SERIES = "series";*/

        protected static final String TEXT_TYPE = " TEXT";
        protected static final String COMMA_SEP = ",";
        protected static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME_NAME + TEXT_TYPE +
                        /*COLUMN_NAME_LOAD + TEXT_TYPE + COMMA_SEP +
                        COLUMN_NAME_SERIES + TEXT_TYPE + COMMA_SEP +*/
                " )";

        protected static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static abstract class WorkoutSets implements BaseColumns {

        public static final String TABLE_NAME = "workout_sets";
        public static final String COLUMN_NAME_NAME = "name";

        protected static final String TEXT_TYPE = " TEXT";
        protected static final String COMMA_SEP = ",";
        protected static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME_NAME + TEXT_TYPE +
                        " )";

        protected static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static abstract class ExercisesWorkoutSets implements BaseColumns {

        public static final String TABLE_NAME = "exercises_workout_sets";
        public static final String COLUMN_NAME_EXERCISE_ID = "exercise_id";
        public static final String COLUMN_NAME_WORKOUT_SET_ID = "workout_set_id";
        public static final String TABLE_NAME_FOREIGN_KEY_1 = "exercises";
        public static final String COLUMN_NAME_FOREIGN_KEY_1 = "id";
        public static final String TABLE_NAME_FOREIGN_KEY_2 = "workout_sets";
        public static final String COLUMN_NAME_FOREIGN_KEY_2 = "id";

        protected static final String TEXT_TYPE = " TEXT";
        protected static final String COMMA_SEP = ",";
        protected static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME_EXERCISE_ID + TEXT_TYPE + COMMA_SEP +
                        COLUMN_NAME_WORKOUT_SET_ID + TEXT_TYPE + COMMA_SEP +
                        "FOREIGN KEY(" + COLUMN_NAME_EXERCISE_ID + ") REFERENCES " + TABLE_NAME_FOREIGN_KEY_1 + "(" + COLUMN_NAME_FOREIGN_KEY_1 + ")" + COMMA_SEP +
                        "FOREIGN KEY(" + COLUMN_NAME_WORKOUT_SET_ID + ") REFERENCES " + TABLE_NAME_FOREIGN_KEY_2 + "(" + COLUMN_NAME_FOREIGN_KEY_2 + ")" +
                        " )";

        protected static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
