package algonquin.cst2335.pajaappfinal;
/*
-------------------------------------------------------
Course: CST 2335 - Mobile Graphical Interface Programming
Final Project: Deezer Song Search API
Student Name: Allan Torres
Student Number: 041022473
-------------------------------------------------------
*/

import android.content.Context;

import androidx.room.Room;

/**
 * Singleton class for accessing the SongSearchDatabase instance.
 */
public class SongSearchDBSingleton {

    private final String databaseName = "SONG_SEARCH_DB";
    private static volatile SongSearchDatabase instance;

    /**
     * Get the singleton instance of the SongSearchDatabase.
     * @param context The application context.
     * @return The SongSearchDatabase instance.
     */
    public static SongSearchDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (SongSearchDBSingleton.class) {
                if (instance == null) {
                    // Create a new instance of SongSearchDatabase
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
