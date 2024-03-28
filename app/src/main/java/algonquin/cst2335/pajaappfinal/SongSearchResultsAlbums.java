package algonquin.cst2335.pajaappfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SongSearchResultsAlbums extends AppCompatActivity {
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
    }
}