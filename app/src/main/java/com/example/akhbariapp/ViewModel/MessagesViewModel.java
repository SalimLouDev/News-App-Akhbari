package com.example.akhbariapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.akhbariapp.Entity.MessageEntity;
import com.example.akhbariapp.Repository.MessageRepository;

import java.util.List;

public class MessagesViewModel extends AndroidViewModel {

    private MessageRepository messageRepository;
    private LiveData<List<MessageEntity>> allMessages;


    public MessagesViewModel(@NonNull Application application) {
        super(application);
        messageRepository = new MessageRepository(application);
        allMessages = messageRepository.getAllMessages();
    }

    public void insert(MessageEntity messageEntity){
        messageRepository.insert_message(messageEntity);
    }

    public void delete(MessageEntity messageEntity){
        messageRepository.delete_message(messageEntity);
    }

    public LiveData<List<MessageEntity>> getAllMessages() {
        return allMessages;
    }
}
