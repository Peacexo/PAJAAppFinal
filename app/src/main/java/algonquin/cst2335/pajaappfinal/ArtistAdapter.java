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
 * Adapter class for displaying artists in a RecyclerView.
 * This adapter binds data from a List of Artist objects to the RecyclerView.
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
public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistHolder> {
    private Context context;
    private List<Artist> artistList;
    private final IRecyclerView recyclerViewInterface;

    /**
     * Constructs a new ArtistAdapter.
     *
     * @param context            The context of the activity or fragment.
     * @param artistList         The list of artists to be displayed.
     * @param recyclerViewInterface The interface for handling item click events.
     */
    public ArtistAdapter(Context context, List<Artist> artistList, IRecyclerView recyclerViewInterface) {
        this.context = context;
        this.artistList = artistList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ArtistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_search_item, parent, false);
        return new ArtistHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistHolder holder, int position) {
        Artist artist = artistList.get(position);
        holder.artist_name.setText(artist.getName());
        Glide.with(context).load(artist.getPoster()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return artistList.size();
    }

    /**
     * ViewHolder class to hold artist item views.
     */
    public class ArtistHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView artist_name;

        public ArtistHolder(@NonNull View itemView, IRecyclerView recyclerViewInterface) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            artist_name = itemView.findViewById(R.id.artist_name);
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
