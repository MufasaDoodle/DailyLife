package com.quantilink.dailylife.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quantilink.dailylife.R;

import java.util.List;

/**
 * Adapter for displaying multiple grocery lists
 */
public class GroceryListAdapter extends RecyclerView.Adapter<GroceryListAdapter.ViewHolder> {
    private List<GroceryList> groceryLists;

    final private OnListItemClickListener onListItemClickListener;

    public GroceryListAdapter(List<GroceryList> groceryLists, GroceryListAdapter.OnListItemClickListener onListItemClickListener) {
        this.groceryLists = groceryLists;
        this.onListItemClickListener = onListItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.groceryitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(groceryLists.get(position).getGroceryListTitle());
    }

    @Override
    public int getItemCount() {
        return groceryLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_name);
            itemView.setOnClickListener(this);
            itemView.findViewById(R.id.deletegroceryListBtn).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.deletegroceryListBtn) {
                onListItemClickListener.OnListItemDelete(groceryLists.get(getAdapterPosition()));
            }
            else {
                onListItemClickListener.OnListItemClick(groceryLists.get(getAdapterPosition()));
            }
        }
    }

    public interface OnListItemClickListener {

        void OnListItemClick(GroceryList groceryList);

        void OnListItemDelete(GroceryList groceryList);
    }
}
