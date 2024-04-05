/**
 * Author: Peace Iyunade
 * Lab section: CST2335 022
 * Creation Date: 31st March 2024
 */
package algonquin.cst2335.pajaappfinal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
/**
 * This class represents an adapter for populating a RecyclerView with saved terms and their definitions.
 * It provides methods to update the list of saved definitions and handle delete button clicks.
 * Implements the OnDeleteClickListener interface to handle delete events.
 * @author Peace Iyunade
 * @version March 31, 2024 (Final Version)
 */

public class SavedTermsAdapter extends RecyclerView.Adapter<SavedTermsAdapter.SavedTermViewHolder>{

    private List<SavedDefinitionDic> savedDefinitions;
    private Context context;
    private OnDeleteClickListener onDeleteClickListener;
    /**
     * Constructs a SavedTermsAdapter with the specified list of saved definitions and context.
     * @param savedDefinitions The list of saved definitions to be displayed.
     * @param context The context in which the adapter will operate.
     */
    public SavedTermsAdapter(List<SavedDefinitionDic> savedDefinitions, Context context) {
        this.savedDefinitions = savedDefinitions;
        this.context = context;
    }
    /**
     * Updates the list of saved definitions with new data.
     * @param newSavedDefinitions The new list of saved definitions.
     */
    public void updateSavedDefinitions(List<SavedDefinitionDic> newSavedDefinitions) {
        if (savedDefinitions != null) {
            savedDefinitions.clear();
            if (newSavedDefinitions != null) {
                savedDefinitions.addAll(newSavedDefinitions);
            }
            notifyDataSetChanged();
        }
    }
    /**
     * Interface definition for a callback to be invoked when a delete button is clicked.
     */
    public interface OnDeleteClickListener {
        void onDeleteClick(SavedDefinitionDic savedDefinition); // Method to handle delete event
    }
    /**
     * Sets the listener to be invoked when a delete button is clicked.
     * @param onDeleteClickListener The listener to be invoked.
     */
    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @NonNull
    @Override
    public SavedTermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_saved_term_dic, parent, false);
        return new SavedTermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedTermViewHolder holder, int position) {
        SavedDefinitionDic savedDefinition = savedDefinitions.get(position);
        holder.bind(savedDefinition);
    }

    @Override
    public int getItemCount() {
        return savedDefinitions.size();
    }
    /**
     * This class represents a ViewHolder for individual saved term items.
     */

    public class SavedTermViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView searchTermTextView;
        Button deleteButtonDic;
        /**
         * Constructs a SavedTermViewHolder for the given itemView.
         * @param itemView The view representing an individual item within the RecyclerView.
         */

        public SavedTermViewHolder(@NonNull View itemView) {
            super(itemView);
            searchTermTextView = itemView.findViewById(R.id.searchTermTextView);
            deleteButtonDic = itemView.findViewById(R.id.deleteButtonDic);
            //definitionTextView = itemView.findViewById(R.id.definitionTextView);
            itemView.setOnClickListener(this);
            deleteButtonDic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Call onDeleteClick method of the interface
                    if (onDeleteClickListener != null) {
                        onDeleteClickListener.onDeleteClick(savedDefinitions.get(getAdapterPosition()));
                    }
                }
            });
        }
        /**
         * Binds the provided saved definition to the ViewHolder item.
         * @param savedDefinition The saved definition to bind.
         */
        public void bind(SavedDefinitionDic savedDefinition) {
            searchTermTextView.setText(savedDefinition.getSearchTerm());
            List<String> definitions = savedDefinition.getDefinitions();
        }

        @Override
        public void onClick(View v) {
            SavedDefinitionDic savedDefinition = savedDefinitions.get(getAdapterPosition());
            showDefinitionDialog(savedDefinition);
        }
        /**
         * Displays a dialog with the definitions of the saved term.
         * @param savedDefinition The SavedDefinitionDic object containing the term and its definitions.
         */
        private void showDefinitionDialog(SavedDefinitionDic savedDefinition) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(savedDefinition.getSearchTerm());
            List<String> definitions = savedDefinition.getDefinitions();
            if (definitions != null && !definitions.isEmpty()) {
                String definitionString = TextUtils.join("\n", definitions);
                builder.setMessage(definitionString);
            } else {
                builder.setMessage("No definition available.");
            }
            builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }
    }
}
