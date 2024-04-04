package algonquin.cst2335.pajaappfinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import data.SavedDefinitionViewModel;

import java.util.ArrayList;


public class DictionaryResults extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DefinitionsAdapter adapter;
    private Button saveTermButton;
    private SavedDefinitionViewModel savedDefinitionViewModel;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dictionary_results);
        // Initialize ViewModel
        //savedDefinitionViewModel = new ViewModelProvider(this).get(SavedDefinitionViewModel.class);
        // Initialize ViewModel with Application instance
        savedDefinitionViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(SavedDefinitionViewModel.class);

        // Find the TextView for the search term
        TextView searchTermTextView = findViewById(R.id.searchTextView);

        // Retrieve the search term from the intent extras
        String searchTerm = getIntent().getStringExtra("searchTerm");

        // Set the search term to the TextView
        searchTermTextView.setText("Search Term: " + searchTerm);

        // Initialize recyclerView
        recyclerView = findViewById(R.id.recyclerViewSearchResults);

        adapter = new DefinitionsAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Retrieve the list of definitions from the intent extras
        ArrayList<String> definitions = getIntent().getStringArrayListExtra("definitions");
        if (definitions != null) {
            // Update the adapter with the list of definitions
            adapter.updateDefinitions(definitions);
        } else {
            Toast.makeText(this, "No definitions found", Toast.LENGTH_SHORT).show();
        }
        // Find the Save Term button
        saveTermButton = findViewById(R.id.saveTermButton);

        // Set OnClickListener for Save Term button
        saveTermButton.setOnClickListener(v -> {
            // Perform actions when Save Term button is clicked
            saveTermToDatabase(searchTerm, definitions);
        });

        Button viewSavedTermsButton = findViewById(R.id.viewSavedTermsButton);
        viewSavedTermsButton.setOnClickListener(v -> {
            // Start the SavedTermsActivity
            Intent intent = new Intent(DictionaryResults.this, SavedTermsDicActivity.class);
            startActivity(intent);
        });
    }
        private void saveTermToDatabase(String searchTerm, ArrayList<String> definitions) {
        // Create a new SavedDefinition object with the searchTerm and definitions
        SavedDefinitionDic savedDefinition = new SavedDefinitionDic(searchTerm, definitions);

        // Insert the SavedDefinition object into the database using your ViewModel
        savedDefinitionViewModel.insert(savedDefinition);

        // Show a toast message indicating that the term has been saved
        Toast.makeText(this, "Term saved successfully", Toast.LENGTH_SHORT).show();
    }
}

