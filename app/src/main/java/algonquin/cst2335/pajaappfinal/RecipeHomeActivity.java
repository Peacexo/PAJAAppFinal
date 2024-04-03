package algonquin.cst2335.pajaappfinal;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private ImageButton searchButton;
    private RecyclerView recyclerView;

    private RecipeAdapter adapter;
    private List<Recipe> recipeList;
    private RequestQueue queue;

    private final String MY_KEY = "13d3e8429e4741c1832683ffae6972ea";
    private final String URL_REQUEST_DATA = "https://api.spoonacular.com/recipes/complexSearch?apiKey=" + MY_KEY + "&query=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_home_activity);

        searchText = findViewById(R.id.editSearchRecipe);
        searchButton = findViewById(R.id.searchRecipeButton);
        recyclerView = findViewById(R.id.recycler_view_recipes);

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
                } else {
                    Toast.makeText(RecipeHomeActivity.this, "Please enter a word to search", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void searchRecipes(String query) {
        String url = URL_REQUEST_DATA + query;

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

}
