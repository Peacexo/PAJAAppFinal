package algonquin.cst2335.pajaappfinal;

import androidx.room.Database;
import androidx.room.RoomDatabase;
/**
 * The AppDatabase class is responsible for defining the Room database and providing access to the DAO.
 * This database holds the favorite locations data.
 *
 * @author JingYi Li
 */
@Database(entities = {FavoriteLocation.class}, version = 4, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FavoriteLocationDao favoriteLocationDao();

}
