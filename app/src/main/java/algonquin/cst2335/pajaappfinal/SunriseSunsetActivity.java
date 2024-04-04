package algonquin.cst2335.pajaappfinal;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SunriseSunsetActivity extends AppCompatActivity {

    private EditText latitudeEditText;
    private EditText longitudeEditText;
    private Button lookupButton;
    private RecyclerView favoritesRecyclerView;
    private FavoritesAdapter favoritesAdapter;
    private List<String> favoriteLocations = new ArrayList<>(); // Assuming you're just storing location names for now
    private AppDatabase db;
    Button viewFavoritesButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sunrise_sunset); // This should match your XML file name.

        // Make sure IDs match your layout
        latitudeEditText = findViewById(R.id.latitudeInput);
        longitudeEditText = findViewById(R.id.longitudeInput);
        lookupButton = findViewById(R.id.lookupButton);
        favoritesRecyclerView = findViewById(R.id.favoritesRecyclerView); // Ensure this

        // Initialize your views and RecyclerView

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "favorites_database")
                .allowMainThreadQueries() // For simplicity, allowing main thread queries. Consider background threads for production.
                // Add your migration object here.
                .fallbackToDestructiveMigration()
                .build();

        viewFavoritesButton = findViewById(R.id.view_favorites_button);
        viewFavoritesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                db.favoriteLocationDao().getFavoriteLocations().observe(SunriseSunsetActivity.this, new Observer<List<FavoriteLocation>>() {
                    @Override
                    public void onChanged(List<FavoriteLocation> favoriteLocations) {
                        favoritesAdapter.setFavoriteLocations(favoriteLocations);
                        favoritesAdapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "Showing favorites", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        lookupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double latitude = Double.parseDouble(latitudeEditText.getText().toString());
                    double longitude = Double.parseDouble(longitudeEditText.getText().toString());
                    fetchSunriseSunsetTimes(latitude, longitude);
                    latitudeEditText.setText("");
                    longitudeEditText.setText("");
                } catch (NumberFormatException e) {
                    Toast.makeText(SunriseSunsetActivity.this, "Please enter valid latitude and longitude values.", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Setup RecyclerView with the adapter
        favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        favoritesAdapter = new FavoritesAdapter(new ArrayList<>(), this);
        favoritesRecyclerView.setAdapter(favoritesAdapter);

    }

    private void fetchSunriseSunsetTimes(double latitude, double longitude) {
        String url = "https://api.sunrise-sunset.org/json?lat=" + latitude + "&lng=" + longitude + "&formatted=0";

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONObject results = response.getJSONObject("results");
                        String sunrise = results.getString("sunrise");
                        String sunset = results.getString("sunset");

                        Intent intent = new Intent(this, SunriseSunsetDetailsActivity.class);
                        intent.putExtra("latitude", latitude);
                        intent.putExtra("longitude", longitude);
                        intent.putExtra("sunrise", sunrise);
                        intent.putExtra("sunset", sunset);
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> Toast.makeText(SunriseSunsetActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show());
        queue.add(jsonObjectRequest);
    }



    private class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {
        private List<FavoriteLocation> favoriteLocations; // Adjusted to use FavoriteLocation
        private Context context;

        // Updated constructor
        public FavoritesAdapter(List<FavoriteLocation> favoriteLocations, Context context) {
            this.favoriteLocations = favoriteLocations;
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sunrise_item_location, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            FavoriteLocation location = favoriteLocations.get(position);


            // New
//            SunriseSunsetDetailsActivity activity = new SunriseSunsetDetailsActivity();
            // 从纬度和经度信息获取城市名称
//            String cityName = activity.fetchLocationName(location.getLatitude(), location.getLongitude());

            // 设置地点名称为获取的城市名称
//            holder.locationName.setText(cityName);
//            holder.locationDetails.setText("Sunrise: " + location.getSunriseTime() + ", Sunset: " + location.getSunsetTime());

            holder.locationName.setText("Location " + (position + 1));
            holder.locationName.setText(location.getLocationName());
            holder.locationDetails.setText("Sunrise: " + location.getSunriseTime() + ", Sunset: " + location.getSunsetTime());

            // Initial favorite button state
            holder.favoriteButton.setText(location.getIsFavorite() ? "Delete" : "Favorite");

            holder.favoriteButton.setSelected(location.isFavorite);

            holder.todaysTimingsButton.setOnClickListener(view -> {
                // Create an Intent to start SunriseSunsetDetailsActivity
                Intent intent = new Intent(context, SunriseSunsetDetailsActivity.class);

                // Pass additional data: latitude, longitude, sunrise, and sunset times
                intent.putExtra("latitude", location.getLatitude());
                intent.putExtra("longitude", location.getLongitude());
                intent.putExtra("sunrise", location.getSunriseTime());
                intent.putExtra("sunset", location.getSunsetTime());

                // Start the activity
                context.startActivity(intent);
            });
            // Toggle favorite status on click
            holder.favoriteButton.setOnClickListener(view -> {
                if (location.getIsFavorite()) {
                    // Assuming direct database operation is replaced with a correct asynchronous handling
                    db.favoriteLocationDao().delete(location); // This line needs to be run off the UI thread

                    // Toast message specifically for deletion action
                    Toast.makeText(context, "Location deleted", Toast.LENGTH_SHORT).show();
                }
            });

        }
        public void setFavoriteLocations(List<FavoriteLocation> favoriteLocations) {
            this.favoriteLocations = favoriteLocations;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return favoriteLocations.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView locationName, locationDetails;
            Button favoriteButton;
            Button todaysTimingsButton;
            ViewHolder(View itemView) {
                super(itemView);
                locationName = itemView.findViewById(R.id.text_location_name);
                locationDetails = itemView.findViewById(R.id.text_location_details);
                favoriteButton = itemView.findViewById(R.id.button_favorite);
                todaysTimingsButton = itemView.findViewById(R.id.button_todays_timings);
            }
        }

    }

}
