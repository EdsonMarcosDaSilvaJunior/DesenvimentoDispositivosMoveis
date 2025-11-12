package br.edu.ifsc.edson;

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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase database;
    EditText editText;
    Button saveButton;
    ListView ListSku;
    ArrayList<String> skuList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.EditTextSku);
        saveButton = findViewById(R.id.BtnSalvar);
        ListSku = findViewById(R.id.ListSku);

        skuList = new ArrayList<>();

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, skuList);

        ListSku.setAdapter(adapter);
        //path
        database = openOrCreateDatabase("app_database", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS skus ("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "sku VARCHAR, qtd VARCHAR)");


        saveButton.setOnClickListener(v -> {
            String sku = editText.getText().toString();
            if(!sku.isEmpty()){
                ContentValues contentValues = new ContentValues();

                contentValues.put("sku", sku);

                database.insert("skus",null,contentValues);
            }
            carregarSku();
        });


        ListSku.setOnItemLongClickListener((parent, view, position, id) -> {

            System.out.println("CHEGOU NO DELETE");

            database.delete("skus", "id = ?" ,new String[]{String.valueOf(id)});
            carregarSku();

            System.out.println("DELETOU");
            return true;
        });



        carregarSku();
    }
    public void carregarSku(){
        skuList.clear();
        Cursor cursor = database.rawQuery("SELECT * FROM skus", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            int columnIndex= cursor.getColumnIndex("sku");

            String s = cursor.getString(columnIndex);
            skuList.add(s);
            cursor.moveToNext();

        }
        adapter.notifyDataSetChanged();
    }

}