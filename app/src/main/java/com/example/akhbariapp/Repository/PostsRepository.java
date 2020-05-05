package com.example.akhbariapp.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.akhbariapp.AppDatabase;
import com.example.akhbariapp.Dao.PostDao;
import com.example.akhbariapp.Entity.PostsEntity;

import java.util.Date;
import java.util.List;

public class PostsRepository {

    private PostDao postDao;

    public PostsRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getinstance(application);
        postDao = appDatabase.postDao();
    }

    public void insert_post(PostsEntity post){
        new InsertPost(postDao).execute(post);
    }

    public LiveData<List<PostsEntity>> gettodayposts(long today){
       return postDao.gettodayposts(today);
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
