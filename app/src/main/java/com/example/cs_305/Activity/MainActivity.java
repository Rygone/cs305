package com.example.cs_305.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import com.example.cs_305.R;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private Button done;
    private Button goWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        done = findViewById(R.id.done);
        goWeather = findViewById(R.id.goWeather);

        done.setOnClickListener(clicked -> {
            Intent intent = new Intent(this, GreetingActivity.class);
            intent.putExtra(GreetingActivity.EXTRA_USER_NAME, name.getText().toString());
            startActivity(intent);
        });

        goWeather.setOnClickListener(clicked -> {
            Intent intent = new Intent(this, WeatherActivity.class);
            startActivity(intent);
        });

        name.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                done.callOnClick();
                return true;
            }
            return false;
        });
    }

    protected void sayHello(String userName) {

    }
}