package algonquin.cst2335.pajaappfinal;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {FavoriteLocation.class}, version = 4, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FavoriteLocationDao favoriteLocationDao();

}
