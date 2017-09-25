package com.example.jorgecafernandez.todos.data;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.jorgecafernandez.todos.data.TodosContract.CategoriesEntry;
import com.example.jorgecafernandez.todos.data.TodosContract.TodosEntry;

/**
 * Created by jorge.c.a.fernandez on 9/25/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    /**
     *  Constant for db declaration name and version
     */
    private static final String DATABASE_NAME = "todos.db";
    private static final int DATABASE_VERSION = 1;
    /**
     * Constant with the SQL query to create table "TABLE_CATEGORIES_CREATE"
     */
    private static final String TABLE_CATEGORIES_CREATE =
            "CREATE TABLE " + CategoriesEntry.TABLE_NAME + " (" +
                    CategoriesEntry._ID + " INTERGER PRIMARY KEY, " +
                    CategoriesEntry.COLUMN_DESCRIPTION + " TEXT " +
                    ")";
    /**
     * SQL query to create table "TABLE_TODOS_CREATE"
     */
    private static final String TABLE_TODOS_CREATE =
            "CREATE TABLE " + TodosEntry.TABLE_NAME + " (" +
                   TodosEntry._ID + " INTEGER PRIMARY KEY, " +
                    TodosEntry.COLUMN_TEXT + " TEXT, " +
                    TodosEntry.COLUMN_CREATED + " TEXT default CURRENT_TIMESTAMP, " +
                    TodosEntry.COLUMN_EXPIRED + " TEXT, " +
                    TodosEntry.COLUMN_DONE + " INTEGER, " +
                    TodosEntry.COLUMN_CATEGORY + " INTEGER, " +
                    " FOREIGN KEY("+ TodosEntry.COLUMN_CATEGORY + ") REFERENCES " + CategoriesEntry.TABLE_NAME
                    + "(" + CategoriesEntry._ID +") " + ")";
    /**
     * Constructor : context, name, factory, version
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    /**
     * Method : onCreate(called when database is created for first time)
     * Method : onUpgrade(called when we want remove tables or columns)
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CATEGORIES_CREATE);
        db.execSQL(TABLE_TODOS_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TodosEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXIST " + CategoriesEntry.TABLE_NAME);
        onCreate(db);
    }
}
