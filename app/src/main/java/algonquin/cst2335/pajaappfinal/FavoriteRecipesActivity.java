package algonquin.cst2335.pajaappfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class FavoriteRecipesActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFavoriteRecipes;

    private RecipeAdapter adapter;
    private List<Recipe> favoriteRecipeList;
    private RecipeDAO recipeDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_recipes);

        recyclerViewFavoriteRecipes = findViewById(R.id.recyclerViewFavoriteRecipes);

        RecipeDatabase recipeDatabase = RecipeDatabase.getInstance(getApplicationContext());
        recipeDAO = recipeDatabase.getRecipeDAO();

        favoriteRecipeList = new ArrayList<>();
        adapter = new RecipeAdapter(favoriteRecipeList, this);
        recyclerViewFavoriteRecipes.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFavoriteRecipes.setAdapter(adapter);

        loadData();
    }

    private void loadData() {
        new Thread(() -> {
            // Fetch all favorite recipes from the database
            List<Recipe> allFavoriteRecipes = recipeDAO.favoriteListRecipe();

            // Clear the existing list and add all fetched recipes to it
            favoriteRecipeList.clear();
            favoriteRecipeList.addAll(allFavoriteRecipes);
        }).start();
        //Notification to the listView has to occur in the main UI thread
        adapter.notifyDataSetChanged();
    }

}