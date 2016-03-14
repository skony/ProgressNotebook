package pl.progressnotebook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by piotr on 08.03.16.
 */
public class AppDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ProgressNotebook.db";

    public AppDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DbContract.Exercises.SQL_CREATE_ENTRIES);
        sqLiteDatabase.execSQL(DbContract.WorkoutSets.SQL_CREATE_ENTRIES);
        sqLiteDatabase.execSQL(DbContract.ExercisesWorkoutSets.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DbContract.Exercises.SQL_DELETE_ENTRIES);
        sqLiteDatabase.execSQL(DbContract.WorkoutSets.SQL_DELETE_ENTRIES);
        sqLiteDatabase.execSQL(DbContract.ExercisesWorkoutSets.SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
}
