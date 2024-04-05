/**
 * FavoriteRecipesActivity displays a list of favorite recipes saved by the user.
 * The user can view their favorite recipes and delete them all at once.
 * Author: Alessandra Prunzel Kittlaus
 * Lab Section: 022
 * Creation Date: 04/04/2024
 */
package algonquin.cst2335.pajaappfinal;

import static algonquin.cst2335.pajaappfinal.R.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class FavoriteRecipesActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFavoriteRecipes;
    private RecipeAdapter adapter;
    private List<Recipe> favoriteRecipeList;
    private RecipeDAO recipeDAO;
    private Button deleteAll;

    private List<Recipe> deletedRecipes = new ArrayList<>();  // List to store deleted recipes temporarily

    /**
     * OnCreate is called when the activity is starting. Responsible for initializing the activity.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_favorite_recipes);

        recyclerViewFavoriteRecipes = findViewById(id.recyclerViewFavoriteRecipes);
        deleteAll = findViewById(id.buttonDeleteAll);

        RecipeDatabase recipeDatabase = RecipeDatabase.getInstance(getApplicationContext());
        recipeDAO = recipeDatabase.getRecipeDAO();

        favoriteRecipeList = new ArrayList<>();
        adapter = new RecipeAdapter(favoriteRecipeList, this);
        recyclerViewFavoriteRecipes.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFavoriteRecipes.setAdapter(adapter);

        loadData();

        /**
         * Set a click listener for the delete All Button. So when the user clicks on it, they are asked
         * if they are sure they want to delete all recipes from favorite list. If they click No, nothing happens,
         * if they click Yes, all recipes are deleted. But, the user still have the chance to retrieve the deleted
         * recipes back if they click "undo" in the message displayed on the screen.
         */
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(FavoriteRecipesActivity.this);
                alertDialogBuilder.setTitle(string.recipe_delete_all_question);
                alertDialogBuilder.setMessage(string.recipe_delete_sure_question);
                alertDialogBuilder.setPositiveButton(string.recipe_yes_decision, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User clicked "Yes", so delete all recipes.
                        deletedRecipes.addAll(favoriteRecipeList); //Recipes being saved in an Array for backup.
                        deleteData();// Call the deleteData method when the button is clicked
                        Snackbar.make(deleteAll, string.recipe_delete_confirmation, Snackbar.LENGTH_LONG)
                                .setAction(string.recipe_undo, clk -> {
                                    favoriteRecipeList.addAll(deletedRecipes); //restoring deleted recipes.
                                    adapter.notifyDataSetChanged();
                                    deletedRecipes.clear(); //Cleaning the deletedRecipes array
                                }).show();
                    }
                });
                alertDialogBuilder.setNegativeButton(string.recipe_no_decision, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User clicked "No", do nothing
                        dialog.dismiss();
                    }
                });
                alertDialogBuilder.create().show();
            }
        });
    }

    /**
     * Load favorite recipes from the database and update the UI accordingly.
     */
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

    /**
     * Delete all favorite recipes from the database and update the UI accordingly.
     */
    private void deleteData() {
        new Thread(() -> {

            // Clear the existing list
            favoriteRecipeList.clear();
            recipeDAO.deleteAll(); // Delete all recipes from the database
        }).start();
        //Notification to the listView has to occur in the main UI thread
        adapter.notifyDataSetChanged();
    }

}