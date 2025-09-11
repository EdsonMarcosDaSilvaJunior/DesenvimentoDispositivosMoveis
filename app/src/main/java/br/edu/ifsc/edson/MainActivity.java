package br.edu.ifsc.edson;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    Button buttonA, buttonB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        frameLayout=findViewById(R.id.frameLayout);
        buttonA=findViewById(R.id.fragmentoA);
        buttonB=findViewById(R.id.fragmentoB);

        FragmentA fragmentA = new FragmentA();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frameLayout, fragmentA);
        fragmentTransaction.commit();
    }
}