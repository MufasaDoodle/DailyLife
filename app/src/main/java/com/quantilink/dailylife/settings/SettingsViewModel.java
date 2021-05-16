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
import com.quantilink.dailylife.models.GroceryList;
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

    public void init(){
        //userId = userRepository.getCurrentUser().getValue().getUid();
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
        DataPackage data = new DataPackage(1, groceryLists, notes, todoLists);

        Gson gson = new Gson();
        Log.i("retrofit", gson.toJson(data));

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

    }

    public void wipeLocalStorage() {
        noteRepo.deleteAllNotes();
        todoListRepo.deleteAllLists();
        groceryRepo.deleteAllGroceryLists();
    }
}
