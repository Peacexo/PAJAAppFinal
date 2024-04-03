package algonquin.cst2335.pajaappfinal;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import algonquin.cst2335.pajaappfinal.SongSearchDatabase;

public class SongSearchDBSingleton {

    private final String databaseName = "SONG_SEARCH_DB";
    private static volatile SongSearchDatabase instance;

    public static SongSearchDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (SongSearchDBSingleton.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                                    SongSearchDatabase.class, "SONG_SEARCH_DB")
                            .fallbackToDestructiveMigration() // Optional: for handling migrations
                            .build();
                }
            }
        }
        return instance;
    }
}
