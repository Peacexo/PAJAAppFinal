package algonquin.cst2335.pajaappfinal;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {SavedDefinitionDic.class}, version = 1)

public abstract class DefinitionDatabase extends RoomDatabase {
    private static DefinitionDatabase instance;
    public abstract SavedDefinitionDAO savedDefinitionDAO();

    public static synchronized DefinitionDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            DefinitionDatabase.class, "definitions_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public static synchronized void destroyInstance() {
        instance = null;
    }

}
