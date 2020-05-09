package com.example.akhbariapp.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.akhbariapp.Entity.PostsEntity;

import java.util.List;

@Dao
public interface PostDao {

    @Insert
    void insert(PostsEntity postsEntity);

    @Query("SELECT * FROM Post WHERE post_date=:today")
    LiveData<List<PostsEntity>>gettodayposts(long today);

    @Query("SELECT * FROM Post WHERE post_date BETWEEN :f_day AND :s_day")
    LiveData<List<PostsEntity>>get_week_posts(long f_day,long s_day);

    @Query("SELECT * FROM Post WHERE post_type Like :post_t")
    LiveData<List<PostsEntity>>get_posts_by_type(String post_t);

    @Query("SELECT * FROM Post WHERE post_date < :d")
    LiveData<List<PostsEntity>>get_past_posts(long d);
}
