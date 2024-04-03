package algonquin.cst2335.pajaappfinal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RecipeDAO {

    @Insert
    void insertRecipe(Recipe recipe);

    @Update
    void updateRecipe(Recipe recipe);

    @Delete
    void deleteRecipe(Recipe recipe);

    @Query("SELECT * FROM recipes")
    List<Recipe> favoriteListRecipe();

    @Query("DELETE FROM recipes WHERE id = :n")
    void deleteOneRecipe(int n);

    @Query("DELETE FROM recipes")
    void deleteAll();
}