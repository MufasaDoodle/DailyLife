package com.quantilink.dailylife.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quantilink.dailylife.R;

import java.util.ArrayList;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {
    private ArrayList<TodoList> todoLists;

    final private OnListItemClickListener onListItemClickListener;

    public TodoListAdapter(ArrayList<TodoList> todoLists, TodoListAdapter.OnListItemClickListener listener) {
        this.todoLists = todoLists;
        onListItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.todo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(todoLists.get(position).getTodoListTitle());
    }

    @Override
    public int getItemCount() {
        return todoLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onListItemClickListener.OnListItemClick(todoLists.get(getAdapterPosition()));
        }
    }

    public interface OnListItemClickListener {
        void OnListItemClick(TodoList todoList);
    }
}
