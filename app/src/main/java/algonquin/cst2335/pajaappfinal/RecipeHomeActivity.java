package algonquin.cst2335.pajaappfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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

import algonquin.cst2335.pajaappfinal.databinding.ActivityRecipeHomeBinding;

public class RecipeHomeActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    private static final String API_KEY = "13d3e8429e4741c1832683ffae6972ea";
    private static final String API_URL = "https://api.spoonacular.com/recipes/complexSearch?apiKey=" + API_KEY + "&query=";

    protected String query;

    protected RequestQueue queue;
    private ActivityRecipeHomeBinding binding;

    RecipeAdapter recipeAdapter;
    List<Recipe> recipesList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRecipeHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        binding.searchRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query = binding.editSearchRecipe.getText().toString().trim();
                if (!query.isEmpty()) {
                    searchRecipes(query);
                } else {
                    Toast.makeText(RecipeHomeActivity.this, "Please enter a search query", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recipeAdapter = new RecipeAdapter(recipesList,getApplicationContext());
        binding.recyclerView.setAdapter(recipeAdapter);
    }

    private void searchRecipes(String query) {
        try {
            if (!query.isEmpty()) {
                String url = API_URL + query;

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray resultsArray = response.getJSONArray("results");
//                                    List<Recipe> recipesList = new ArrayList<>();
                                    for (int i = 0; i < resultsArray.length(); i++) {
                                        JSONObject recipeObject = resultsArray.getJSONObject(i);
                                        int id = recipeObject.getInt("recipeId");
                                        String title = recipeObject.getString("recipeTitle");
                                        String imageUrl = recipeObject.getString("recipeImage");
                                        recipesList.add(new Recipe(id, title, imageUrl));
                                    }
                                    recipeAdapter.notifyDataSetChanged();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e(TAG, "Error:" + error.getMessage());
                                Snackbar.make(binding.recyclerView, "R.string.error_msg", Snackbar.LENGTH_SHORT).show();
                            }
                        });

                queue = Volley.newRequestQueue(this);
                queue.add(request);
            } else {
                Snackbar.make(binding.recyclerView, "R.string.error_msg", Snackbar.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Error encoding query");
        }
    }
}