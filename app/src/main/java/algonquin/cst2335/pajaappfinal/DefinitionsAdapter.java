/**
 * Author: Peace Iyunade
 * Lab section: CST2335 022
 * Creation Date: 1st April 2024
 */
package algonquin.cst2335.pajaappfinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * This class represents an adapter for populating a RecyclerView with definitions.
 * It provides methods to update the list of definitions and bind data to individual ViewHolder items.
 * @author Peace Iyunade
 * @version April 1, 2024 (Final Version)
 */

public class DefinitionsAdapter extends RecyclerView.Adapter<DefinitionsAdapter.DefinitionViewHolder> {

    private List<String> definitions;
    /**
     * Constructs a DefinitionsAdapter with the specified list of definitions.
     * @param definitions The list of definitions to be displayed.
     */

    public DefinitionsAdapter(List<String> definitions) {
        this.definitions = definitions;
    }
    /**
     * Updates the list of definitions with new data.
     * @param newDefinitions The new list of definitions.
     */

    public void updateDefinitions(List<String> newDefinitions) {
        definitions.clear();
        definitions.addAll(newDefinitions);
        notifyDataSetChanged();
    }

    /**
     * Creates a new ViewHolder instance for the definition item view.
     * @param parent The ViewGroup into which the new View will be added.
     * @param viewType The view type of the new View.
     * @return A new DefinitionViewHolder instance.
     */

    @NonNull
    @Override
    public DefinitionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_result_dic, parent, false);
        return new DefinitionViewHolder(itemView);
    }

    /**
     * Binds data to the ViewHolder item at the specified position.
     * @param holder The ViewHolder to bind data to.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull DefinitionViewHolder holder, int position) {
        String definition = definitions.get(position);
        holder.bind(definition);
    }
    /**
     * Returns the total number of items in the data set held by the adapter.
     * @return The total number of items in the data set.
     */
    @Override
    public int getItemCount() {
        return definitions.size();
    }

    /**
     * This class represents a ViewHolder for individual definition items.
     */

    public static class DefinitionViewHolder extends RecyclerView.ViewHolder {
        TextView definitionTextView;
        /**
         * Constructs a DefinitionViewHolder for the given itemView.
         * @param itemView The view representing an individual item within the RecyclerView.
         */

        public DefinitionViewHolder(@NonNull View itemView) {
            super(itemView);
            // Ensure that definitionTextView is properly initialized
            definitionTextView = itemView.findViewById(R.id.definitionTextView);
        }

        /**
         * Binds the provided definition to the ViewHolder item.
         * @param definition The definition to bind.
         */
        public void bind(String definition) {
            // Check if definitionTextView is null before setting text
            if (definitionTextView != null) {
                definitionTextView.setText(definition);
            }
        }
    }
}
