package algonquin.cst2335.pajaappfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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

        albums = findViewById(R.id.results_list);
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
            albumList.clear();
                try {
                    JSONArray items = response.getJSONArray("data");

                    for (int i = 0; i < items.length(); i++) {
                        JSONObject albums = items.getJSONObject(i);
                        String name = albums.getString("album");
//                        String cover = albums.getString("cover_medium");
//                        String tracklist = albums.getString("tracklist");
                        Album album = new Album (name);
                        albumList.add(album);
                    }
//                    Adapter adapter = new Adapter(SongSearchResultsAlbums.this, albumList, SongSearchResultsAlbums.this);
//                    albums.setAdapter(adapter);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SongSearchResultsAlbums.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(int position) {

    }
}