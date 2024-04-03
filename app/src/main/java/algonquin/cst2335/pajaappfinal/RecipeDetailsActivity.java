package algonquin.cst2335.pajaappfinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
    private final String MY_KEY = "13d3e8429e4741c1832683ffae6972ea";
    private final String URL_REQUEST_DATA = "https://api.spoonacular.com/recipes/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);


        VolleySingleton volleySingleton = VolleySingleton.getInstance(this);

        queue = volleySingleton.getRequestQueue();

        // Get recipe details from intent extras
        Intent intent = getIntent();
        //-1 e o valor default caso nao tenha valor
        int recipeId = intent.getIntExtra("recipeId", -1);

        if (recipeId != -1) {
            // Fetch recipe details using recipeId
            searchRecipeDetails(recipeId);
        } else {
            // Handle error: Invalid recipeId
            Toast.makeText(this, "Invalid recipeId", Toast.LENGTH_SHORT).show();
            finish(); // Finish activity if recipeId is invalid
        }
    }

    private void searchRecipeDetails(int recipeId) {
        String url = URL_REQUEST_DATA + recipeId + "/information?apiKey=" + MY_KEY;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Parse response to retrieve recipe details
                            String title = response.getString("title");
                            String imageUrl = response.getString("image");
                            String summary = response.getString("summary");
                            String fullRecipeUrl = response.getString("sourceUrl");

                            // Populate views with recipe details
                            ImageView imageViewRecipe = findViewById(R.id.imageViewRecipe);
                            TextView textViewTitle = findViewById(R.id.textViewTitle);
                            TextView textViewSummary = findViewById(R.id.textViewSummary);
                            TextView textViewFullRecipeUrl = findViewById(R.id.textViewFullRecipeUrl);

                            Picasso.get().load(imageUrl).into(imageViewRecipe);
                            textViewTitle.setText(title);
                            textViewSummary.setText(summary);
                            textViewFullRecipeUrl.setText(fullRecipeUrl);
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RecipeDetailsActivity.this, "Error fetching recipe details: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the RequestQueue
        queue.add(request);
    }
}