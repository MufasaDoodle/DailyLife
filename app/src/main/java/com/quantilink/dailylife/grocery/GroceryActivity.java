package com.quantilink.dailylife.grocery;

import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.quantilink.dailylife.R;
import com.quantilink.dailylife.models.Grocery;
import com.quantilink.dailylife.models.GroceryAdapter;
import com.quantilink.dailylife.models.GroceryList;

/**
 * Handles the view for the individual grocery list
 */
public class GroceryActivity extends AppCompatActivity implements GroceryAdapter.OnListItemClickListener {
    private GroceryViewModel viewModel;
    RecyclerView groceryList;
    GroceryAdapter adapter;

    EditText groceryText;
    EditText amount;
    Button addBtn;
    EditText titleET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);
        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(GroceryViewModel.class);

        Bundle bundle = getIntent().getExtras();

        viewModel.setCurrentGroceryList((GroceryList) bundle.get("GroceryList"));

        groceryList = findViewById(R.id.groceryRV);
        groceryList.hasFixedSize();
        groceryList.setLayoutManager(new LinearLayoutManager(this));

        adapter = new GroceryAdapter(viewModel.getGroceryList(), this);
        groceryList.setAdapter(adapter);

        groceryText = findViewById(R.id.addGroceryField);
        amount = findViewById(R.id.editTextNumber);
        addBtn = findViewById(R.id.addGroceryBtn);

        addBtn.setOnClickListener(v -> {
            addNewGrocery();
        });

        ImageButton btn = findViewById(R.id.grocerybackBtn);
        btn.setOnClickListener(v -> {
            saveAndClose();
        });

        titleET = findViewById(R.id.groceryTitleET);

        titleET.setText(viewModel.getGroceryList().getGroceryListTitle());

        titleET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setNewTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void addNewGrocery() {
        if(groceryText.getText().toString().isEmpty()){
            return;
        }

        viewModel.addGrocery(new Grocery(groceryText.getText().toString(), Integer.parseInt(amount.getText().toString()), false));

        adapter = new GroceryAdapter(viewModel.getGroceryList(), this);
        groceryList.setAdapter(adapter);

        groceryText.setText("");
    }

    void saveAndClose() {
        viewModel.updateGroceryList();
        Toast.makeText(this, "Saved list", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onBackPressed() {
        saveAndClose();
        super.onBackPressed();
    }

    @Override
    public void onPause() {
        viewModel.updateGroceryList();
        Toast.makeText(this, "Saved list", Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    @Override
    public void OnCheckBoxTap(int index, boolean isChecked) {
        viewModel.getGroceryList().getGroceries().get(index).setChecked(isChecked);

        //sometimes the itemview is null, not sure why, so i'm doing a try-catch to prevent a crash
        try{
            RecyclerView.ViewHolder holder = groceryList.findViewHolderForAdapterPosition(index);
            EditText temp = holder.itemView.findViewById(R.id.groceryText);

            if(isChecked){
                temp.setPaintFlags(temp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
            else {
                temp.setPaintFlags(temp.getPaintFlags() ^ Paint.STRIKE_THRU_TEXT_FLAG);
            }
        }
        catch (Exception e){
            //do nothing
        }
    }

    @Override
    public void OnTitleTextChanged(int index, String text) {
        viewModel.getGroceryList().getGroceries().get(index).setTitle(text);
    }

    @Override
    public void OnAmountChanged(int index, int num) {
        viewModel.getGroceryList().getGroceries().get(index).setAmount(num);
    }

    @Override
    public void OnGroceryDeleteClicked(int index) {
        viewModel.removeGroceryFromList(index);
        adapter.notifyDataSetChanged();
    }
}