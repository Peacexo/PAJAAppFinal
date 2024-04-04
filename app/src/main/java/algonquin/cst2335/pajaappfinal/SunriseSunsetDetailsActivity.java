package algonquin.cst2335.pajaappfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SunriseSunsetDetailsActivity extends AppCompatActivity {

    private TextView sunriseTextView, sunsetTextView, locationTextView, latitudeTextView, longitudeTextView;
    private Button addFavoriteButton;

    private AppDatabase db;
    private FavoriteLocationDao favoriteLocationDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sunrise_sunset_details); // Ensure this matches your layout file name

        //Back button
        Button backButton = findViewById(R.id.backButton);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "favorites_database")
                .allowMainThreadQueries() // For simplicity, allowing main thread queries. Consider background threads for production.
                // Add your migration object here.
                .fallbackToDestructiveMigration()
                .build();
        favoriteLocationDao = db.favoriteLocationDao();
        // Retrieve data
        double latitude = getIntent().getDoubleExtra("latitude", 0);
        double longitude = getIntent().getDoubleExtra("longitude", 0);
        String sunrise = getIntent().getStringExtra("sunrise");
        String sunset = getIntent().getStringExtra("sunset");

        // Display data
        ((TextView) findViewById(R.id.latitudeTextView)).setText(String.valueOf(latitude));
        ((TextView) findViewById(R.id.longitudeTextView)).setText(String.valueOf(longitude));
//        ((TextView) findViewById(R.id.sunriseTextView)).setText(sunrise);
//        ((TextView) findViewById(R.id.sunsetTextView)).setText(sunset);
        ((TextView) findViewById(R.id.sunriseTextView)).setText(parseTime(sunrise));
        ((TextView) findViewById(R.id.sunsetTextView)).setText(parseTime(sunset));

        // Set location text
        locationTextView = findViewById(R.id.locationTextView);
        fetchLocationName(latitude, longitude);
        addFavoriteButton = findViewById(R.id.addFavoriteButton);

        addFavoriteButton.setOnClickListener(view -> {
            // Create and insert a new FavoriteLocation into the database
            FavoriteLocation favoriteLocation = new FavoriteLocation("Location", sunrise, sunset);
            favoriteLocation.setLatitude(latitude);
            favoriteLocation.setLongitude(longitude);
            favoriteLocation.setIsFavorite(true);
            favoriteLocationDao.insert(favoriteLocation); // Directly calling the DAO method without using Executors or AsyncTask
            Toast.makeText(getApplicationContext(), "Added to favorites", Toast.LENGTH_SHORT).show();
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate back to sunrise_sunset activity
                Intent intent = new Intent(SunriseSunsetDetailsActivity.this, algonquin.cst2335.pajaappfinal.SunriseSunsetActivity.class);
                startActivity(intent);
                finish(); // Finish the current activity to prevent stacking
            }
        });
    }

    // API
    public String fetchLocationName(double latitude, double longitude) {
        String locationUrl = "https://nominatim.openstreetmap.org/reverse?format=json&lat=" + latitude + "&lon=" + longitude + "&zoom=5";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, locationUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject addressObject = response.getJSONObject("address");
                            String state = addressObject.optString("state", "");
                            if (!state.isEmpty()) {
                                locationTextView.setText(state);
                            } else {
                                locationTextView.setText("Location: Unknown");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("API Error", "Error occurred while fetching data: " + error.getMessage());
                        Toast.makeText(SunriseSunsetDetailsActivity.this, "Error occurred while fetching data", Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(this).add(request);
        return locationUrl;
    }

    private String parseTime(String timestamp) {
        // Split the timestamp to get the time part
        String[] parts = timestamp.split("T");
        if (parts.length >= 2) {
            String timePart = parts[1];
            // Remove the trailing Z or timezone offset, if present
            int offsetIndex = timePart.indexOf("+");
            if (offsetIndex != -1) {
                timePart = timePart.substring(0, offsetIndex);
            } else if (timePart.endsWith("Z")) {
                timePart = timePart.substring(0, timePart.length() - 1);
            }
            // Split the time part to get the hours and minutes
            String[] timeComponents = timePart.split(":");
            if (timeComponents.length >= 2) {
                return timeComponents[0] + ":" + timeComponents[1]; // Return only hours and minutes
            }
        }
        return ""; // Return empty string if parsing fails
    }

}
