package algonquin.cst2335.pajaappfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter class for displaying songs in a RecyclerView.
 */
public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {
    private Context context;
    private List<Song> songList;
    private final IRecyclerView recyclerViewInterface;

    /**
     * Constructor for the SongAdapter.
     *
     * @param context              The context of the activity or fragment.
     * @param songList             The list of songs to display.
     * @param recyclerViewInterface The interface for handling item click events.
     */
    public SongAdapter(Context context, List<Song> songList, IRecyclerView recyclerViewInterface) {
        this.context = context;
        this.songList = songList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_search_song_list, parent, false);
        return new SongHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder holder, int position) {
        Song song = songList.get(position);
        holder.song_title.setText(song.getTitle());
        String minutes = convertSecondsToMinutes(song.getDuration());
        holder.song_duration.setText(minutes);
        holder.song_track_position.setText("#" + song.getTrackPosition());
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    /**
     * Converts seconds to minutes and seconds format.
     *
     * @param seconds The duration in seconds.
     * @return The duration formatted as "mm:ss".
     */
    public static String convertSecondsToMinutes(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }

    /**
     * ViewHolder class for holding the views of each song item.
     */
    public class SongHolder extends RecyclerView.ViewHolder {
        TextView song_title, song_duration, song_track_position;

        /**
         * Constructor for the SongHolder.
         *
         * @param itemView The view of the song item.
         * @param recyclerViewInterface The interface for handling item click events.
         */
        public SongHolder(@NonNull View itemView, IRecyclerView recyclerViewInterface) {
            super(itemView);

            song_title = itemView.findViewById(R.id.song_title);
            song_duration = itemView.findViewById(R.id.song_duration);
            song_track_position = itemView.findViewById(R.id.song_track_position);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
