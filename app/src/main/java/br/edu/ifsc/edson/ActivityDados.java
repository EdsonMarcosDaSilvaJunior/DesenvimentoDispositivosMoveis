package br.edu.ifsc.edson;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityDados extends AppCompatActivity {
    Button buttonSave;
    EditText edValorHora, edPorcentagemLucro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados);
        buttonSave=findViewById(R.id.buttonSave);
        edValorHora=findViewById(R.id.editTextValorHora);
        edPorcentagemLucro=findViewById(R.id.editTextPorcentagemLucro);

        buttonSave.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

            Float valorHora = Float.parseFloat(edValorHora.getText().toString());
            Float porcetagemLucro = Float.parseFloat(edPorcentagemLucro.getText().toString());

            intent.putExtra("valorHora", valorHora);
            intent.putExtra("porcetagemLucro", porcetagemLucro);
            startActivity(intent);
        });
    }
}