package br.edu.ifsc.edson;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    Button button;
    EditText edPeso;
    EditText edAltura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //Associar os objetos declarados e inicializados a partir do XML com variaveis locais
        button=findViewById(R.id.Button);
        edPeso=findViewById(R.id.EdPeso);
        edAltura=findViewById(R.id.EdAltura);

        button.setOnClickListener(v -> {
        Intent intent = new Intent(getApplicationContext(), Activity_IMC_Resultado.class);

        Float peso = Float.parseFloat(edPeso.getText().toString());
        Float altura = Float.parseFloat(edAltura.getText().toString());

        intent.putExtra("peso", peso);
        intent.putExtra("altura", altura);
        startActivity(intent);
        });

    }

}