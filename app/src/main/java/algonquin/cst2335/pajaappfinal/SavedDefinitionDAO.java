/**
 * Author: Peace Iyunade
 * Lab section: CST2335 022
 * Creation Date: 31st March 2024
 */
package algonquin.cst2335.pajaappfinal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
/**
 * This interface serves as a Data Access Object (DAO) for interacting with the saved definitions database table.
 * It provides methods for inserting new definitions, querying all saved definitions, and deleting a specific definition.
 * Uses Room annotations to define the database operations.
 * @author Peace Iyunade
 * @version March 31, 2024 (Final Version)
 */

@Dao
public interface SavedDefinitionDAO {
    /**
     * Inserts a new saved definition into the database.
     * @param definition The saved definition to be inserted.
     */
    @Insert
    void insertDefinition(SavedDefinitionDic definition);
    /**
     * Retrieves all saved definitions from the database.
     * @return A LiveData object containing a list of all saved definitions.
     */
    @Query("SELECT * FROM saveddefinitions")
    LiveData<List<SavedDefinitionDic>> getAllSavedDefinitions();
    /**
     * Deletes a specific saved definition from the database.
     * @param definition The saved definition to be deleted.
     */
    @Delete
    void deleteDefinition(SavedDefinitionDic definition);
}
