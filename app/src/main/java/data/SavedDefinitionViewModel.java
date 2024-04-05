/**
 * Author: Peace Iyunade
 * Lab section: CST2355 022
 * Creation Date: 31st March 2024
 */
package data;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;

import algonquin.cst2335.pajaappfinal.DefinitionDatabase;
import algonquin.cst2335.pajaappfinal.SavedDefinitionDAO;
import algonquin.cst2335.pajaappfinal.SavedDefinitionDic;
/**
 * The ViewModel class for managing saved definitions in the PAJA Dictionary app.
 * This class extends AndroidViewModel to provide application context-awareness.
 *
 * @author Peace Iyunade
 * @version March 31, 2024 (Final Version)
 */


public class SavedDefinitionViewModel extends AndroidViewModel {
private SavedDefinitionDAO savedDefinitionDAO;
    private LiveData<List<SavedDefinitionDic>> savedDefinitions;

    /**
     * Constructs a new SavedDefinitionViewModel instance.
     *
     * @param application The application context.
     */


    public SavedDefinitionViewModel(Application application) {
        super(application);
        // Initialize the database instance
        DefinitionDatabase database = DefinitionDatabase.getInstance(application);
        if (database != null) {
            savedDefinitionDAO = database.savedDefinitionDAO();
            savedDefinitions = savedDefinitionDAO.getAllSavedDefinitions();
        } else {
            // Handle database initialization failure
            Log.e("SavedDefinitionViewModel", "Database instance is null. Unable to initialize DAO and retrieve saved definitions.");
            savedDefinitions = new MutableLiveData<>();
        }
    }
    /**
     * Retrieves all saved definitions as LiveData.
     *
     * @return LiveData containing a list of saved definitions.
     */
    public LiveData<List<SavedDefinitionDic>> getAllSavedDefinitions() {
        return savedDefinitions;
    }
    /**
     * Inserts a new saved definition into the database.
     *
     * @param definition The definition to be inserted.
     */

    public void insert(SavedDefinitionDic definition) {
        new Thread(() -> savedDefinitionDAO.insertDefinition(definition)).start();
    }

    /**
     * Deletes a saved definition from the database.
     *
     * @param definition The definition to be deleted.
     */
    public void delete(SavedDefinitionDic definition) {
        new Thread(() -> {
            savedDefinitionDAO.deleteDefinition(definition);
        }).start();
    }

}

