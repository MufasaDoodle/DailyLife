package com.quantilink.dailylife.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quantilink.dailylife.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<Note> notes;

    final private OnListItemClickListener onListItemClickListener;

    public NotesAdapter(List<Note> notes, OnListItemClickListener listener) {
        this.notes = notes;
        onListItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(notes.get(position).getNoteTitle());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            itemView.setOnClickListener(this);
            itemView.findViewById(R.id.deleteNoteBtn).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.deleteNoteBtn){
                onListItemClickListener.OnListItemDeleteClick(notes.get(getAdapterPosition()));
            }
            else {
                onListItemClickListener.OnListItemClick(notes.get(getAdapterPosition()));
            }
        }
    }

    public interface OnListItemClickListener {
        void OnListItemClick(Note note);
        void OnListItemDeleteClick(Note note);
    }
}


