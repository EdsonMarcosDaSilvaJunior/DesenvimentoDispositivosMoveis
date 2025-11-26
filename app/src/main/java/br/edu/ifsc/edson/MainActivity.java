package br.edu.ifsc.edson;

import android.app.LocaleManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button buttonConfig, buttonCalcular;
    EditText edValorMaterial, edTempo, edTotal;
    float valorHora, porcentagemLucro;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (!sharedPreferences.contains("config")) {
            editor.putBoolean("config", false);
            editor.apply();
        }else{
            editor.putBoolean("config", true);
        }

        buttonConfig = findViewById(R.id.buttonConfig);
        buttonCalcular=findViewById(R.id.buttonCalcular);

        edValorMaterial=findViewById(R.id.editTextMaterial);
        edTempo=findViewById(R.id.editTextTempo);
        edTotal=findViewById(R.id.editTextTotal);

        buttonConfig.setOnClickListener(v-> {
            Intent intent = new Intent(getApplicationContext(), ActivityDados.class);
            startActivity(intent);
        });


        if(!sharedPreferences.contains("config")){
            buttonConfig.performClick();
        } else {
            Bundle bundle = getIntent().getExtras();
            valorHora = bundle.getFloat("valorHora");
            porcentagemLucro = bundle.getFloat("porcetagemLucro");
        }

        buttonCalcular.setOnClickListener(v ->{
            Float valorMaterial = Float.parseFloat(edValorMaterial.getText().toString());
            Float tempo = Float.parseFloat(edTempo.getText().toString());

            float total = 0;

            edTotal.setText(Float.toString(total));
        });
    }

    /*@Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        if(!sharedPreferences.contains("config")){
            buttonConfig.performClick();
        };
    }*/
}