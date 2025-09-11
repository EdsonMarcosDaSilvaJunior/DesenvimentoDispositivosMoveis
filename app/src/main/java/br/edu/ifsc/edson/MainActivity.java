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


    FragmentA fragmentA;
    FragmentB fragmentB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        frameLayout=findViewById(R.id.frameLayout);
        buttonA=findViewById(R.id.fragmentoA);
        buttonB=findViewById(R.id.fragmentoB);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        buttonA.setOnClickListener(v -> {
            if(fragmentA == null){
                fragmentA = new FragmentA();
            }
            fragmentTransaction.add(R.id.frameLayout, fragmentA);
            fragmentTransaction.commit();
        });

        buttonB.setOnClickListener(v -> {
            if(fragmentB == null){
                fragmentB = new FragmentB();
            }
            fragmentTransaction.add(R.id.frameLayout, fragmentB);
            fragmentTransaction.commit();
        });
    }
}