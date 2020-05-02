package com.example.akhbariapp.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User")
public class EntityUser {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String FirstName,LastName,City,password;
    private int nationalID;

    public EntityUser(String FirstName, String LastName, String password, String City, int nationalID) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.password = password;
        this.City = City;
        this.nationalID = nationalID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public int getNationalID() {
        return nationalID;
    }

    public void setNationalID(int nationalID) {
        nationalID = nationalID;
    }
}
