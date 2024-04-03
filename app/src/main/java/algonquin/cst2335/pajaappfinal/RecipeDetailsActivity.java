package algonquin.cst2335.pajaappfinal;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class RecipeDetailsActivity extends AppCompatActivity {

    private RequestQueue queue;
    private RecipeDAO recipeDAO;

    Button saveToFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        saveToFavorites = findViewById(R.id.buttonSaveToFavorites);

        // Instantiate VolleySingleton
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        // Get the RequestQueue from VolleySingleton
        queue = volleySingleton.getRequestQueue();


        // Get recipe details from intent extras
        Recipe recipe = (Recipe) getIntent().getSerializableExtra("recipe");
        // Fetch recipe details from API based on recipe object
        if (recipe != null) {
            searchRecipeDetails(recipe);
        } else {
            Toast.makeText(this, "Invalid recipe", Toast.LENGTH_SHORT).show();
            finish();// Finish activity if recipe is invalid
        }


// Inside RecipeDetailsActivity
        RecipeDatabase recipeDatabase = RecipeDatabase.getInstance(getApplicationContext());
        recipeDAO = recipeDatabase.getRecipeDAO();


        saveToFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save the recipe to favorites using RoomDatabase
                saveRecipeToFavorites(recipe);
            }
        });
    }


    private void searchRecipeDetails(Recipe recipe) {
        String url = "https://api.spoonacular.com/recipes/" + recipe.getId() + "/information?apiKey=13d3e8429e4741c1832683ffae6972ea";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Parse response to retrieve recipe details
                            String summary = response.getString("summary");
                            String fullRecipeUrl = response.getString("sourceUrl");

                            // Update existing Recipe object with new information
                            recipe.setSummary(summary);
                            recipe.setFullRecipeUrl(fullRecipeUrl);


                            ImageView imageViewRecipe = findViewById(R.id.imageViewRecipe);
                            TextView textViewTitle = findViewById(R.id.textViewTitle);
                            TextView textViewSummary = findViewById(R.id.textViewSummary);
                            TextView textViewFullRecipeUrl = findViewById(R.id.textViewFullRecipeUrl);

                            // Populate views with recipe details
                            Picasso.get().load(recipe.getImageUrl()).into(imageViewRecipe);
                            textViewTitle.setText(recipe.getTitle());
                            textViewSummary.setText(recipe.getSummary());
                            textViewFullRecipeUrl.setText(recipe.getFullRecipeUrl());
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RecipeDetailsActivity.this, "Error parsing recipe details", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RecipeDetailsActivity.this, "Error fetching recipe details: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(request);

    }

    private void saveRecipeToFavorites(Recipe recipe) {

        // Execute the saving operation in a background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Insert the recipe into the database
                recipeDAO.insertRecipe(recipe);
            }
        }).start();

        // Show a toast message indicating that the recipe is saved to favorites
        Toast.makeText(this, "Recipe saved to favorites", Toast.LENGTH_SHORT).show();
    }

}