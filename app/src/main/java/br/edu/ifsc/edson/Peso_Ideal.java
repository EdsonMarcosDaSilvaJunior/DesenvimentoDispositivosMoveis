package br.edu.ifsc.edson;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Peso_Ideal extends AppCompatActivity {

    Button buttonFinish;
    TextView tvPesoIdeal, tvPeso, tvNome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peso_ideal);

        buttonFinish=findViewById(R.id.buttonFinish);
        tvPeso=findViewById(R.id.tvPeso);
        tvNome=findViewById(R.id.tvNome);

        buttonFinish.setOnClickListener(v -> {
            finish();
        });

        Bundle bundle = getIntent().getExtras();
        float imc = bundle.getFloat("imc");
        String nome = bundle.getString("nome");
        float peso = bundle.getFloat("peso");
        float altura = bundle.getFloat("altura");


        float pesoIdeal;
        tvNome.setText(nome);
        if(imc < 20) {
            pesoIdeal = (21 * (altura * altura) - peso);
            tvPeso.setText("Você deverá Engordar: " + pesoIdeal  + " Kilos");
        } else if( imc > 25) {
            pesoIdeal = (24 * (altura * altura)) - peso;
            tvPeso.setText("Você deverá Emagrecer: " + pesoIdeal + " Kilos");
        } else {
            tvPeso.setText("Você está no peso Ideal!!");
        }


    }
}
