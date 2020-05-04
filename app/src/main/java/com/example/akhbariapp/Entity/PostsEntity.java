package com.example.akhbariapp.Entity;

import android.net.Uri;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Post")
public class PostsEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title,description,post_type,city;
    private Date post_date;
    private String image_uri;
    public PostsEntity(String title,String description,String city,String post_type,Date post_date,String image_uri) {
        this.title=title;
        this.description=description;
        this.city=city;
        this.post_type = post_type;
        this.post_date=post_date;
        this.image_uri = image_uri;
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
        return description;
    }

    public void setDescription(String description) {}

    public String getPost_type() {
        return post_type;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getPost_date() {
        return post_date;
    }

    public void setPost_date(Date post_date) {
        this.post_date = post_date;
    }

    public String getImage_uri() {
        return image_uri;
    }

    public void setImage_uri(String image_uri) {
        this.image_uri = image_uri;
    }
}
