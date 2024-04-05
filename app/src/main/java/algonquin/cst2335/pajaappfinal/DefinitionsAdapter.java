package algonquin.cst2335.pajaappfinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DefinitionsAdapter extends RecyclerView.Adapter<DefinitionsAdapter.DefinitionViewHolder> {

    private List<String> definitions;

    public DefinitionsAdapter(List<String> definitions) {
        this.definitions = definitions;
    }

    public void updateDefinitions(List<String> newDefinitions) {
        definitions.clear();
        definitions.addAll(newDefinitions);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DefinitionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_result_dic, parent, false);
        return new DefinitionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DefinitionViewHolder holder, int position) {
        String definition = definitions.get(position);
        holder.bind(definition);
    }

    @Override
    public int getItemCount() {
        return definitions.size();
    }

    public static class DefinitionViewHolder extends RecyclerView.ViewHolder {
        TextView definitionTextView;

        public DefinitionViewHolder(@NonNull View itemView) {
            super(itemView);
            // Ensure that definitionTextView is properly initialized
            definitionTextView = itemView.findViewById(R.id.definitionTextView);
        }

        public void bind(String definition) {
            // Check if definitionTextView is null before setting text
            if (definitionTextView != null) {
                definitionTextView.setText(definition);
            }
        }
    }
}
