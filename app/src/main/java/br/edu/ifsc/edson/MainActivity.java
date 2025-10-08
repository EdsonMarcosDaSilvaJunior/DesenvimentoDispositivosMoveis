package br.edu.ifsc.edson;

import android.app.LocaleManager;
import android.os.Bundle;
import android.view.View;
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

    ListView listView;
    EditText editTextNome;
    Button button;
    ArrayList<String> nomes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView =findViewById(R.id.listView);
        editTextNome = findViewById(R.id.editTextNome);
        button = findViewById(R.id.adicionarNome);
        nomes = new ArrayList<>(){{
            add("Edson");
            add("Augusto");
        }};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                nomes
        );

        button.setOnClickListener(v -> {
            nomes.add(editTextNome.getText().toString());
            editTextNome.setText("");
            adapter.notifyDataSetChanged();
        });
        listView.setAdapter(adapter);

    }
}