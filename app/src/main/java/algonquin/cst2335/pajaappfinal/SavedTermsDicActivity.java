package algonquin.cst2335.pajaappfinal;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import data.SavedDefinitionViewModel;

public class SavedTermsDicActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SavedTermsAdapter adapter;
    private SavedDefinitionViewModel savedDefinitionViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.saved_terms_dic_layout);
//
//        recyclerView = findViewById(R.id.recyclerViewSavedTerms);
//
//        // Create an empty adapter
//        adapter = new SavedTermsAdapter(new ArrayList<>());
//
//        // Set the adapter to the RecyclerView
//        recyclerView.setAdapter(adapter);
//
//        // Set the layout manager
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        savedDefinitionViewModel = new SavedDefinitionViewModel(this);
//
//        savedDefinitionViewModel.getAllSavedDefinitions().observe(this, new Observer<List<SavedDefinitionDic>>() {
//            @Override
//            public void onChanged(List<SavedDefinitionDic> savedDefinitions) {
//                // Update the adapter with the new list of saved definitions
//                adapter.updateSavedDefinitions(savedDefinitions);
//            }
//        });
//
//    }

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
    }

    }
