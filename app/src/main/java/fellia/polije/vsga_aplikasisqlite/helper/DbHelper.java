package fellia.polije.vsga_aplikasisqlite.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 2;
    static  final String DATABASE_NAME = "digitalent.db";
    public static final String TABLES_SQLite = "sqlite";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADDRESS = "address";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + TABLE_SQLite + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY autoincrement, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_ADDRESS + " TEXT NOT NULL " +
                ")";
        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLES_SQLite);
        onCreate(db);
    }

    public ArrayList<HashMap<String, String>> getAllData(){
        ArrayList<HashMap<String, String>> wordList;
        String selecQuery = "SELECT * FROM " + TABLES_SQLite;
        SQLiteDatabase database = this.getWritetableDatabase();
        Cursor cursor = database.rawQuery(selecQuery, null);
        if(cursor.moveToFirst()){
            do{
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(COLUMN_ID, cursor.getString(0));
                map.put(COLUMN_NAME, cursor.getString(1));
                map.put(COLUMN_ADDRESS, cursor.getString(0));
                wordList.add(map);
            } while (cursor.moveToNext());
        }

        Log.e("select sqlite", " " + wordList );

        database.close();
        return wordList;
    }

    public void insert (String name, String address){
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLES_SQLite + " (name, address) " +
                "VALUES ('" + name + "', '" + address + "')";

        Log.e("insert sqlite", " " + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public void update (int id, String name, String addreess){
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLES_SQLite + "SET "
                + COLUMN_NAME + "='" + name + "', "
                + COLUMN_ADDRESS + "='" + addreess + "'"
                + " WHERE " + COLUMN_ID + "=" + "'" + id + "'";
        Log.e("update sqlite", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void delete(int id){
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "DELETE FROM " + TABLES_SQLite + " WHERE " + COLUMN_ID + "=" + id + "'";
        Log.e("update sqlite", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }
}
