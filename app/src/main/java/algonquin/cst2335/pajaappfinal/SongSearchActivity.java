package algonquin.cst2335.pajaappfinal;
/*
-------------------------------------------------------
Course: CST 2335 - Mobile Graphical Interface Programming
Final Project: Deezer Song Search API
Student Name: Allan Torres
Student Number: 041022473
-------------------------------------------------------
*/

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity for searching and displaying songs using the Deezer Song Search API.
 */
public class SongSearchActivity extends AppCompatActivity implements IRecyclerView {

    private RecyclerView resultList;
    private RequestQueue requestQueue;
    private EditText search;
    private Button searchButton;
    private String artistName;
    private List<Artist> artistList;
    TextView textView;
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "MyPreferences";
    private static final String SEARCH_TEXT_KEY = "searchText";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.song_search_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.song_favorite_item) {
            Intent favorite = new Intent(SongSearchActivity.this, SongSearchFavorites.class);
            startActivity(favorite);
        } else if (item.getItemId() == R.id.song_about_item) {
            Toast.makeText(SongSearchActivity.this, getString(R.string.song_about_message), Toast.LENGTH_SHORT)
                    .show();
        } else {
            showInstructionDialog(this);
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_search);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.song_welcome_text));
        setSupportActionBar(toolbar);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        resultList = findViewById(R.id.artist_result_list);
        resultList.setHasFixedSize(true);
        resultList.setLayoutManager(new LinearLayoutManager(this));

        search = findViewById(R.id.search_editText);
        searchButton = findViewById(R.id.search_button);
        textView = findViewById(R.id.results);
        search.clearFocus();

        // Retrieve saved text and set it to the EditText
        String savedText = getSavedSearchText();
        if (!savedText.isEmpty()) {
            search.setText(savedText);
        }

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        searchButton.setOnClickListener(click -> {
            artistName = search.getText().toString().trim();
            textView.setText("");
            if (artistName.isBlank() || artistName.isEmpty()) {
                Toast.makeText(SongSearchActivity.this, getString(R.string.song_erro_search_text), Toast.LENGTH_SHORT)
                        .show();
            } else {
                artistList = new ArrayList<>();
                fetchArtist();
                textView.setText(getString(R.string.results)); // Change NO HARD CODE!!!

            }
        });
    }

    /**
     * Fetches the list of artists using the Deezer Song Search API based on the entered search query.
     */
    private void fetchArtist() {
        String url = "https://api.deezer.com/search/artist/?q=" + artistName;

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // Clear the artistList before adding new items
                    artistList.clear();
                    JSONArray items = response.getJSONArray("data");
                    for (int i = 0; i < items.length(); i++) {
                        JSONObject jsonObject = items.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        String picture = jsonObject.getString("picture_medium");
                        String tracklist = jsonObject.getString("tracklist");
                        Artist artist = new Artist(name, picture, tracklist);
                        artistList.add(artist);
                    }

                    ArtistAdapter adapter = new ArtistAdapter(SongSearchActivity.this, artistList, SongSearchActivity.this);
                    resultList.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(SongSearchActivity.this, getString(R.string.song_erro_json), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SongSearchActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(SongSearchActivity.this, SongSearchResultsAlbums.class);
        intent.putExtra("artistName", artistList.get(position).getName());
        intent.putExtra("artistPoster", artistList.get(position).getPoster());
        intent.putExtra("artistTracklist", artistList.get(position).getTracklist());
        startActivity(intent);
    }

    /**
     * Saves the entered search text in SharedPreferences.
     * @param text The search text to be saved.
     */
    private void saveSearchText(String text) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SEARCH_TEXT_KEY, text);
        editor.apply();
    }

    /**
     * Retrieves the saved search text from SharedPreferences.
     * @return The saved search text.
     */
    private String getSavedSearchText() {
        return sharedPreferences.getString(SEARCH_TEXT_KEY, "");
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Save the search text when the activity is stopped
        saveSearchText(search.getText().toString().trim());
    }

    /**
     * Displays an instruction dialog.
     * @param context The context for creating the dialog.
     */
    public static void showInstructionDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.song_instruction_title));
        builder.setMessage(context.getString(R.string.song_instruction));
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
