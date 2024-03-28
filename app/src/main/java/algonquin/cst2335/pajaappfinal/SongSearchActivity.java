package algonquin.cst2335.pajaappfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class SongSearchActivity extends AppCompatActivity implements IRecyclerView {

    private RecyclerView favoriteList;
    private RequestQueue requestQueue;
    private EditText search;
    private Button searchButton;
    private String artistName;
    private List<Artist> artistList;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_search);

        favoriteList = findViewById(R.id.favorite_list);
        favoriteList.setHasFixedSize(true);
        favoriteList.setLayoutManager(new LinearLayoutManager(this));
        search = findViewById(R.id.search_editText);
        searchButton = findViewById(R.id.search_button);
        textView = findViewById(R.id.results);
        search.clearFocus();

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        searchButton.setOnClickListener(click -> {
            artistName = search.getText().toString().trim();
            textView.setText("");
            if (artistName.isBlank() || artistName.isEmpty()) {
                Toast.makeText(SongSearchActivity.this, "Insert an artist to search!", Toast.LENGTH_SHORT)
                        .show();
            } else {
                artistList = new ArrayList<>();
                fetchArtist();
                textView.setText("RESULTS"); // Change NO HARD CODE!!!
            }
        });
    }

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
                        String picture = jsonObject.getString("picture");
                        String tracklist = jsonObject.getString("tracklist");
                        Artist artist = new Artist(name, picture, tracklist);
                        artistList.add(artist);
                    }

                    // Move adapter creation and setting outside the loop
                    Adapter adapter = new Adapter(SongSearchActivity.this, artistList, SongSearchActivity.this);
                    favoriteList.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(SongSearchActivity.this, "Error parsing JSON", Toast.LENGTH_SHORT).show();
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
}
