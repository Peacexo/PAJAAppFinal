package algonquin.cst2335.pajaappfinal;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.SongHolder> {
    private Context context;
    private List<Song> songList;
    private final IRecyclerView recyclerViewinterface;
    private SongDAO songDao;

    public FavoriteAdapter(Context context, List<Song> songList, IRecyclerView iRecyclerView) {
        this.context = context;
        this.songList = songList;
        recyclerViewinterface = iRecyclerView;
        songDao = SongSearchDBSingleton.getInstance(context).songDAO();
    }

    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_search_song_favorite_list, parent, false);
        return new SongHolder(view, recyclerViewinterface);
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder holder, int position) {
        Song song = songList.get(position);
        holder.song_title.setText(song.getTitle());
        String minutes = convertSecondsToMinutes(song.getDuration());
        holder.song_duration.setText(minutes);
        holder.song_track_position.setText("#" + song.getTrackPosition());

        // Add click listener to delete button
        holder.delete_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirm Delete")
                        .setMessage("Are you sure you want to delete this song?")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Perform delete operation
                                int adapterPosition = holder.getAdapterPosition();
                                if (adapterPosition != RecyclerView.NO_POSITION) {
                                    // Remove item from list and notify adapter
                                    Song deletedSong = songList.get(adapterPosition);
                                    songList.remove(adapterPosition);
                                    Executor executor = Executors.newSingleThreadExecutor();
                                    executor.execute(() -> {
                                                try {
                                                    songDao.deleteSong(deletedSong);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                    Toast.makeText(context, "Error removing song to favorites", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                    notifyItemRemoved(adapterPosition);
                                    Toast.makeText(context, "Song deleted: " + deletedSong.getTitle(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Cancel delete operation
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public static String convertSecondsToMinutes(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }

    public class SongHolder extends RecyclerView.ViewHolder {

        TextView song_title, song_duration, song_track_position;
        Button delete_bttn;

        public SongHolder(@NonNull View itemView, IRecyclerView recyclerViewInterface) {
            super(itemView);

            song_title = itemView.findViewById(R.id.song_title);
            song_duration = itemView.findViewById(R.id.song_duration);
            song_track_position = itemView.findViewById(R.id.song_track_position);
            delete_bttn = itemView.findViewById(R.id.delete_bttn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewinterface != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            recyclerViewinterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
