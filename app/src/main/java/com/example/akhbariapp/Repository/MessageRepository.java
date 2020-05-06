package com.example.akhbariapp.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.akhbariapp.AppDatabase;
import com.example.akhbariapp.Dao.MessageDAO;
import com.example.akhbariapp.Entity.MessageEntity;


import java.util.List;

public class MessageRepository {

    private MessageDAO messageDAO;
    private LiveData<List<MessageEntity>> allMessages;

    public MessageRepository(Application application){

        AppDatabase appDatabase = AppDatabase.getInstance(application);
        messageDAO = appDatabase.messageDAO();
        allMessages =  messageDAO.getAllMessages();
    }

    public void insert_message(MessageEntity messageEntity){
        new insertMessageAsyncTask(messageDAO).execute(messageEntity);
    }

    public void delete_message(MessageEntity messageEntity){
        new deleteMessageAsyncTask(messageDAO).execute(messageEntity);
    }

    public LiveData<List<MessageEntity>> getAllMessages() {
        return allMessages;
    }





    private static class insertMessageAsyncTask extends AsyncTask<MessageEntity,Void,Void>{
        private MessageDAO messageDAO;
        private insertMessageAsyncTask(MessageDAO messageDAO){
            this.messageDAO=messageDAO;
        }

        @Override
        protected Void doInBackground(MessageEntity... messageEntities) {
            messageDAO.insert(messageEntities[0]);
            return null;
        }
    }

    //////////////////////////////////
    private static class deleteMessageAsyncTask extends AsyncTask<MessageEntity,Void,Void>{
        private MessageDAO messageDAO;
        private deleteMessageAsyncTask(MessageDAO messageDAO){
            this.messageDAO=messageDAO;
        }

        @Override
        protected Void doInBackground(MessageEntity... messageEntities) {
            messageDAO.delete(messageEntities[0]);
            return null;
        }
    }

}
