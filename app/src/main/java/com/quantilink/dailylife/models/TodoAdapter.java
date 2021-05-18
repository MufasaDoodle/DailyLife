package com.quantilink.dailylife.models;

import android.content.Context;
import android.graphics.Paint;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quantilink.dailylife.R;

import java.util.ArrayList;

/**
 * Adapter for displaying multiple todos
 */
public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
    private TodoList todos;

    final private TodoAdapter.OnListItemClickListener onListItemClickListener;

    public TodoAdapter(TodoList todos, TodoAdapter.OnListItemClickListener listener) {
        this.todos = todos;
        onListItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.todo_edit, parent, false);
        return new TodoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.checkBox.setChecked(todos.getTodos().get(position).isFinished());
        holder.todoText.setText(todos.getTodos().get(position).getTodoText());

        if(holder.checkBox.isChecked()){
            holder.todoText.setPaintFlags(holder.todoText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

    @Override
    public int getItemCount() {
        return todos.getTodos().size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener, TextWatcher, View.OnClickListener {
        EditText todoText;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.todocheckBox);
            todoText = itemView.findViewById(R.id.todoEditText);

            todoText.addTextChangedListener(this);
            checkBox.setOnCheckedChangeListener(this);
            itemView.findViewById(R.id.tododelete).setOnClickListener(this);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            onListItemClickListener.OnCheckBoxTap(getAdapterPosition(),isChecked);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //do nothing
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            onListItemClickListener.OnEditTextChanged(getAdapterPosition(), s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
            //do nothing
        }

        @Override
        public void onClick(View v) {
            onListItemClickListener.OnTodoDeletePressed(getAdapterPosition());
        }
    }


    public interface OnListItemClickListener {
        void OnCheckBoxTap(int index, boolean isChecked);
        void OnEditTextChanged(int index, String text);
        void OnTodoDeletePressed(int index);
    }
}
