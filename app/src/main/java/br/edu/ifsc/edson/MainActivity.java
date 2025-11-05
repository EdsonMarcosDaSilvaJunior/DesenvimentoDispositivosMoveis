package br.edu.ifsc.edson;

import android.app.LocaleManager;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase database;
    EditText editText;
    Button saveButton;
    ListView listView;
    ArrayList<String> usersList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextText);
        saveButton = findViewById(R.id.BtnSalvar);
        listView = findViewById(R.id.ListName);

        usersList = new ArrayList<>();


        //path
        database = openOrCreateDatabase("app_database", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS users ("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "name VARCHAR, texto VARCHAR)");


        saveButton.setOnClickListener(v -> {
            String texto = editText.getText().toString();
            if(!texto.isEmpty()){
                ContentValues contentValues = new ContentValues();

                contentValues.put("name", texto);
                contentValues.put("texto", texto);

                database.insert("users",null,contentValues);
            }
            carregarUser();
        });

        carregarUser();
    }
    public void carregarUser(){
        usersList.clear();
        Cursor cursor = database.rawQuery("SELECT * FROM users", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            int columnIndex= cursor.getColumnIndex("texto");

            String s = cursor.getString(columnIndex);
            usersList.add(s);
            cursor.moveToNext();
        }

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, usersList);
        listView.setAdapter(adapter);

    }
}