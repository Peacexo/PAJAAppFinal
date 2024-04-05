/**
 * Author: Peace Iyunade
 * Lab section: CST2335 022
 * Creation Date: 31st March 2024
 */
package algonquin.cst2335.pajaappfinal;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * This class represents the Room database for storing saved definitions.
 * It defines the database schema, provides a singleton instance of the database, and exposes DAO operations.
 * Uses Room annotations to define the database configuration.
 * @author Peace Iyunade
 * @version March 31, 2024 (Final Version)
 */

@Database(entities = {SavedDefinitionDic.class}, version = 1)

public abstract class DefinitionDatabase extends RoomDatabase {
    private static DefinitionDatabase instance;
    /**
     * Retrieves the DAO interface for accessing database operations.
     * @return The DAO interface for saved definitions.
     */
    public abstract SavedDefinitionDAO savedDefinitionDAO();
    /**
     * Returns a singleton instance of the DefinitionDatabase.
     * @param context The application context.
     * @return The singleton instance of the DefinitionDatabase.
     */

    public static synchronized DefinitionDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            DefinitionDatabase.class, "definitions_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
    /**
     * Destroys the existing instance of the DefinitionDatabase.
     */
    public static synchronized void destroyInstance() {
        instance = null;
    }

}
