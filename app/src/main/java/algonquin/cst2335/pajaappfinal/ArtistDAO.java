package algonquin.cst2335.pajaappfinal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Data Access Object (DAO) interface for managing Artist entities in the Room database.
 * This interface defines methods for inserting, querying, and deleting artists.
 *
 * <p>
 * -------------------------------------------------------
 * Course: CST 2335 - Mobile Graphical Interface Programming
 * Final Project: Deezer Song Search API
 * Student Name: Allan Torres
 * Student Number: 041022473
 * -------------------------------------------------------
 * </p>
 */
@Dao
public interface ArtistDAO {
    /**
     * Inserts a new artist into the database.
     *
     * @param a The artist to be inserted.
     */
    @Insert
    void insertArtist(Artist a);

    /**
     * Retrieves all artists from the database.
     *
     * @return A list of all artists in the database.
     */
    @Query("SELECT * FROM Artist")
    List<Artist> getAllArtists();

    /**
     * Deletes an artist from the database.
     *
     * @param a The artist to be deleted.
     */
    @Delete
    void deleteArtist(Artist a);
}
