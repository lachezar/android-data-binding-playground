package se.finatech.databindingstest;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import se.finatech.databindingstest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        final ClockTimer timer = new ClockTimer();
        timer.start();

        if (binding != null) {
            binding.setTimer(timer);
        }
    }
}
