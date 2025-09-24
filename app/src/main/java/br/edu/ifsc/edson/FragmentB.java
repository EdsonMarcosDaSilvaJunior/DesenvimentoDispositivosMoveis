package br.edu.ifsc.edson;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentB extends Fragment {

    TextView tvReais, tvDolar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private double converteMoeda(String reais){
        return Double.parseDouble(reais) * 5.33;
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = getArguments();
        tvReais.setText(bundle.getString("reais"));

        //Double convertido = converteMoeda(bundle.getString("reais"));
        //tvDolar.setText(convertido.toString());
    }
}
