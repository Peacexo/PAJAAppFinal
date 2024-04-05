/**
 * Author: Peace Iyunade
 * Lab section: CST2335 022
 * Creation Date: 31st March 2024
 */
package algonquin.cst2335.pajaappfinal;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import data.SavedDefinitionViewModel;

/**
 * This class represents the activity for displaying saved terms and their definitions in a RecyclerView.
 * It allows users to view and delete saved terms from the database.
 * Implements the OnDeleteClickListener interface to handle delete button clicks.
 * @author Peace Iyunade
 * @version March 31, 2024 (Final Version)
 */

public class SavedTermsDicActivity extends AppCompatActivity implements SavedTermsAdapter.OnDeleteClickListener {
    private RecyclerView recyclerView;
    private SavedTermsAdapter adapter;
    private SavedDefinitionViewModel savedDefinitionViewModel;
    /**
     * Initializes the activity layout and sets up necessary components.
     * @param savedInstanceState The saved instance state bundle.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_terms_dic_layout);

        recyclerView = findViewById(R.id.recyclerViewSavedTerms);
        adapter = new SavedTermsAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize SavedDefinitionViewModel with Application instance
        savedDefinitionViewModel = new ViewModelProvider(this).get(SavedDefinitionViewModel.class);
        savedDefinitionViewModel.getAllSavedDefinitions().observe(this, new Observer<List<SavedDefinitionDic>>() {
            @Override
            public void onChanged(List<SavedDefinitionDic> savedDefinitions) {
                // Update the adapter with the new list of saved definitions
                adapter.updateSavedDefinitions(savedDefinitions);
            }

        });
        // Set the delete button click listener
        adapter.setOnDeleteClickListener(this);
    }
    /**
     * Handles the delete button click event.
     * @param savedDefinition The SavedDefinitionDic object to be deleted.
     */
    @Override
    public void onDeleteClick(SavedDefinitionDic savedDefinition) {
        // Handle delete button click here
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Deletion");
        builder.setMessage("Are you sure you want to delete this item?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Delete the item from the database
                savedDefinitionViewModel.delete(savedDefinition);
                Toast.makeText(SavedTermsDicActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing if the user clicks "No"
            }
        });
        builder.create().show();
    }
}
