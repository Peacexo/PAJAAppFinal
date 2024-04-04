package algonquin.cst2335.pajaappfinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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


        favorites = findViewById(R.id.favorites_list);
        favorites.setHasFixedSize(true);
        favorites.setLayoutManager(new LinearLayoutManager(this));

        db = SongSearchDBSingleton.getInstance(this);
        songDAO = db.songDAO();
        Executor thread = Executors.newSingleThreadExecutor();
        thread.execute(() -> {
                    favoritSongsList.addAll(songDAO.getAllSong());
                });
        favoriteAdapter = new FavoriteAdapter(SongSearchFavorites.this, favoritSongsList, SongSearchFavorites.this);
        favorites.setAdapter(favoriteAdapter);

    }


    @Override
    public void onItemClick(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Would you like to remove this song from your favorites list?")
                .setTitle("Remove from favorites:")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Song song = favoritSongsList.get(position);

                        Executor executor = Executors.newSingleThreadExecutor();
                        executor.execute(() -> {
                            try {
                                songDAO.deleteSong(song);
                                favorites.getAdapter().notifyItemRemoved(position);
                                favoritSongsList.remove(song);

                            } catch (Exception e) {
                                e.printStackTrace();
                                runOnUiThread(() -> Toast.makeText(SongSearchFavorites.this, "Error removing song to favorites", Toast.LENGTH_SHORT).show());
                            }
                        });
                    }
                }).create().show();
    }
}