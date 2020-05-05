package com.example.akhbariapp;

import androidx.room.TypeConverter;

import org.joda.time.LocalDate;

import java.util.Date;

public class DateConverter {
    @TypeConverter
    public static LocalDate fromTimestamp(Long value) {
        return value == null ? null : LocalDate.fromDateFields(new Date(value));
    }

    @TypeConverter
    public static Long dateToTimestamp(LocalDate date) {
        return date == null ? null : date.toDate().getTime();
    }

}
