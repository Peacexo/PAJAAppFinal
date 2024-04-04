package algonquin.cst2335.pajaappfinal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SongDAO {
    @Insert
    void insertSong(Song s);
    @Query("Select * from Song")
    List<Song> getAllSong();
    @Delete
    void deleteSong(Song s);
}
