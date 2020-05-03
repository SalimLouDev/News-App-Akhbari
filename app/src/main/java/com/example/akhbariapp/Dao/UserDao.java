package com.example.akhbariapp.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.akhbariapp.Entity.EntityUser;

@Dao
public interface UserDao {

    @Insert
    void add_user(EntityUser user);

    @Query("SELECT * FROM User WHERE password=:pass AND nationalID=:nat_id")
    EntityUser find(String pass, String nat_id);

    @Query("SELECT * FROM User WHERE FirstName=:f AND LastName=:l AND nationalID=:nat")
    EntityUser check(String f,String l,String nat);
}
