package com.quantilink.dailylife.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.quantilink.dailylife.models.TodoList;

import java.util.List;

@Dao
public interface TodoListDAO {
    @Insert
    void insert(TodoList todoList);

    @Update
    void update(TodoList todoList);

    @Delete
    void delete(TodoList todoList);

    @Query("SELECT * FROM todolist_table")
    LiveData<List<TodoList>> getAllTodoLists();

    @Query("DELETE FROM todolist_table")
    void deleteAllTodoLists();

    @Query("SELECT * FROM todolist_table WHERE id=:index")
    TodoList getTodoList(long index);

    @Query("SELECT * FROM todolist_table ORDER BY id DESC LIMIT 1")
    TodoList getLatestTodoList();
}
