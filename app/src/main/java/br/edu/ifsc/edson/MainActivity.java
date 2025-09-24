package br.edu.ifsc.edson;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    FrameLayout frameLayout;


    FragmentA fragmentA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        frameLayout=findViewById(R.id.frameLayout);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(fragmentA == null){
            fragmentA = new FragmentA();
        }
        fragmentTransaction.replace(R.id.frameLayout, fragmentA);
        fragmentTransaction.commit();
    }
}