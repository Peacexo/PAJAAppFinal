package algonquin.cst2335.pajaappfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DictionaryMain extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DefinitionsAdapter adapter;
    private EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_main);

        // Inflate the item layout
        //View itemLayout = getLayoutInflater().inflate(R.layout.item_search_result_dic, null);

        // Find RecyclerView from the inflated item layout
        //RecyclerView recyclerViewSearchResults = itemLayout.findViewById(R.id.recyclerViewSearchResults);
        ImageView searchButton = findViewById(R.id.searchButton);
        EditText searchEditText = findViewById(R.id.searchEditText);

        // Set up RecyclerView and its adapter
        //adapter = new DefinitionsAdapter(new ArrayList<>());
       // recyclerViewSearchResults.setAdapter(adapter);
       // recyclerViewSearchResults.setLayoutManager(new LinearLayoutManager(this));

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchTerm = searchEditText.getText().toString().trim();
                if (!searchTerm.isEmpty()) {
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
                Toast.makeText(DictionaryMain.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Add the request to the RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
