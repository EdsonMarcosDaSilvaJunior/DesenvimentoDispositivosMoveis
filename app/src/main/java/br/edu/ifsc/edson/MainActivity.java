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
    ArrayList<User> usersList;
    ArrayAdapter<User> adapter;

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
                editText.setText("");
            }
            carregarUser();
        });

        carregarUser();

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            User selecionado = usersList.get(position);
            mostrarOpcoes(selecionado);
            return true;
        });
    }
    public void carregarUser() {
        usersList.clear();
        Cursor cursor = database.rawQuery("SELECT * FROM users", null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex("id");
                int textoIndex = cursor.getColumnIndex("texto");

                if (idIndex != -1 && textoIndex != -1) {
                    int id = cursor.getInt(idIndex);
                    String texto = cursor.getString(textoIndex);

                    usersList.add(new User(id, texto));
                }
            } while (cursor.moveToNext());
        }

        if (cursor != null) cursor.close();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, usersList);
        listView.setAdapter(adapter);
    }

    private void mostrarOpcoes(User selecionado) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Opções para: " + selecionado.getNome());

        CharSequence[] opcoes = {"Editar", "Excluir"};

        builder.setItems(opcoes, (dialog, which) -> {
            if (which == 0) {
                mostrarDialogoEditar(selecionado);
            } else if (which == 1) {
                excluirSelecionado(selecionado.id);
            }
        });
        builder.show();
    }

    private void excluirSelecionado(int id) {
        database.delete("users", "id = ?", new String[]{String.valueOf(id)});
        carregarUser();
        android.widget.Toast.makeText(this, "Exluído com Sucesso",android.widget.Toast.LENGTH_SHORT).show();
    }
    private void mostrarDialogoEditar(User selecionado) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Editar: " + selecionado.getNome());

        EditText entrada = new EditText(this);
        entrada.setText(selecionado.nome);
        builder.setView(entrada);

        builder.setPositiveButton("Salvar", (dialog, which) -> {
            String novoNome = entrada.getText().toString();
            if(!novoNome.isEmpty()){
                ContentValues values = new ContentValues();
                values.put("name", novoNome);
                values.put("texto", novoNome);

                database.update("users", values, "id = ?", new String[]{String.valueOf(selecionado.id)});
                carregarUser();
                android.widget.Toast.makeText(this, "Editado com Sucesso",android.widget.Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());
        builder.show();
    }
}
