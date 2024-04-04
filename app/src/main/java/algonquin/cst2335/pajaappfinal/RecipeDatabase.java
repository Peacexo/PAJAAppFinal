package algonquin.cst2335.pajaappfinal;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Recipe.class}, version = 1)
public abstract class RecipeDatabase extends RoomDatabase {

    private static RecipeDatabase minstance;
    private static final String DB_NAME = "recipe_db";
    public abstract RecipeDAO getRecipeDAO();

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
