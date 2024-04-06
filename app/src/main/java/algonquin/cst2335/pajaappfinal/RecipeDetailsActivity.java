/**
 * RecipeDetailsActivity displays the details of a recipe including its title, image, summary, and full recipe URL.
 * Author: Alessandra Prunzel Kittlaus
 * Lab Section: 022
 * Creation Date: 04/04/2024
 */
package algonquin.cst2335.pajaappfinal;


import static algonquin.cst2335.pajaappfinal.R.string.recipe_already_saved;
import static algonquin.cst2335.pajaappfinal.R.string.recipe_error_parsing_details;
import static algonquin.cst2335.pajaappfinal.R.string.recipe_invalid;
import static algonquin.cst2335.pajaappfinal.R.string.recipe_saved_to_favorites;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

    private final String MY_KEY = "13d3e8429e4741c1832683ffae6972ea";
    private final String URL_REQUEST_DATA = "https://api.spoonacular.com/recipes/";
    private RequestQueue queue;
    private RecipeDAO recipeDAO;
    Button saveToFavorites;

    /**
     * OnCreate is called when the activity is starting. Responsible for initializing the activity.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        saveToFavorites = findViewById(R.id.buttonSaveToFavorites);

        // Instantiate VolleySingleton
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        // Get the RequestQueue from VolleySingleton
        queue = volleySingleton.getRequestQueue();

        // Get recipe details sent from intent extras in RecipeAdapter class, onBindViewHolder method.
        Recipe recipe = (Recipe) getIntent().getSerializableExtra("recipe");

        if (recipe != null) {
            searchRecipeDetails(recipe); // Fetch recipe details from API based on recipe object
        } else {
            Toast.makeText(this, recipe_invalid, Toast.LENGTH_SHORT).show();
            finish();// Finish activity if recipe is invalid
        }

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

    /**
     * Searches for additional recipe details from the API and updates the Recipe Object accordingly.
     * @param recipe The recipe object for which details are fetched.
     */
    private void searchRecipeDetails(Recipe recipe) {
        String url = URL_REQUEST_DATA + recipe.getId() + "/information?apiKey=" + MY_KEY;

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
                            Toast.makeText(RecipeDetailsActivity.this, recipe_error_parsing_details, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RecipeDetailsActivity.this, getString(R.string.recipe_error_fetching_details) + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(request);

    }

    /**
     * Saves the recipe to the favorites list.
     * @param recipe
     */
    private void saveRecipeToFavorites(Recipe recipe) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Recipe checkingForExistentRecipe = recipeDAO.getRecipeById(recipe.getId());
                if (checkingForExistentRecipe == null) {

                    recipeDAO.insertRecipe(recipe);// Insert the recipe into the database

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RecipeDetailsActivity.this, recipe_saved_to_favorites, Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RecipeDetailsActivity.this, recipe_already_saved, Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }).start();
    }
}
