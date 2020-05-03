package com.example.akhbariapp;

import org.w3c.dom.Text;

public class MessageItem {

    private String Title;
    private String Read_more;
    private String Date,time;

    public MessageItem(String Title , String Read_more , String Date , String time){

        this.Title=Title;
        this.Read_more=Read_more;
        this.Date=Date;
        this.time=time;

    }


    public String getRead_more() {
        return Read_more;
    }

    public void setRead_more(String read_more) {
        Read_more = read_more;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
