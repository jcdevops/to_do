package com.example.jorgecafernandez.todos;

/** Import libraries */
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.jorgecafernandez.todos.data.DatabaseHelper;
import com.example.jorgecafernandez.todos.data.TodosContract.TodosEntry;

/** TodoListActivity class */
public class TodoListActivity extends AppCompatActivity {
    String[] itemname ={
            "Get theatre tickets",
            "Order pizza for tonight",
            "Buy groceries",
            "Running session at 19.30",
            "Call Uncle Sam"
    };

    /** readData method */
    private void readData(){
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] projection = {TodosEntry.COLUMN_TEXT,
                TodosEntry.COLUMN_CREATED,
                TodosEntry.COLUMN_EXPIRED,
                TodosEntry.COLUMN_DONE,
                TodosEntry.COLUMN_CATEGORY};

    /** Selection variable(WHERE COLUMN_CATEGORY) & SelectionArg(Category value) */
        String selection = TodosEntry.COLUMN_CATEGORY + " = ?";
        String[] selectionArg = {"1"};
        /** Execute query & log the count */
        Cursor c = db.query(TodosEntry.TABLE_NAME,
                projection, selection, selectionArg, null, null, null);
        int i = c.getCount();
        Log.d("Record Count", String.valueOf(i));

        /** List table contents */
        String rowContent = "";
        while (c.moveToNext()){
            for (i=0; i<4; i++){
                rowContent += c.getString(i) + " - ";
            }
            Log.i("Row" + String.valueOf(c.getPosition()), rowContent);
            rowContent = "";
        }
        c.close();
    }

    /** onCreate method of TodoListActivity */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //DatabaseHelper helper = new DatabaseHelper(this);
        //SQLiteDatabase db = helper.getReadableDatabase();
        //CreateTodo();
        readData();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ListView lv=(ListView) findViewById(R.id.lvTodos);

        /** Adds the custom layout */
        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.todo_list_item,
                R.id.tvNote,itemname));

        /** Adds the click event to the listView, reading the content */
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Intent intent = new Intent(TodoListActivity.this, TodoActivity.class);
                String content= (String) lv.getItemAtPosition(pos);
                intent.putExtra("Content", content);
                startActivity(intent);
            }
        });
        /** FAB Button */
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /** Private Method to insert data */
    private void CreateTodo(){
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        /** Constant query with the query to insert */
        String query = "INSERT INTO todos ("
                + TodosEntry.COLUMN_TEXT + ","
                + TodosEntry.COLUMN_CATEGORY + ","
                + TodosEntry.COLUMN_CREATED + ","
                + TodosEntry.COLUMN_EXPIRED + ","
                + TodosEntry.COLUMN_DONE + ")"
                + " VALUES (\"Go to the gym\" , 1, \"2016-01-01\", \"\",0)";

        /** Exec query */
        db.execSQL(query);

        /** ContentValue Instance opened */
        ContentValues values = new ContentValues();

        /** Call put method to write in DB */
        values.put(TodosEntry.COLUMN_TEXT, "Call Mr Bean");
        values.put(TodosEntry.COLUMN_CATEGORY, 1);
        values.put(TodosEntry.COLUMN_CREATED, "2016-01-02");
        values.put(TodosEntry.COLUMN_DONE, 0);

        /** Variable todo_id contain the row id inserted */
        long todo_id = db.insert(TodosEntry.TABLE_NAME, null, values);
    }
}
