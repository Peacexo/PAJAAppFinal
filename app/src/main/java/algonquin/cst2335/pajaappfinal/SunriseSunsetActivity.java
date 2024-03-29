package algonquin.cst2335.pajaappfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Switch;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.view.View;

import android.os.Bundle;

public class SunriseSunsetActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private Switch inputSwitch;
    private EditText locationInput, latInput, lonInput;

    private final String url = "api.sunrisesunset.io/json?lat=XXXXX&lng=YYYYYY&timezone=UTC&date=today";


    //Switch
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sunrise_sunset);

        recyclerView = findViewById(R.id.recyclerView);

        inputSwitch = findViewById(R.id.inputSwitch);
        locationInput = findViewById(R.id.locationInput);
        latInput = findViewById(R.id.latInput);
        lonInput = findViewById(R.id.lonInput);

        inputSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //by lat & lon
                    locationInput.setVisibility(View.GONE);
                    latInput.setVisibility(View.VISIBLE);
                    lonInput.setVisibility(View.VISIBLE);
                } else {
                    //by city
                    locationInput.setVisibility(View.VISIBLE);
                    latInput.setVisibility(View.GONE);
                    lonInput.setVisibility(View.GONE);
                }
            }
        });
    }
}