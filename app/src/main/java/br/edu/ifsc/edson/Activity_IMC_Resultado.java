package br.edu.ifsc.edson;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_IMC_Resultado extends AppCompatActivity {

    Button buttonFinish;
    TextView tvResultado;
    TextView tvClassificacao;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc_resultado);

        buttonFinish=findViewById(R.id.buttonFinish);
        tvResultado=findViewById(R.id.tvIMC);
        imageView=findViewById(R.id.imageViewPerfil);
        tvClassificacao=findViewById(R.id.tvClassificacao);

        buttonFinish.setOnClickListener(v -> {
            finish();
        });

        Bundle bundle = getIntent().getExtras();
        float peso = bundle.getFloat("peso");
        float altura = bundle.getFloat("altura");

        float imc = peso/(altura*altura);

        tvResultado.setText(Float.toString(imc));

        if(imc<18.5){
            imageView.setImageResource(R.drawable.abaixopeso);
            tvClassificacao.setText("Abaixo do Peso");
        } else if(imc > 18.5 && imc < 24.9){
            imageView.setImageResource(R.drawable.normal);
            tvClassificacao.setText("Normal");
        } else if(imc > 25 && imc < 29.9){
            imageView.setImageResource(R.drawable.sobrepeso);
            tvClassificacao.setText("Sobrepeso");
        } else if(imc > 30 && imc < 34.9){
            imageView.setImageResource(R.drawable.obesidade1);
            tvClassificacao.setText("Obesidade 1");
        } else if(imc > 35 && imc < 39.9){
            imageView.setImageResource(R.drawable.obesidade2);
            tvClassificacao.setText("Obesidade 2");
        } else {
            imageView.setImageResource(R.drawable.obesidade3);
            tvClassificacao.setText("Obesidade 3");
        }
    }
}