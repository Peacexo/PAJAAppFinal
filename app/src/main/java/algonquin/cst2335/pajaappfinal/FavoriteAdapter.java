
package algonquin.cst2335.pajaappfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.SongHolder> {
    private Context context;
    private List<Song> songList;
    private final IRecyclerView recyclerViewinterface;
    public FavoriteAdapter(Context context, List<Song> songList, IRecyclerView iRecyclerView){
        this.context = context;
        this.songList = songList;
        recyclerViewinterface = iRecyclerView;
    }
    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_search_song_favorite_list,parent, false);
        return new SongHolder(view, recyclerViewinterface);
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder holder, int position) {
        Song song = songList.get(position);
        holder.song_title.setText(song.getTitle());
        String minutes = convertSecondsToMinutes(song.getDuration());
        holder.song_duration.setText(minutes);
        holder.song_track_position.setText("#"+song.getTrackPosition());
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

    public class SongHolder extends RecyclerView.ViewHolder{

        TextView song_title, song_duration, song_track_position;

        public  SongHolder(@NonNull View itemView, IRecyclerView recyclerViewInterface) {
            super(itemView);

            song_title = itemView.findViewById(R.id.song_title);
            song_duration = itemView.findViewById(R.id.song_duration);
            song_track_position = itemView.findViewById(R.id.song_track_position);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewinterface !=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            recyclerViewinterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
