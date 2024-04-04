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

import java.util.ArrayList;
import java.util.List;

import algonquin.cst2335.pajaappfinal.Album;
import algonquin.cst2335.pajaappfinal.IRecyclerView;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumHolder>{

    private Context context;
    private List<Album> albumList;
    private final IRecyclerView recyclerView;

    public AlbumAdapter (Context context, List<Album>albumList, IRecyclerView recyclerView){
        this.context = context;
        this.albumList = albumList;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public AlbumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_search_item,parent, false);
        return new AlbumHolder(view , recyclerView);
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

    public class AlbumHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView album_name;
        public AlbumHolder(@NonNull View itemView, IRecyclerView recyclerView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            album_name = itemView.findViewById(R.id.artist_name);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(recyclerView !=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            recyclerView.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
}