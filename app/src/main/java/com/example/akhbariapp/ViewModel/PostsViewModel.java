package com.example.akhbariapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.akhbariapp.Entity.PostsEntity;
import com.example.akhbariapp.Repository.PostsRepository;

import java.util.List;

public class PostsViewModel extends AndroidViewModel {

    private PostsRepository postsRepository;
    public PostsViewModel(@NonNull Application application) {
        super(application);
        postsRepository = new PostsRepository(application);
    }

    public void add_post(PostsEntity postsEntity){
        postsRepository.insert_post(postsEntity);
    }

    public LiveData<List<PostsEntity>> gettodayposts(long today){
        return postsRepository.gettodayposts(today);
    }
}
