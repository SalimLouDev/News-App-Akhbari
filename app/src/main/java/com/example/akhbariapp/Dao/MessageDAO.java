package com.example.akhbariapp.Dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.akhbariapp.Entity.MessageEntity;

import java.util.List;

@Dao
public interface MessageDAO {

    @Insert
    void insert(MessageEntity messageEntity);

    @Delete
    void delete(MessageEntity messageEntity);

    @Query("SELECT * FROM message_table ORDER BY messageID DESC")
    LiveData<List<MessageEntity>> getAllMessages();
}
