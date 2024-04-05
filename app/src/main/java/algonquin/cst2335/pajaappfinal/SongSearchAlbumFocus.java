package algonquin.cst2335.pajaappfinal;
/*
-------------------------------------------------------
Course: CST 2335 - Mobile Graphical Interface Programming
Final Project: Deezer Song Search API
Student Name: Allan Torres
Student Number: 041022473
-------------------------------------------------------
*/

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
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
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Activity for displaying details of a specific album and its songs.
 */
public class SongSearchAlbumFocus extends AppCompatActivity implements IRecyclerView {

    private TextView albumTitle;
    private ImageView albumCover;
    private RecyclerView albumInformation;
    private RequestQueue requestQueue;
    private ArrayList<Song> songList = new ArrayList<>();

    private SongSearchDatabase db;

    private SongDAO songDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_search_album_focus);

        db = SongSearchDBSingleton.getInstance(this);
        songDAO = db.songDAO();

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();
        albumTitle = findViewById(R.id.album_title);
        albumCover = findViewById(R.id.album_cover);
        albumInformation = findViewById(R.id.album_information);
        albumInformation.setHasFixedSize(true);
        albumInformation.setLayoutManager(new LinearLayoutManager(this));

        String album_title = getIntent().getStringExtra("albumName");
        String album_cover = getIntent().getStringExtra("albumCover");
        String album_tracklist = getIntent().getStringExtra("albumTracklist");
        String album_artist = getIntent().getStringExtra("artistName");

        albumTitle.setText(album_title);
        Glide.with(this).load(album_cover).into(albumCover);

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, album_tracklist, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    songList.clear();
                    JSONArray items = response.getJSONArray("data");
                    for (int i = 0; i < items.length(); i++) {
                        JSONObject jsonObject = items.getJSONObject(i);
                        String name = jsonObject.getString("title");
                        int duration = jsonObject.getInt("duration");
                        int trackPosition = jsonObject.getInt("track_position");
                        Song song = new Song(name, duration, trackPosition, album_artist, album_cover, album_title, album_tracklist);
                        songList.add(song);
                    }
                    Log.d("Album List Size", "Size: " + songList.size());
                    SongAdapter adapter = new SongAdapter(SongSearchAlbumFocus.this, songList, SongSearchAlbumFocus.this);
                    albumInformation.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(SongSearchAlbumFocus.this, getString(R.string.song_erro_json), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SongSearchAlbumFocus.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);


    }

    /**
     * Handles the click event on a song item in the album.
     * @param position The position of the clicked song in the list.
     */
    @Override
    public void onItemClick(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.song_message_add_favorite))
                .setTitle(getString(R.string.song_title_question_add_favorite))
                .setNegativeButton(getString(R.string.no_tex), null)
                .setPositiveButton(getString(R.string.yes_text), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Song song = songList.get(position);

                        Executor executor = Executors.newSingleThreadExecutor();
                        executor.execute(() -> {
                            try {
                                songDAO.insertSong(song);
                                runOnUiThread(() -> Toast.makeText(SongSearchAlbumFocus.this, getString(R.string.song_confirmation_add), Toast.LENGTH_SHORT).show());
                            } catch (Exception e) {
                                e.printStackTrace();
                                runOnUiThread(() -> Toast.makeText(SongSearchAlbumFocus.this, getString(R.string.song_erro_add_favorite), Toast.LENGTH_SHORT).show());
                            }
                        });
                    }
                }).create().show();
    }
}
