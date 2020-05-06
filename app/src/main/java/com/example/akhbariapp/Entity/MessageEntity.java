package com.example.akhbariapp.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "message_table")
public class MessageEntity {

    @PrimaryKey(autoGenerate = true)
    private int messageID;

    private String title;

    private String description;

    private String date;

    private String time;

    public MessageEntity(String title, String description, String date, String time) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public int getMessageID() {
        return messageID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
