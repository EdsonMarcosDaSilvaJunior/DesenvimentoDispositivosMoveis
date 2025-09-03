package br.edu.ifsc.edson;

import android.app.LocaleManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    TextView textViewResultado;
    EditText editTextMin,editTextMax;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //Associar os objetos declarados e inicializados a partir do XML com variaveis locais
        button=findViewById(R.id.button);
        textViewResultado=findViewById(R.id.tvResultado);
        editTextMin=findViewById(R.id.edMin);
        editTextMax=findViewById(R.id.edMax);

        button.setOnClickListener((v) -> {

            int min=Integer.parseInt(editTextMin.getText().toString());
            int max=Integer.parseInt(editTextMax.getText().toString());
            int sorteado=0;
            sorteado = (int) (Math.random()*(max - min)+min);

            textViewResultado.setText(Integer.toString(sorteado));


        });
        getString(R.string.hello);
        LocaleManager local;
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}