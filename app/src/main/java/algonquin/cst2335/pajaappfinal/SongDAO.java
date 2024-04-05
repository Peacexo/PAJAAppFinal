package algonquin.cst2335.pajaappfinal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Data Access Object (DAO) interface for managing songs in the database.
 */
@Dao
public interface SongDAO {
    /**
     * Inserts a song into the database.
     *
     * @param s The song to be inserted.
     */
    @Insert
    void insertSong(Song s);

    /**
     * Retrieves all songs from the database.
     *
     * @return A list of all songs in the database.
     */
    @Query("SELECT * FROM Song")
    List<Song> getAllSong();

    /**
     * Deletes a song from the database.
     *
     * @param s The song to be deleted.
     */
    @Delete
    void deleteSong(Song s);
}
