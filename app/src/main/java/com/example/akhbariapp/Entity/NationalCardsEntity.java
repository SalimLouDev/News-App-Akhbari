package com.example.akhbariapp.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "NationalCards")
public class NationalCardsEntity {
    @PrimaryKey(autoGenerate = true)
    int id;
    String first_name,last_name,residence;

    public NationalCardsEntity(String first_name, String last_name, String residence) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.residence = residence;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }
}
