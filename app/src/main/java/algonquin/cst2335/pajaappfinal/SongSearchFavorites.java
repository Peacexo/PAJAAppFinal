package algonquin.cst2335.pajaappfinal;
/*
-------------------------------------------------------
Course: CST 2335 - Mobile Graphical Interface Programming
Final Project: Deezer Song Search API
Student Name: Allan Torres
Student Number: 041022473
-------------------------------------------------------
*/

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Activity for displaying favorite songs and navigating to their details.
 */
public class SongSearchFavorites extends AppCompatActivity implements IRecyclerView{
    private RecyclerView favorites;
    private ArrayList<Song>favoritSongsList = new ArrayList<>();
    private FavoriteAdapter favoriteAdapter;
    private SongDAO songDAO;

    private SongSearchDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_search_favorites);

        // Initialize RecyclerView and adapter
        favorites = findViewById(R.id.favorites_list);
        favorites.setHasFixedSize(true);
        favorites.setLayoutManager(new LinearLayoutManager(this));

        // Initialize database and DAO
        db = SongSearchDBSingleton.getInstance(this);
        songDAO = db.songDAO();

        // Retrieve favorite songs from database and update the RecyclerView
        Executor thread = Executors.newSingleThreadExecutor();
        thread.execute(() -> {
            favoritSongsList.addAll(songDAO.getAllSong());
        });
        favoriteAdapter = new FavoriteAdapter(SongSearchFavorites.this, favoritSongsList, SongSearchFavorites.this);
        favorites.setAdapter(favoriteAdapter);
    }

    /**
     * Called when an item in the favorites list is clicked.
     * @param position The position of the clicked item in the list.
     */
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(SongSearchFavorites.this, SongSearchAlbumFocus.class);
        intent.putExtra("albumName", favoritSongsList.get(position).getAlbumTitle());
        intent.putExtra("albumCover", favoritSongsList.get(position).getCover());
        intent.putExtra("albumTracklist", favoritSongsList.get(position).getTracklist());
        intent.putExtra("artistName", favoritSongsList.get(position).getArtistName());
        startActivity(intent);
    }
}
