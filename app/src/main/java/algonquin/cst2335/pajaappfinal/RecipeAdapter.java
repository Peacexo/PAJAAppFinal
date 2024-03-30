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

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> recipesList;
    private Context context;

    public RecipeAdapter(List<Recipe> recipesList, Context context) {
        this.recipesList = recipesList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //recuperar a view com o item da lista, o layout
        View recipesList = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_row,parent,false);

        return new RecipeViewHolder(recipesList);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        //aqui eu exibo proriamente o item
        Recipe recipeRow = recipesList.get(position);


        holder.recipeTitleTextView.setText(recipeRow.getRecipeTitle());
        holder.recipeIdTextView.setText(String.valueOf(recipeRow.getRecipeId()));

        // Load recipe image using Glide or any other image loading library
        Glide.with(context)
                .load(recipeRow.getRecipeImageUrl())
                .placeholder(R.drawable.recipe_placeholder_image)
                .into(holder.recipeImageView);
    }

    @Override
    public int getItemCount() {

        return recipesList.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder{
        TextView recipeIdTextView;
        TextView recipeTitleTextView;
        ImageView recipeImageView;
        public RecipeViewHolder(@NonNull View itemView) {

            super(itemView);
            recipeImageView = itemView.findViewById(R.id.recipeImage);
            recipeTitleTextView = itemView.findViewById(R.id.recipeTitle);
            recipeIdTextView = itemView.findViewById(R.id.recipeId);
        }
    }
}
