package algonquin.cst2335.pajaappfinal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private static final String API_KEY = "https://api.spoonacular.com/recipes/complexSearch?apiKey=13d3e8429e4741c1832683ffae6972ea&query=";

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

        queue = Volley.newRequestQueue(this);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchText.getText().toString().trim();
                if (!query.isEmpty()) {
                    fetchRecipes(query);
                } else {
                    Toast.makeText(RecipeHomeActivity.this, "Please enter a search query", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fetchRecipes(String query) {
        String url = API_KEY + query;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray resultsArray = response.getJSONArray("results");
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
