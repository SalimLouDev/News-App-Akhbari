package com.example.akhbariapp.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "NationalCards")
public class NationalCardsEntity {
    @PrimaryKey(autoGenerate = true)
    int id;
    private String first_name,last_name,residence;
    int national_id;
    public NationalCardsEntity(String first_name, String last_name, String residence ,int national_id) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.residence = residence;
        this.national_id = national_id;
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

    public int getNational_id() {
        return national_id;
    }

    public void setNational_id(int national_id) {
        this.national_id = national_id;
    }
}
