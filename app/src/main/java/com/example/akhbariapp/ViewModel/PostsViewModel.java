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

    public LiveData<List<PostsEntity>> get_week_posts(long f_day,long s_day){
        return postsRepository.get_week_posts(f_day,s_day);
    }

    public LiveData<List<PostsEntity>> get_posts_by_type(String post_t){
        return postsRepository.get_posts_by_type(post_t);
    }

    public LiveData<List<PostsEntity>> get_posts_by_name(String t,long d){
        return postsRepository.get_posts_by_name(t,d);
    }
}
