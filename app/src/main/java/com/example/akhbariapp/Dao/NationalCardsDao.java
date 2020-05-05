package com.example.akhbariapp.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.akhbariapp.Entity.NationalCardsEntity;

import java.util.List;

@Dao
public interface NationalCardsDao {

    @Insert
    void insert(NationalCardsEntity nationalCardsEntity);

    @Query("SELECT * FROM NationalCards WHERE first_name=:f AND last_name=:l AND residence=:r AND national_id=:nat")
    List<NationalCardsEntity> check (String f, String l, String r, int nat);

}
