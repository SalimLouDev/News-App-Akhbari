package com.example.akhbariapp.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.io.Serializable;

@Entity(tableName = "Post")
public class PostsEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title,description,post_type,city;

    private LocalDate post_date;
    private LocalDateTime post_time;
    private String image_uri;

    public PostsEntity(String title, String description, String city, String post_type, LocalDate post_date, LocalDateTime post_time,
                       String image_uri) {
        this.title=title;
        this.description=description;
        this.city=city;
        this.post_type = post_type;
        this.post_date=post_date;
        this.post_time = post_time;
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

    public LocalDate getPost_date() {
        return post_date;
    }

    public void setPost_date(LocalDate post_date) {
        this.post_date = post_date;
    }

    public String getImage_uri() {
        return image_uri;
    }

    public void setImage_uri(String image_uri) {
        this.image_uri = image_uri;
    }

    public LocalDateTime getPost_time() {
        return post_time;
    }

    public void setPost_time(LocalDateTime post_time) {
        this.post_time = post_time;
    }
}
