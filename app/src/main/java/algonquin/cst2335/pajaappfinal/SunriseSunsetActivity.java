package algonquin.cst2335.pajaappfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class SunriseSunsetActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    private Switch inputSwitch;
    private EditText locationInput;
    private EditText latInput;
    private EditText lonInput;
    //Switch
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sunrise_sunset);

//        recyclerView = findViewById(R.id.recyclerView);

        Switch inputSwitch = findViewById(R.id.inputSwitch);
        EditText locationInput = findViewById(R.id.locationInput);
        EditText latInput = findViewById(R.id.latInput);
        EditText lonInput = findViewById(R.id.lonInput);

        inputSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //by lat & lon
                    locationInput.setVisibility(View.GONE);
                    latInput.setVisibility(View.VISIBLE);
                    lonInput.setVisibility(View.VISIBLE);
                } else {
                    //by lat & lon
                    //by city
                    locationInput.setVisibility(View.VISIBLE);
                    latInput.setVisibility(View.GONE);
                    lonInput.setVisibility(View.GONE);
                }
            }
        });

    }

}