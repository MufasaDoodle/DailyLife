package com.quantilink.dailylife.settings;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.quantilink.dailylife.api.DataAPI;
import com.quantilink.dailylife.api.ServiceGenerator;
import com.quantilink.dailylife.data.GroceryRepo;
import com.quantilink.dailylife.data.NoteRepo;
import com.quantilink.dailylife.data.TodoListRepo;
import com.quantilink.dailylife.data.UserRepository;
import com.quantilink.dailylife.models.DataPackage;
import com.quantilink.dailylife.models.DataPackageResponse;
import com.quantilink.dailylife.models.Grocery;
import com.quantilink.dailylife.models.GroceryList;
import com.quantilink.dailylife.models.ListsToJson;
import com.quantilink.dailylife.models.Note;
import com.quantilink.dailylife.models.TodoList;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsViewModel extends AndroidViewModel {
    TodoListRepo todoListRepo;
    NoteRepo noteRepo;
    GroceryRepo groceryRepo;
    UserRepository userRepository;

    String userId;

    MutableLiveData<String> statusString;

    public SettingsViewModel(@NonNull Application application) {
        super(application);
        todoListRepo = TodoListRepo.getInstance(application);
        noteRepo = NoteRepo.getInstance(application);
        groceryRepo = GroceryRepo.getInstance(application);
        userRepository = UserRepository.getInstance(application);
        statusString = new MutableLiveData<>();
    }

    public void setId(){
        if(userRepository.getCurrentUser().getValue() != null){
            userId = userRepository.getCurrentUser().getValue().getUid();
        }
        else {
            userId = "-1";
        }
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }

    public void signOut(){
        userRepository.signOut();
    }

    public LiveData<List<GroceryList>> getGroceries() {
        return groceryRepo.getGroceries();
    }

    public LiveData<List<Note>> getNotes() {
        return noteRepo.getNotes();
    }

    public LiveData<List<TodoList>> getTodoLists() {
        return todoListRepo.getTodoLists();
    }

    public void saveDataToCloud(List<GroceryList> groceryLists, List<Note> notes, List<TodoList> todoLists) {
        DataAPI dataAPI = ServiceGenerator.getDataAPI();

        Gson gson = new Gson();
        String json = gson.toJson(new ListsToJson(groceryLists, notes, todoLists));
        DataPackage data = new DataPackage(userId, json);

        Log.i("roo", gson.toJson(data));

        Call<DataPackage> call = dataAPI.saveData(data);
        call.enqueue(new Callback<DataPackage>() {
            @Override
            public void onResponse(Call<DataPackage> call, Response<DataPackage> response) {

                try {
                    Log.i("Retrofit", "got response" + response.code() + response.errorBody().string() + response.body() + response.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (response.code() == 200) {
                    statusString.setValue("Successfully uploaded");
                }
            }

            @Override
            public void onFailure(Call<DataPackage> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong" + t.getMessage());
            }
        });

    }

    public void restoreDataFromCloud() {
        DataAPI dataAPI = ServiceGenerator.getDataAPI();

        Call<DataPackageResponse> call = dataAPI.getData(userId);
        call.enqueue(new Callback<DataPackageResponse>() {
            @Override
            public void onResponse(Call<DataPackageResponse> call, Response<DataPackageResponse> response) {
                if(response.code() == 200){
                    wipeLocalStorage();

                    DataPackage data = response.body().getDataPackage();
                    Gson gson = new Gson();
                    ListsToJson lists = gson.fromJson(data.getJsonData(), ListsToJson.class);

                    for(Note note : lists.getNotes()){
                        noteRepo.addNote(note);
                    }

                    for (TodoList todoList : lists.getTodoLists()){
                        todoListRepo.addTodoList(todoList);
                    }

                    for(GroceryList groceryList : lists.getGroceryLists()){
                        groceryRepo.addGrocery(groceryList);
                    }
                }
            }

            @Override
            public void onFailure(Call<DataPackageResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong " + t.getMessage());
            }
        });
    }

    public void wipeLocalStorage() {
        noteRepo.deleteAllNotes();
        todoListRepo.deleteAllLists();
        groceryRepo.deleteAllGroceryLists();
    }
}
