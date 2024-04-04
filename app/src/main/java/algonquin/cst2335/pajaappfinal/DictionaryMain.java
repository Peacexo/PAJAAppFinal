package algonquin.cst2335.pajaappfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DictionaryMain extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DefinitionsAdapter adapter;
    private EditText searchEditText;
    private SharedPreferences sharedPreferences;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.help_menu_dic, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.help_dic) {
            // Display an AlertDialog with instructions
            showHelpDialog();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showHelpDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Help");
        builder.setMessage("Instructions for using the interface:\n\n1. Enter a word to search in the provided field.\n2. Click on the search button to retrieve definitions.\n3. Tap on a definition to view detailed information.");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_main);
        Toolbar toolbar = findViewById(R.id.toolbar_help_dic);

        // Set the Toolbar as the support action bar
        setSupportActionBar(toolbar);
        // Remove the title (app name) from the Toolbar
        getSupportActionBar().setTitle("");


        ImageView searchButton = findViewById(R.id.searchButton);
        EditText searchEditText = findViewById(R.id.searchEditText);
        sharedPreferences = getSharedPreferences("SearchPrefs", Context.MODE_PRIVATE);

        // Retrieve the last search term from SharedPreferences and display it in the EditText field
        String lastSearchTerm = sharedPreferences.getString("lastSearchTerm", "");
        searchEditText.setText(lastSearchTerm);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchTerm = searchEditText.getText().toString().trim();
                if (!searchTerm.isEmpty()) {
                    // Save the search term to SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("lastSearchTerm", searchTerm);
                    editor.apply();

                    searchDefinition(searchTerm);
                }
            }
        });

    }
    private void searchDefinition(String searchTerm) {
        String url = "https://api.dictionaryapi.dev/api/v2/entries/en/" + searchTerm;

        // Create a StringRequest with GET method
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Parse the response and extract the list of definitions
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            List<String> definitions = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                JSONArray meaningsArray = jsonObject.getJSONArray("meanings");
                                for (int j = 0; j < meaningsArray.length(); j++) {
                                    JSONObject meaningObj = meaningsArray.getJSONObject(j);
                                    JSONArray definitionsArray = meaningObj.getJSONArray("definitions");
                                    for (int k = 0; k < definitionsArray.length(); k++) {
                                        JSONObject definitionObj = definitionsArray.getJSONObject(k);
                                        String definition = definitionObj.getString("definition");
                                        definitions.add(definition);
                                    }
                                }
                            }
                            // Start the next activity and pass the list of definitions
                            Intent intent = new Intent(DictionaryMain.this, DictionaryResults.class);
                            intent.putStringArrayListExtra("definitions", (ArrayList<String>) definitions);
                            intent.putExtra("searchTerm", searchTerm);
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle Volley error
                // Handle Volley error
                error.printStackTrace();
                // Show Snackbar for search error
                showSnackbar("Error: " + error.getMessage());
                // Toast.makeText(DictionaryMain.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    // Method to display a Snackbar
    private void showSnackbar(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }

}
