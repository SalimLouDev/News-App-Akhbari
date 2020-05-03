package com.example.akhbariapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.akhbariapp.Entity.EntityUser;
import com.example.akhbariapp.Repository.UserRepository;

import java.util.concurrent.ExecutionException;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public void add_user(EntityUser user){
        userRepository.add_user(user);
    }

    public EntityUser find(String pass,String nat_id) throws ExecutionException, InterruptedException {
        return userRepository.find(pass,nat_id);
    }

    public EntityUser check(String f,String l,String nat) throws ExecutionException, InterruptedException {
        return userRepository.check(f,l,nat);
    }
}
