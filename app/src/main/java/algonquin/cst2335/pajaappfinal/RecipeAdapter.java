package algonquin.cst2335.pajaappfinal;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private List<Recipe> recipeList;
    private Context context;

    public RecipeAdapter(List<Recipe> recipeList, Context context) {
        this.recipeList = recipeList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View thisRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_row, parent, false);
        return new RecipeViewHolder(thisRow);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.bind(recipe);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int recipeId = recipe.getId();
                // Create intent to open RecipeDetailsActivity
                Intent intent = new Intent(context, RecipeDetailsActivity.class);
                // Pass recipe details as intent extras
                intent.putExtra("recipeId", recipeId);
                intent.putExtra("title", recipe.getTitle());
                intent.putExtra("imageUrl", recipe.getImageUrl());

                // Start RecipeDetailsActivity
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewRecipe;
        private TextView textViewRecipeTitle;
        private TextView textViewRecipeId;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewRecipe = itemView.findViewById(R.id.recipeImage);
            textViewRecipeTitle = itemView.findViewById(R.id.recipeTitle);
            textViewRecipeId = itemView.findViewById(R.id.recipeId);
        }

        public void bind(Recipe recipe) {
            textViewRecipeTitle.setText(recipe.getTitle());
            textViewRecipeId.setText(String.valueOf(recipe.getId()));
            Picasso.get().load(recipe.getImageUrl()).into(imageViewRecipe);
        }
    }
}
