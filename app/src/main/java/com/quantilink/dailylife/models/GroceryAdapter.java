package com.quantilink.dailylife.models;

import android.graphics.Paint;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quantilink.dailylife.R;

import java.util.List;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.ViewHolder> {
    private GroceryList groceries;
    final private OnListItemClickListener onListItemClickListener;

    public GroceryAdapter(GroceryList groceries, OnListItemClickListener onListItemClickListener) {
        this.groceries = groceries;
        this.onListItemClickListener = onListItemClickListener;
    }

    @NonNull
    @Override
    public GroceryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.groceryitem_edit, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryAdapter.ViewHolder holder, int position) {
        holder.checkBox.setChecked(groceries.getGroceries().get(position).isChecked());
        holder.title.setText(groceries.getGroceries().get(position).getTitle());
        holder.amount.setText(String.valueOf(groceries.getGroceries().get(position).getAmount()));

        if(holder.checkBox.isChecked()){
            holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

    @Override
    public int getItemCount() {
        return groceries.getGroceries().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener, TextWatcher, View.OnClickListener {
        EditText title;
        CheckBox checkBox;
        EditText amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.groceryText);
            checkBox = itemView.findViewById(R.id.groceryCheckBox);
            amount = itemView.findViewById(R.id.editTextNumber);

            title.addTextChangedListener(this);
            checkBox.setOnCheckedChangeListener(this);
            itemView.findViewById(R.id.deleteGroceryBtn).setOnClickListener(this);
            //ugly solution imo, but not sure how else i'm supposed to distinguish the two textwatchers
            amount.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    //nothing
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    try {
                        onListItemClickListener.OnAmountChanged(getAdapterPosition(), Integer.parseInt(s.toString()));
                    }
                    catch (Exception e){
                        amount.setText("0");
                        return;
                    }

                }

                @Override
                public void afterTextChanged(Editable s) {
                    //nothing
                }
            });
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //do nothing
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            onListItemClickListener.OnTitleTextChanged(getAdapterPosition(), s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
            //do nothing
        }

        @Override
        public void onClick(View v) {
            onListItemClickListener.OnGroceryDeleteClicked(getAdapterPosition());
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            onListItemClickListener.OnCheckBoxTap(getAdapterPosition(),isChecked);
        }
    }

    public interface OnListItemClickListener {
        void OnCheckBoxTap(int index, boolean isChecked);
        void OnTitleTextChanged(int index, String text);
        void OnAmountChanged(int index, int num);
        void OnGroceryDeleteClicked(int index);
    }
}
