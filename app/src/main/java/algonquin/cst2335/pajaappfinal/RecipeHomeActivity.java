/**
 * RecipeHomeActivity is the home page for the Recipe Search APP. It allows users to search for recipes
 * and view their details. They can also access their favorite recipes.
 * Author: Alessandra Prunzel Kittlaus
 * Lab Section: 022
 * Creation Date: 04/04/2024
 */
package algonquin.cst2335.pajaappfinal;

import static algonquin.cst2335.pajaappfinal.R.string.recipe_keyword_for_search;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecipeHomeActivity extends AppCompatActivity {
    private EditText searchText;
    private Button searchButton;
    private RecyclerView recyclerView;
    private Button favoriteRecipes;
    private Toolbar toolbar;
    public final static String PREFERENCES_FILE = "MyData";
    private SharedPreferences preferences;
    private RecipeAdapter adapter;
    private List<Recipe> recipeList;
    private RequestQueue queue;

    private final String MY_KEY = "13d3e8429e4741c1832683ffae6972ea";
    private final String URL_API_KEY = "https://api.spoonacular.com/recipes/complexSearch?apiKey=" + MY_KEY + "&query=";

    /**
     * Initializes the options menu for the activity.
     * Inflates the menu layout defined in 'recipe_menu.xml'.
     * Sets a click listener for the 'About' menu item to display instructions when clicked.
     * @param menu The menu object to initialize.
     * @return true to display the menu, false otherwise.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.recipe_menu, menu);
        MenuItem aboutInterface = menu.findItem(R.id.itemAbout);
        aboutInterface.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                displayInstructionAlertDialog();
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Displays an AlertDialog with app instructions when the about option is selected.
     */
    private void displayInstructionAlertDialog() {
        android.app.AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(R.string.recipe_about_app);
        alertDialogBuilder.setMessage(getString(R.string.recipe_instructions));
        alertDialogBuilder.setPositiveButton(R.string.recipe_got_it, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    });
        alertDialogBuilder.create().show();
}

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
        setContentView(R.layout.recipe_home_activity);

        searchText = findViewById(R.id.editSearchRecipe);
        searchButton = findViewById(R.id.searchRecipeButton);
        recyclerView = findViewById(R.id.recycler_view_recipes);
        favoriteRecipes = findViewById(R.id.buttonFavorites);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        // Hide the title in the toolbar
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        preferences = getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        //Read preferences
        String previousResearch = preferences.getString("QueryResearched", "");
        searchText.setText(previousResearch);

        recipeList = new ArrayList<>();
        adapter = new RecipeAdapter(recipeList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Instantiate VolleySingleton
        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);
        // Get the RequestQueue from VolleySingleton
        queue = volleySingleton.getRequestQueue();

        /**
         * Sets a click listener for the search button.
         * When clicked, it retrieves the keyword from EditText, trims it, and checks if it's empty.
         * If not empty, it initiates a recipe search with the provided query (keyword) and saves
         * the query to SharedPreferences. If empty, it displays a toast indicating the need for a keyword for search.
         */
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchText.getText().toString().trim();
                if (!query.isEmpty()) {
                    searchRecipes(query);
                    saveTextToSharedPreferences(query);
                } else {
                    Toast.makeText(RecipeHomeActivity.this, recipe_keyword_for_search, Toast.LENGTH_SHORT).show();
                }
            }
        });

        /**
         * Sets a click listener for the "List My Favorites" button to view favorite recipes list.
         * When clicked, it starts the FavoriteRecipesActivity to display the list of favorite recipes.
         */
        favoriteRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the activity to display the favorite list of recipes
                Intent intent = new Intent(RecipeHomeActivity.this, FavoriteRecipesActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Searches for recipes using the provided query (keyword) string.
     * Use a Volley request to retrieve recipe information.
     * Upon receiving a response, it parses the JSON data to extract recipe details such as ID, title, and image URL.
     * @param query
     */
    private void searchRecipes(String query) {
        String url = URL_API_KEY + query;

        // Create a JsonObjectRequest to send a GET request to the API
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray resultsArray = response.getJSONArray("results"); // Parse the JSON response to retrieve the results array
                            if (resultsArray.length() == 0) {
                                // If no recipes are found, show a message
                                Snackbar.make(findViewById(android.R.id.content), getString(R.string.recipes_not_found) + query + "'", Snackbar.LENGTH_LONG)
                                        .setAction("OK", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                searchText.setText("");
                                            }
                                        })
                                        .show();
                            } else {
                                recipeList.clear(); // Clear the existing recipe list array
                                for (int i = 0; i < resultsArray.length(); i++) {
                                    JSONObject recipeObject = resultsArray.getJSONObject(i);
                                    int id = recipeObject.getInt("id");
                                    String title = recipeObject.getString("title");
                                    String imageUrl = recipeObject.getString("image");

                                    // Create a Recipe object with the retrieved details
                                    Recipe recipe = new Recipe(id, title, imageUrl);
                                    recipeList.add(recipe); // Add the recipe to the recipe list array.
                                }
                                adapter.notifyDataSetChanged();  // Notify the adapter to update the RecyclerView with the new data
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RecipeHomeActivity.this, R.string.recipe_error_fetching_details + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(request);
    }

    /**
     * Saves the provided query string to SharedPreferences for future retrieval.
     * @param editTextQuery The query string typed by the user in the EditText field.
     */
    private void saveTextToSharedPreferences(String editTextQuery) {
        SharedPreferences.Editor writer = preferences.edit();
        writer.putString("queryResearched", editTextQuery);
        writer.apply(); //save to disk
        Toast.makeText(this, R.string.recipe_saved_to_sharedpreferences, Toast.LENGTH_SHORT).show();
    }

    /**
     * Called as part of the activity lifecycle when the activity is paused.
     * It retrieves the query from EditText field, converts it to a string, and then saves
     * it to SharedPreferences using the saveTextToSharedPreferences() method.
     */
    @Override
    protected void onPause() {
        super.onPause();
        String editTextQuery = searchText.getText().toString();
        saveTextToSharedPreferences(editTextQuery);
    }

    /**
     * Called after the activity is closed and resumed, when the activity will start interacting with the user.
     * It retrieves the keyword(query) from SharedPreferences and sets it as the text in
     *  search field (EditText)
     */
    @Override
    protected void onResume() {
        super.onResume();
        String editTextQuery = preferences.getString("queryResearched", "");
        searchText.setText(editTextQuery);
    }

}
