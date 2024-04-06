package algonquin.cst2335.pajaappfinal;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

/**
 * The SunriseSunsetActivity class is responsible for displaying the main screen of the application,
 * where users can input latitude and longitude values to fetch sunrise and sunset times.
 * It also allows users to view their favorite locations and their corresponding sunrise and sunset times.
 * <p>
 * The class includes methods for saving latitude and longitude values to SharedPreferences,
 * fetching sunrise and sunset times from an API, and updating the RecyclerView with favorite locations.
 * <p>
 * Additionally, it contains an inner class FavoritesAdapter which is a RecyclerView Adapter used to
 * populate the list of favorite locations in the RecyclerView.
 */
/**
 * @author JingYi Li
 */
public class SunriseSunsetActivity extends AppCompatActivity {

    private EditText latitudeEditText;
    private EditText longitudeEditText;
    private Button lookupButton;
    private RecyclerView favoritesRecyclerView;
    private FavoritesAdapter favoritesAdapter;
    private List<String> favoriteLocations = new ArrayList<>(); // Assuming you're just storing location names for now
    private AppDatabase db;
    private SharedPreferences sharedPreferences;
    Button viewFavoritesButton;

    /**
     * Called when the activity is first created. Responsible for initializing the activity's views,
     * setting up the RecyclerView with its adapter, and setting click listeners for the lookup button
     * and view favorites button.
     *
     * @param savedInstanceState a Bundle containing the activity's previously saved state, if available.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sunrise_sunset); // This should match your XML file name.
        Toolbar toolbar = findViewById(R.id.sunrise_bar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");


        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("LocationData", MODE_PRIVATE);

        // Make sure IDs match your layout
        latitudeEditText = findViewById(R.id.latitudeInput);
        longitudeEditText = findViewById(R.id.longitudeInput);
        lookupButton = findViewById(R.id.lookupButton);
        favoritesRecyclerView = findViewById(R.id.favoritesRecyclerView); // Ensure this

        // Set latitude and longitude values from SharedPreferences to their respective TextViews
        TextView latSaveTextView = findViewById(R.id.lat_save);
        TextView lonSaveTextView = findViewById(R.id.lon_save);
        String savedLatitude = sharedPreferences.getString("latitude", "");
        String savedLongitude = sharedPreferences.getString("longitude", "");
        latSaveTextView.setText(savedLatitude);
        lonSaveTextView.setText(savedLongitude);

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

                    // Save latitude and longitude to SharedPreferences
                    saveLocationData(String.valueOf(latitude), String.valueOf(longitude));

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.sunrise_menu, menu);
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.sunrise_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Check if the item selected matches the menu item
        if (item.getItemId() == R.id.sunrise_menu_about) {
            showHelpDialog();
            return true; // Return true to indicate that the menu item click event has been handled
        } else {
            // If the selected item does not match the menu item, let the superclass handle it
            return super.onOptionsItemSelected(item);
        }
    }

    private void showHelpDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ABOUT");
        builder.setMessage("Hi!");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Saves the provided latitude and longitude values to SharedPreferences for future use.
     * Also updates the corresponding TextViews in the layout to display the new latitude and longitude.
     *
     * @param latitude  the latitude value to be saved
     * @param longitude the longitude value to be saved
     */
    // Method to save latitude and longitude to SharedPreferences
    private void saveLocationData(String latitude, String longitude) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Latitude", latitude);
        editor.putString("Longitude", longitude);
        editor.apply();

        // Update lat_save and lon_save TextViews with the latitude and longitude values
        TextView latSaveTextView = findViewById(R.id.lat_save);
        TextView lonSaveTextView = findViewById(R.id.lon_save);
        latSaveTextView.setText(latitude);
        lonSaveTextView.setText(longitude);
    }

    /**
     * Fetches sunrise and sunset times from an API based on the provided latitude and longitude values.
     * Displays the retrieved times in a new activity (SunriseSunsetDetailsActivity) if successful.
     * Shows a toast message if an error occurs during data fetching.
     *
     * @param latitude  the latitude value for which to fetch sunrise and sunset times
     * @param longitude the longitude value for which to fetch sunrise and sunset times
     */
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


    /**
     * The FavoritesAdapter class is an inner class of SunriseSunsetActivity responsible for populating
     * the RecyclerView with favorite locations.
     * <p>
     * It extends RecyclerView.Adapter and implements methods to create, bind, and update ViewHolder objects
     * for each item in the RecyclerView.
     * <p>
     * This adapter class also includes a ViewHolder inner class to hold references to the views
     * corresponding to each item in the RecyclerView.
     */

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

