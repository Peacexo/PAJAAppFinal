/**
 * RecipeAdapter is a RecyclerView adapter responsible for managing and displaying a list of recipes.
 * Author: Alessandra Prunzel Kittlaus
 * Lab Section: 022
 * Creation Date: 04/04/2024
 * Reference: [1] https://gaandlaneeraja.medium.com/how-to-pass-objects-between-android-activities-86f2cfb61bd4
 */
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

import java.io.Serializable;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private List<Recipe> recipeList;
    private Context context;

    /**
     * Constructor to initialize the RecipeAdapter.
     * @param recipeList The list of recipes to be displayed.
     * @param context The context of the calling activity.
     */
    public RecipeAdapter(List<Recipe> recipeList, Context context) {
        this.recipeList = recipeList;
        this.context = context;
    }

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View thisRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_row, parent, false);
        return new RecipeViewHolder(thisRow);
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.bind(recipe);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create intent to open RecipeDetailsActivity
                Intent intent = new Intent(context, RecipeDetailsActivity.class);
                // Pass recipe object as intent extras. Reference: [1]
                intent.putExtra("recipe", recipe);
                context.startActivity(intent);

                // Start RecipeDetailsActivity
                context.startActivity(intent);
            }
        });
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     * @return The total number of items in the data set.
     */
    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    /**
     * Represents each individual item view in the RecyclerView.
     */
    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewRecipe;
        private TextView textViewRecipeTitle;
        private TextView textViewRecipeId;

        /**
         * Constructor
         * @param itemView The item view associated with the ViewHolder.
         */
        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewRecipe = itemView.findViewById(R.id.recipeImage);
            textViewRecipeTitle = itemView.findViewById(R.id.recipeTitle);
            textViewRecipeId = itemView.findViewById(R.id.recipeId);
        }

        /**
         * Binds(links) the recipe data to the item view.
         * @param recipe The Recipe object to bind.
         */
        public void bind(Recipe recipe) {
            textViewRecipeTitle.setText(recipe.getTitle());
            textViewRecipeId.setText(String.valueOf(recipe.getId()));
            Picasso.get().load(recipe.getImageUrl()).into(imageViewRecipe);
        }
    }
}
