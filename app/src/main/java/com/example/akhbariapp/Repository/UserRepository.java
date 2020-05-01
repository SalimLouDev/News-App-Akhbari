package com.example.akhbariapp.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.akhbariapp.AppDatabase;
import com.example.akhbariapp.Dao.UserDao;
import com.example.akhbariapp.Entity.EntityUser;

import java.util.concurrent.ExecutionException;

public class UserRepository {

    private UserDao userDao;
    public UserRepository(Application application){

        AppDatabase database = AppDatabase.getinstance(application);
        userDao = database.userDao();
    }


    public void add_user(EntityUser user){
      new InsertAsyncTask(userDao).execute(user);
    }

    public EntityUser find(String pass, String nat_id) throws ExecutionException, InterruptedException {
        return new FindAsyncTask(userDao).execute(pass,nat_id).get();
    }

    static class InsertAsyncTask extends AsyncTask<EntityUser,Void,Void>{

        private UserDao userDao;

        InsertAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(EntityUser... entityUsers) {
            userDao.add_user(entityUsers[0]);
            return null;
        }
    }

    static class FindAsyncTask extends AsyncTask<String,Void,EntityUser>{

        private UserDao userDao;
        FindAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected EntityUser doInBackground(String... strings) {
            return userDao.find(strings[0],strings[1]);
        }
    }
}
