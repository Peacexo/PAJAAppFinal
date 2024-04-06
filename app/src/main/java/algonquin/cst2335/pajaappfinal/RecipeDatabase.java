/**
 * RecipeDatabase is the Room database class that provides access to the DAO and initializes the database.
 * Author: Alessandra Prunzel Kittlaus
 * Lab Section: 022
 * Creation Date: 04/04/2024
 */
package algonquin.cst2335.pajaappfinal;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Recipe.class}, version = 1)
public abstract class RecipeDatabase extends RoomDatabase {

    private static RecipeDatabase minstance;
    private static final String DB_NAME = "recipe_db";

    /**
     * Retrieves the RecipeDAO instance to access database operations(CRUD).
     * @return The RecipeDAO instance.
     */
    public abstract RecipeDAO getRecipeDAO();

    /**
     * Retrieves the singleton instance of the RecipeDatabase.
     * @param ctx The application context.
     * @return The RecipeDatabase instance.
     */
    public static synchronized RecipeDatabase getInstance(Context ctx) {
        if(minstance == null) {
            minstance = Room.databaseBuilder(ctx.getApplicationContext(),
                            RecipeDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return minstance;
    }
}
