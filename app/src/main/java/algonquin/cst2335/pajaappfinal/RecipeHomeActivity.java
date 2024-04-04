package algonquin.cst2335.pajaappfinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.recipe_menu, menu);
        return true;
    }

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
        // Hide the title from the toolbar
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

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchText.getText().toString().trim();
                if (!query.isEmpty()) {
                    searchRecipes(query);
                    saveTextToSharedPreferences(query);
                } else {
                    Toast.makeText(RecipeHomeActivity.this, "Please enter a search query", Toast.LENGTH_SHORT).show();
                }
            }
        });


        favoriteRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the activity to display the favorite list of recipes
                Intent intent = new Intent(RecipeHomeActivity.this, FavoriteRecipesActivity.class);
                startActivity(intent);
            }
        });
    }

    private void searchRecipes(String query) {
        String url = URL_API_KEY + query;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray resultsArray = response.getJSONArray("results");
                            if (resultsArray.length() == 0) {
                                // If no recipes are found, show a Snackbar
                                Snackbar.make(findViewById(android.R.id.content), "No recipes found for '" + query + "'", Snackbar.LENGTH_LONG)
                                        .setAction("OK", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                searchText.setText("");
                                            }
                                        })
                                        .show();
                            } else {
                                recipeList.clear();
                                for (int i = 0; i < resultsArray.length(); i++) {
                                    JSONObject recipeObject = resultsArray.getJSONObject(i);
                                    int id = recipeObject.getInt("id");
                                    String title = recipeObject.getString("title");
                                    String imageUrl = recipeObject.getString("image");
                                    Recipe recipe = new Recipe(id, title, imageUrl);
                                    recipeList.add(recipe);
                                }
                                adapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RecipeHomeActivity.this, "Error fetching recipes: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(request);
    }

    private void saveTextToSharedPreferences(String editTextQuery) {
        SharedPreferences.Editor writer = preferences.edit();
        writer.putString("queryResearched", editTextQuery);
        writer.apply(); //save to disk
        Toast.makeText(this, "Text saved to SharedPreferences", Toast.LENGTH_SHORT).show();
    }

    // Override onPause to save the sharedPreferences when the activity is paused
    @Override
    protected void onPause() {
        super.onPause();
        String editTextQuery = searchText.getText().toString();
        saveTextToSharedPreferences(editTextQuery);
    }

    // Override onResume to retrieve the Saved sharedPreferences when the activity is closed and resumed
    @Override
    protected void onResume() {
        super.onResume();
        String editTextQuery = preferences.getString("queryResearched", "");
        searchText.setText(editTextQuery);
    }

}
