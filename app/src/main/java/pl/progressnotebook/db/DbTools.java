package pl.progressnotebook.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by piotr on 15.03.16.
 */
public class DbTools {

    public static long insert(SQLiteDatabase db, String tableName, String colName, String value){
        ContentValues values = new ContentValues();
        values.put(colName, value);
        long newRowId;
        newRowId = db.insert(
                tableName,
                null,
                values);

        return newRowId;
    }

    public static void delete(SQLiteDatabase db, String tableName, String colName, String value){
        String selection = colName + " LIKE ?";
        String[] selectionArgs = { value };
        db.delete(tableName, selection,selectionArgs);
    }

    public static void update(SQLiteDatabase db, String tableName, String colName, String oldValue,
                              String newValue){
        ContentValues values = new ContentValues();
        values.put(colName, newValue);
        String selection = colName + " LIKE ?";
        String[] selectionArgs = { oldValue };
        int count = db.update(
                tableName,
                values,
                selection,
                selectionArgs);
    }
}
