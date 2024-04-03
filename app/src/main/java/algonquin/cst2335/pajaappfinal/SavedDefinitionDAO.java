package algonquin.cst2335.pajaappfinal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SavedDefinitionDAO {

    @Insert
    void insertDefinition(SavedDefinitionDic definition);

    @Query("SELECT * FROM saveddefinitions")
    LiveData<List<SavedDefinitionDic>> getAllSavedDefinitions();

    @Delete
    void deleteDefinition(SavedDefinitionDic definition);
}
