package com.example.akhbariapp.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Blob;

@Entity(tableName = "Post")
public class PostsEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title,Description;
    private Blob image;

    public PostsEntity(String title, String description, Blob image) {
        this.title = title;
        Description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }
}
