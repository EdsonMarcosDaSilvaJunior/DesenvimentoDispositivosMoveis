package br.edu.ifsc.edson;

import android.app.LocaleManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button button;
    ArrayList<String> nomes;
    ControllerPlaneta controllerPlaneta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView =findViewById(R.id.listView);
        button = findViewById(R.id.adicionarNome);
        controllerPlaneta = new ControllerPlaneta();

        PlanetaAdapter adapter = new PlanetaAdapter(this,R.layout.itemlista, controllerPlaneta.getPlanetas());


        button.setOnClickListener(v -> {});

        listView.setOnItemClickListener((parent, view, position, id) -> {});

        listView.setAdapter(adapter);

    }
}