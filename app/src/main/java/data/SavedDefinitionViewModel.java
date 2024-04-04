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


public class SavedDefinitionViewModel extends AndroidViewModel {
private SavedDefinitionDAO savedDefinitionDAO;
    private LiveData<List<SavedDefinitionDic>> savedDefinitions;

//    public SavedDefinitionViewModel() {
//
//    }

//    public SavedDefinitionViewModel(Context context) {
//        super();
//        // Get the application context
//        Context applicationContext = context.getApplicationContext();
//
//        // Initialize the database instance
//        DefinitionDatabase database = DefinitionDatabase.getInstance(applicationContext);
//        //DefinitionDatabase database = DefinitionDatabase.getInstance(context.getApplicationContext());
//        if (database != null) {
//            savedDefinitionDAO = database.savedDefinitionDAO();
//            savedDefinitions = savedDefinitionDAO.getAllSavedDefinitions();
//        } else {
//            MutableLiveData<List<SavedDefinitionDic>> emptyList = new MutableLiveData<>();
//            emptyList.setValue(new ArrayList<>());
//            savedDefinitions = emptyList;
//            Log.e("SavedDefinitionViewModel", "Database instance is null. Unable to initialize DAO and retrieve saved definitions.");
//
//        }
//    }
//
//    public LiveData<List<SavedDefinitionDic>> getAllSavedDefinitions() {
//        return savedDefinitions;
//    }
//
//
//    public void insert(SavedDefinitionDic definition) {
//        if (savedDefinitionDAO == null) {
//            Log.e("SavedDefinitionViewModel", "savedDefinitionDAO is null. Cannot insert definition.");
//            return;
//        }
//        savedDefinitionDAO.insertDefinition(definition);
//    }
//
//    public void delete(SavedDefinitionDic definition) {
//        savedDefinitionDAO.deleteDefinition(definition);
//    }

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

    public LiveData<List<SavedDefinitionDic>> getAllSavedDefinitions() {
        return savedDefinitions;
    }

//    public void insert(SavedDefinitionDic definition) {
//        if (savedDefinitionDAO != null) {
//            savedDefinitionDAO.insertDefinition(definition);
//        } else {
//            Log.e("SavedDefinitionViewModel", "savedDefinitionDAO is null. Cannot insert definition.");
//        }
//    }
    public void insert(SavedDefinitionDic definition) {
        new Thread(() -> savedDefinitionDAO.insertDefinition(definition)).start();
    }

//    public void delete(SavedDefinitionDic definition) {
//        if (savedDefinitionDAO != null) {
//            savedDefinitionDAO.deleteDefinition(definition);
//        } else {
//            Log.e("SavedDefinitionViewModel", "savedDefinitionDAO is null. Cannot delete definition.");
//        }
//    }
    public void delete(SavedDefinitionDic definition) {
        new Thread(() -> {
            savedDefinitionDAO.deleteDefinition(definition);
        }).start();
    }

}

