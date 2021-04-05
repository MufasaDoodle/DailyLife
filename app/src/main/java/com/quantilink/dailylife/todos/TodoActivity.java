package com.quantilink.dailylife.todos;

import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.quantilink.dailylife.R;
import com.quantilink.dailylife.models.Note;
import com.quantilink.dailylife.models.TodoAdapter;
import com.quantilink.dailylife.models.TodoList;
import com.quantilink.dailylife.models.TodoListAdapter;
import com.quantilink.dailylife.note.NoteViewModel;
import com.quantilink.dailylife.todolist.TodosViewModel;

public class TodoActivity extends AppCompatActivity implements TodoAdapter.OnListItemClickListener {
    private TodoViewModel viewModel;

    RecyclerView todos;
    TodoAdapter todoAdapter;

    EditText todoTitle;

    EditText newTodoText;
    Button newTodoBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        viewModel = new ViewModelProvider(this).get(TodoViewModel.class);

        //init text fields or whatever we do with each todo
        //set text fields to whatever values todo

        Bundle bundle = getIntent().getExtras();
        viewModel.setCurrentTodoList((TodoList) bundle.get("TodoList")); //set the current list in the VM

        todos = findViewById(R.id.todoRV);
        todos.hasFixedSize();
        todos.setLayoutManager(new LinearLayoutManager(this));

        todoAdapter = new TodoAdapter(viewModel.getTodoList(), this); //maybe change the adapter to require a todolist instead of arraylist OTHER WAY
        todos.setAdapter(todoAdapter);

        newTodoText = findViewById(R.id.addTodoTextField);
        newTodoBtn = findViewById(R.id.addTodoButton);

        newTodoBtn.setOnClickListener(v -> {
            addNewTodo();
        });

        ImageButton button = findViewById(R.id.todobackbtn);

        button.setOnClickListener(v -> {
            saveAndClose();
        });

        todoTitle = findViewById(R.id.todoTitleET);

        todoTitle.setText(viewModel.getTodoList().getTodoListTitle());

        todoTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                viewModel.setOldTitle();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setNewTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //viewModel.updateTodoListName();
            }
        });
    }

    private void addNewTodo() {
        viewModel.addNewTodo(newTodoText.getText().toString());

        todoAdapter = new TodoAdapter(viewModel.getTodoList(), this);
        todos.setAdapter(todoAdapter);

        newTodoText.setText("");
    }

    void saveAndClose() {
        viewModel.updateTodoListName();
        viewModel.updateTodoList();
        Toast.makeText(this, "Saved list", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onBackPressed() {
        saveAndClose();
        super.onBackPressed();
    }

    @Override
    public void OnCheckBoxTap(int index, boolean isChecked) {
        viewModel.getTodoList().getTodos().get(index).setFinished(isChecked);

        //sometimes the itemview is null, not sure why, so i'm doing a try-catch to prevent a crash
        try{
            RecyclerView.ViewHolder holder = todos.findViewHolderForAdapterPosition(index);
            EditText temp = holder.itemView.findViewById(R.id.todoEditText);

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
    public void OnEditTextChanged(int index, String text) {
        viewModel.getTodoList().getTodos().get(index).setTodoText(text);
    }
}
