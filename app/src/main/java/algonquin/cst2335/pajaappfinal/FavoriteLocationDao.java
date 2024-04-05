package algonquin.cst2335.pajaappfinal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import algonquin.cst2335.pajaappfinal.FavoriteLocation;

@Dao
public interface FavoriteLocationDao {
    @Insert
    void insert(FavoriteLocation location);

    @Query("SELECT * FROM favorite_locations")
    LiveData<List<FavoriteLocation>> getAllFavorites();

    @Query("SELECT * FROM favorite_locations WHERE id = :id")
    FavoriteLocation findById(int id);
    @Delete
    void delete(FavoriteLocation location);

    @Query("SELECT * FROM favorite_locations WHERE is_favorite = 1")
    LiveData<List<FavoriteLocation>> getFavoriteLocations();
    @Update
    void update(FavoriteLocation location);

    @Query("DELETE FROM favorite_locations WHERE is_Favorite = 0")
    void deleteNonFavorites();
}

