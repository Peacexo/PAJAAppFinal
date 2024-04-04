package algonquin.cst2335.pajaappfinal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AlbumDAO {
    @Insert
    void insertAlbum(Artist a);
    @Query("Select * from Album")
    List<Album> getAllAlbums();
    @Delete
    void deleteArtist(Album a);
}
