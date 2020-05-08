package com.example.akhbariapp.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.akhbariapp.AppDatabase;
import com.example.akhbariapp.Dao.PostDao;
import com.example.akhbariapp.Entity.PostsEntity;

import java.util.List;

public class PostsRepository {

    private PostDao postDao;

    public PostsRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        postDao = appDatabase.postDao();
    }

    public void insert_post(PostsEntity post){
        new InsertPost(postDao).execute(post);
    }

    public LiveData<List<PostsEntity>> gettodayposts(long today){
       return postDao.gettodayposts(today);
    }

    public LiveData<List<PostsEntity>> get_week_posts(long f_day,long s_day){
        return postDao.get_week_posts(f_day,s_day);
    }

    public LiveData<List<PostsEntity>> get_posts_by_type(String post_t){
        return postDao.get_posts_by_type(post_t);
    }

    public LiveData<List<PostsEntity>> get_posts_by_name(String t,long d){
       return postDao.get_posts_by_name(t,d);
    }

    static class InsertPost extends AsyncTask<PostsEntity,Void,Void>{

        private PostDao postDao;

        InsertPost(PostDao postDao) {
            this.postDao = postDao;
        }

        @Override
        protected Void doInBackground(PostsEntity... postsEntities) {
            postDao.insert(postsEntities[0]);
            return null;
        }
    }
}
