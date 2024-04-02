package algonquin.cst2335.pajaappfinal;

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
        albumList = new ArrayList<>();
        albums = findViewById(R.id.results_list);
        albums.setHasFixedSize(true);
        albums.setLayoutManager(new LinearLayoutManager(this));
        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();
        artistPic = findViewById(R.id.artist_pic);
        artist_name = findViewById(R.id.artist_name);

        String name, poster, tracklist;
        name = getIntent().getStringExtra("artistName");
        poster = getIntent().getStringExtra("artistPoster");
        tracklist = getIntent().getStringExtra("artistTracklist");
        artist = new Artist(name, poster, tracklist);

        Glide.with(this).load(poster).into(artistPic);
        artist_name.setText(artist.getName());


        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, tracklist, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    albumList.clear();
                    JSONArray data = response.getJSONArray("data");

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

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(SongSearchResultsAlbums.this, SongSearchAlbumFocus.class);
        intent.putExtra("artistName", albumList.get(position).getName());
        intent.putExtra("artistPoster", albumList.get(position).getCover());
        intent.putExtra("artistTracklist", albumList.get(position).getTracklist());
        startActivity(intent);
    }
}