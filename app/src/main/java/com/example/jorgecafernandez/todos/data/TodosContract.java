package com.example.jorgecafernandez.todos.data;

import android.provider.BaseColumns;

/**
 * Created by jorge.c.a.fernandez on 9/25/2017.
 */
/** Contract */
public final class TodosContract {
    /** todos table */
    public static final class TodosEntry implements BaseColumns {
        /** Table name */
        public static final String TABLE_NAME = "todos";
        /** Column (field) names */
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_TEXT = "text";
        public static final String COLUMN_CREATED = "created";
        public static final String COLUMN_EXPIRED = "expired";
        public static final String COLUMN_DONE = "done";
        public static final String COLUMN_CATEGORY = "category";
    }
    /** Category table */
    public static final class CategoriesEntry implements BaseColumns {
        /** Table name */
        public static final String TABLE_NAME = "categories";
        /** Column names */
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_DESCRIPTION = "description";
    }

}
