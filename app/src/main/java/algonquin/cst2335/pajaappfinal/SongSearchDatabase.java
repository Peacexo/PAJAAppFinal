package algonquin.cst2335.pajaappfinal;
/*
-------------------------------------------------------
Course: CST 2335 - Mobile Graphical Interface Programming
Final Project: Deezer Song Search API
Student Name: Allan Torres
Student Number: 041022473
-------------------------------------------------------
*/

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Room Database class for managing entities related to Deezer Song Search API.
 */
@Database(entities = {Artist.class, Album.class, Song.class}, version=1)
public abstract class SongSearchDatabase extends RoomDatabase {

    /**
     * Get the Data Access Object (DAO) for managing Artist entities.
     * @return The ArtistDAO object.
     */
    public abstract ArtistDAO artistDAO();

    /**
     * Get the Data Access Object (DAO) for managing Album entities.
     * @return The AlbumDAO object.
     */
    public abstract AlbumDAO albumDAO();

    /**
     * Get the Data Access Object (DAO) for managing Song entities.
     * @return The SongDAO object.
     */
    public abstract SongDAO songDAO();
}
