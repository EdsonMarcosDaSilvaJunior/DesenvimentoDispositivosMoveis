package br.edu.ifsc.edson;

import android.app.LocaleManager;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sm;

    Sensor sensorLuz;
    TextView tvLuz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLuz=findViewById(R.id.textView);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorLuz = sm.getDefaultSensor(Sensor.TYPE_LIGHT);

        sm.registerListener(MainActivity.this, sensorLuz, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float luz = event.values[0];
        tvLuz.setText(luz+" lz");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}