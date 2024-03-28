
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

import algonquin.cst2335.pajaappfinal.Artist;
import algonquin.cst2335.pajaappfinal.IRecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.ArtistHolder> {
    private Context context;
    private List<Artist> artistList;
    private final IRecyclerView recyclerViewinterface;
    public Adapter(Context context, List<Artist> artistList, IRecyclerView iRecyclerView){
        this.context = context;
        this.artistList = artistList;
        recyclerViewinterface = iRecyclerView;
    }
    @NonNull
    @Override
    public ArtistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_search_item,parent, false);
        return new ArtistHolder(view, recyclerViewinterface);
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

    public class ArtistHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView artist_name;
        public  ArtistHolder(@NonNull View itemView, IRecyclerView recyclerViewInterface) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            artist_name = itemView.findViewById(R.id.artist_name);
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
