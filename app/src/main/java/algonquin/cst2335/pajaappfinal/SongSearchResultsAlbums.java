package algonquin.cst2335.pajaappfinal;
/*
-------------------------------------------------------
Course: CST 2335 - Mobile Graphical Interface Programming
Final Project: Deezer Song Search API
Student Name: Allan Torres
Student Number: 041022473
-------------------------------------------------------
*/

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Activity for displaying search results albums and navigating to their details.
 */
public class SongSearchResultsAlbums extends AppCompatActivity implements IRecyclerView {
    private RecyclerView albums;
    private RequestQueue requestQueue;
    private ImageView artistPic;
    private TextView artist_name;
    private ArrayList<Album> albumList;
    private Artist artist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_search_results_albums);

        // Initialize views and variables
        albumList = new ArrayList<>();
        albums = findViewById(R.id.results_list);
        albums.setHasFixedSize(true);
        albums.setLayoutManager(new LinearLayoutManager(this));
        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();
        artistPic = findViewById(R.id.artist_pic);
        artist_name = findViewById(R.id.artist_name);

        // Load artist details from intent
        String name, poster, tracklist;
        name = getIntent().getStringExtra("artistName");
        poster = getIntent().getStringExtra("artistPoster");
        tracklist = getIntent().getStringExtra("artistTracklist");
        artist = new Artist(name, poster, tracklist);

        // Display artist details in the UI
        Glide.with(this).load(poster).into(artistPic);
        artist_name.setText(artist.getName());

        // Fetch albums from the tracklist API endpoint
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, tracklist, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    albumList.clear();
                    JSONArray data = response.getJSONArray("data");

                    // Parse album data from JSON response
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject trackObject = data.getJSONObject(i);

                        JSONObject albumObject = trackObject.getJSONObject("album");
                        String albumTitle = albumObject.getString("title");
                        String albumCover = albumObject.getString("cover_medium");
                        String albumTracklist = albumObject.getString("tracklist");

                        Album album = new Album(albumTitle, albumCover, albumTracklist);
                        albumList.add(album);
                    }
                    Log.d("Album List Size", "Size: " + albumList.size());

                    // Set up RecyclerView with album adapter
                    AlbumAdapter adapter = new AlbumAdapter(SongSearchResultsAlbums.this, albumList, SongSearchResultsAlbums.this);
                    albums.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(SongSearchResultsAlbums.this, "Error parsing JSON", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SongSearchResultsAlbums.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }

    /**
     * Called when an item in the album list is clicked.
     * @param position The position of the clicked item in the list.
     */
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(SongSearchResultsAlbums.this, SongSearchAlbumFocus.class);
        intent.putExtra("albumName", albumList.get(position).getName());
        intent.putExtra("albumCover", albumList.get(position).getCover());
        intent.putExtra("albumTracklist", albumList.get(position).getTracklist());
        intent.putExtra("artistName", albumList.get(position).getArtistName());
        startActivity(intent);
    }
}
