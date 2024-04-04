package algonquin.cst2335.pajaappfinal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SavedTermsAdapter extends RecyclerView.Adapter<SavedTermsAdapter.SavedTermViewHolder>{
//    private List<SavedDefinitionDic> savedDefinitions;
//
//    public SavedTermsAdapter(List<SavedDefinitionDic> savedDefinitions) {
//        this.savedDefinitions = savedDefinitions;
//    }
//
//        public void updateSavedDefinitions(List<SavedDefinitionDic> newSavedDefinitions) {
//            if (savedDefinitions != null) { // Check if savedDefinitions is not null
//                savedDefinitions.clear();
//                if (newSavedDefinitions != null) { // Check if newSavedDefinitions is not null
//                    savedDefinitions.addAll(newSavedDefinitions);
//                }
//                notifyDataSetChanged();
//            }
//    }
//    @NonNull
//    @Override
//    public SavedTermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_saved_term_dic, parent, false);
//        return new SavedTermViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull SavedTermViewHolder holder, int position) {
//        SavedDefinitionDic savedDefinition = savedDefinitions.get(position);
//        holder.bind(savedDefinition);
//    }
//
//    @Override
//    public int getItemCount() {
//        return savedDefinitions.size();
//    }
//
//    public static class SavedTermViewHolder extends RecyclerView.ViewHolder {
//        TextView searchTermTextView;
//        TextView definitionTextView;
//
//        public SavedTermViewHolder(@NonNull View itemView) {
//            super(itemView);
//            searchTermTextView = itemView.findViewById(R.id.searchTermTextView);
//            definitionTextView = itemView.findViewById(R.id.definitionTextView);
//        }
//
////        public void bind(SavedDefinitionDic savedDefinition) {
////            searchTermTextView.setText(savedDefinition.getSearchTerm());
////            definitionTextView.setText((CharSequence) savedDefinition.getDefinitions());
////        }
//        public void bind(SavedDefinitionDic savedDefinition) {
//            searchTermTextView.setText(savedDefinition.getSearchTerm());
//            // Assuming getDefinition() returns an ArrayList<String>
//            // Convert it to a single string separated by commas, for example
//            List<String> definitions = savedDefinition.getDefinitions();
//            if (definitions != null && !definitions.isEmpty()) {
//                String definitionString = TextUtils.join(", ", definitions);
//                definitionTextView.setText(definitionString);
//            } else {
//                // Handle the case when definitions list is empty or null
//                definitionTextView.setText("");
//            }
//        }
//
//    }
private List<SavedDefinitionDic> savedDefinitions;
    private Context context;

    public SavedTermsAdapter(List<SavedDefinitionDic> savedDefinitions, Context context) {
        this.savedDefinitions = savedDefinitions;
        this.context = context;
    }

    public void updateSavedDefinitions(List<SavedDefinitionDic> newSavedDefinitions) {
        if (savedDefinitions != null) {
            savedDefinitions.clear();
            if (newSavedDefinitions != null) {
                savedDefinitions.addAll(newSavedDefinitions);
            }
            notifyDataSetChanged();
        }
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

    public class SavedTermViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView searchTermTextView;
        //TextView definitionTextView;

        public SavedTermViewHolder(@NonNull View itemView) {
            super(itemView);
            searchTermTextView = itemView.findViewById(R.id.searchTermTextView);
            //definitionTextView = itemView.findViewById(R.id.definitionTextView);
            itemView.setOnClickListener(this);
        }

        public void bind(SavedDefinitionDic savedDefinition) {
            searchTermTextView.setText(savedDefinition.getSearchTerm());
            List<String> definitions = savedDefinition.getDefinitions();
//            if (definitions != null && !definitions.isEmpty()) {
//                String definitionString = TextUtils.join(", ", definitions);
//                definitionTextView.setText(definitionString);
//            } else {
//                definitionTextView.setText("");
//            }
        }

        @Override
        public void onClick(View v) {
            SavedDefinitionDic savedDefinition = savedDefinitions.get(getAdapterPosition());
            showDefinitionDialog(savedDefinition);
        }

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
