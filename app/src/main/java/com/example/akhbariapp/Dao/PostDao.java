package com.example.akhbariapp.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.akhbariapp.Entity.PostsEntity;

import java.util.Date;
import java.util.List;

@Dao
public interface PostDao {

    @Insert
    void insert(PostsEntity postsEntity);

    @Query("SELECT * FROM Post WHERE post_date=:today")
    LiveData<List<PostsEntity>>gettodayposts(Date today);
}
