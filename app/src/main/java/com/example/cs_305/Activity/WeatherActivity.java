package com.example.cs_305.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.cs_305.R;
import com.example.cs_305.Service.GeocodingService;
import com.example.cs_305.Service.LocationService;
import com.example.cs_305.Service.WeatherService;
import com.example.cs_305.Tool.Location;

public class WeatherActivity extends AppCompatActivity {
    private EditText adresseText;
    private Button done;
    private Switch useLocation;
    private TextView weatherText;

    GeocodingService gs;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        adresseText = findViewById(R.id.adresseText);
        done = findViewById(R.id.done);
        useLocation = findViewById(R.id.useLocation);
        weatherText = findViewById(R.id.weatherText);

        gs = new GeocodingService(this);
        location = new Location(0,0);

        done.setOnClickListener(clicked -> {
            Activity activity = this;
            new Thread(){
                public void run() {
                    if(useLocation.isChecked()){
                        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
                        }
                        location = LocationService.getPosition(activity);
                        String address = gs.getAddress(location);
                        if(address != null) {
                            adresseText.setText(address);
                        } else {
                            adresseText.setText("An error has occurred");
                        }
                    } else {
                        location = gs.getlocation(adresseText.getText().toString());
                    }
                    String res = WeatherService.getWeather(location);
                    if(res != null) {
                        weatherText.setText(res);
                    } else {
                        weatherText.setText("An error has occurred");
                    }
                }
            }.start();

        });

        adresseText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                useLocation.setChecked(false);
                done.callOnClick();
                return true;
            }
            return false;
        });
    }


}