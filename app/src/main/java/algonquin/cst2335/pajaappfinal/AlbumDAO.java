package algonquin.cst2335.pajaappfinal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Represents a Data Access Object (DAO) for handling Album database operations.
 *
 * -------------------------------------------------------
 * Course: CST 2335 - Mobile Graphical Interface Programming
 * Final Project: Deezer Song Search API
 * Student Name: Allan Torres
 * Student Number: 041022473
 * -------------------------------------------------------
 */
@Dao
public interface AlbumDAO {
    /**
     * Inserts an album into the database.
     *
     * @param a The album object to be inserted.
     */
    @Insert
    void insertAlbum(Artist a);

    /**
     * Retrieves all albums from the database.
     *
     * @return A list of all albums stored in the database.
     */
    @Query("Select * from Album")
    List<Album> getAllAlbums();

    /**
     * Deletes an album from the database.
     *
     * @param a The album object to be deleted.
     */
    @Delete
    void deleteArtist(Album a);
}
