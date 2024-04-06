/**
 * RecipeDAO (Data Access Object) provides methods to interact with the Recipe database.
 * Author: Alessandra Prunzel Kittlaus
 * Lab Section: 022
 * Creation Date: 04/04/2024
 */
package algonquin.cst2335.pajaappfinal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RecipeDAO {

    /**
     * Inserts a recipe into the database.
     * @param recipe The recipe to insert.
     */
    @Insert
    void insertRecipe(Recipe recipe);

    /**
     * Updates a recipe in the database.
     * @param recipe
     */
    @Update
    void updateRecipe(Recipe recipe);

    /**
     * Deletes a recipe from the database.
     * @param recipe
     */
    @Delete
    void deleteRecipe(Recipe recipe);

    /**
     * Retrieves a list of all favorite recipes from the database.
     * @return
     */
    @Query("SELECT * FROM recipes")
    List<Recipe> favoriteListRecipe();

    /**
     * Deletes a recipe with the specified ID from the database.
     * @param id
     */
    @Query("DELETE FROM recipes WHERE id = :id")
    void deleteOneRecipe(int id);

    /**
     * Deletes all recipes from the database.
     */
    @Query("DELETE FROM recipes")
    void deleteAll();

    /**
     * Retrieves a recipe from the database based on its ID.
     * @param id
     * @return The recipe with the specified ID.
     */
    @Query("SELECT * FROM recipes WHERE id = :id")
    Recipe getRecipeById(int id);

}