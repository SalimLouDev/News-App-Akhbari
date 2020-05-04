package com.example.akhbariapp.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User")
public class EntityUser {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String FirstName,LastName,City,password,user_type;
    private int nationalID,admin_code;

    public EntityUser(String FirstName, String LastName, String password, String City, int nationalID,int admin_code,String user_type) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.password = password;
        this.City = City;
        this.nationalID = nationalID;
        this.admin_code = admin_code;
        this.user_type = user_type;
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

    public int getAdmin_code() {
        return admin_code;
    }

    public void setAdmin_code(int admin_code) {
        this.admin_code = admin_code;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
}
