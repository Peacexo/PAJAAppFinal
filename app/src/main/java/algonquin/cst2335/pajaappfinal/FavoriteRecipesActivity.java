package algonquin.cst2335.pajaappfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class FavoriteRecipesActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFavoriteRecipes;

    private RecipeAdapter adapter;
    private List<Recipe> favoriteRecipeList;
    private RecipeDAO recipeDAO;

    private Button deleteAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_recipes);

        recyclerViewFavoriteRecipes = findViewById(R.id.recyclerViewFavoriteRecipes);
        deleteAll = findViewById(R.id.buttonDeleteAll);

        RecipeDatabase recipeDatabase = RecipeDatabase.getInstance(getApplicationContext());
        recipeDAO = recipeDatabase.getRecipeDAO();

        favoriteRecipeList = new ArrayList<>();
        adapter = new RecipeAdapter(favoriteRecipeList, this);
        recyclerViewFavoriteRecipes.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFavoriteRecipes.setAdapter(adapter);

        loadData();

        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(FavoriteRecipesActivity.this);
                alertDialogBuilder.setTitle("Delete All Recipes");
                alertDialogBuilder.setMessage("Are you sure you want to delete all recipes from favorites?");
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User clicked "Yes", so delete all recipes
                        // Call the deleteData method when the button is clicked
                        deleteData();
                    }
                });
                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User clicked "No", do nothing
                        dialog.dismiss(); // Dismiss the dialog
                    }
                });

                // Create and show the AlertDialog
                alertDialogBuilder.create().show();
            }
        });
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

    private void deleteData() {
        new Thread(() -> {

            // Clear the existing list and add all fetched recipes to it
            favoriteRecipeList.clear();
            recipeDAO.deleteAll();
        }).start();
        //Notification to the listView has to occur in the main UI thread
        adapter.notifyDataSetChanged();
    }

}