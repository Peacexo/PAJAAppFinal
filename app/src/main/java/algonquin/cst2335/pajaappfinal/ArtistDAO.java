package algonquin.cst2335.pajaappfinal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface ArtistDAO {
@Insert
    void insertArtist(Artist a);
@Query("Select * from Artist")
    List<Artist> getAllArtists();
@Delete
    void deleteArtist(Artist a);
}
