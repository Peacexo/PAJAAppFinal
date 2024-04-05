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

/**
 * Represents an adapter for the RecyclerView used to display a list of albums.
 *
 * <p>
 * -------------------------------------------------------
 * Course: CST 2335 - Mobile Graphical Interface Programming
 * Final Project: Deezer Song Search API
 * Student Name: Allan Torres
 * Student Number: 041022473
 * -------------------------------------------------------
 * </p>
 */
public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumHolder> {

    private Context context;
    private List<Album> albumList;
    private final IRecyclerView recyclerView;

    /**
     * Constructs a new AlbumAdapter with the specified context, list of albums, and RecyclerView interface.
     *
     * @param context      The context of the activity or fragment.
     * @param albumList    The list of albums to be displayed.
     * @param recyclerView The interface for handling item click events.
     */
    public AlbumAdapter(Context context, List<Album> albumList, IRecyclerView recyclerView) {
        this.context = context;
        this.albumList = albumList;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public AlbumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_search_item, parent, false);
        return new AlbumHolder(view, recyclerView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumHolder holder, int position) {
        Album album = albumList.get(position);
        holder.album_name.setText(album.getName());
        Glide.with(context).load(album.getCover()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    /**
     * Inner ViewHolder class to hold the views of each item in the RecyclerView.
     */
    public class AlbumHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView album_name;

        public AlbumHolder(@NonNull View itemView, IRecyclerView recyclerView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            album_name = itemView.findViewById(R.id.artist_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerView != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            recyclerView.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
