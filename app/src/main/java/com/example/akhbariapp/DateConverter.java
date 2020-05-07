package com.example.akhbariapp;

import androidx.room.TypeConverter;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

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

    @TypeConverter
    public static LocalDateTime fromTimestamp1(Long value) {
        return value == null ? null : LocalDateTime.fromDateFields(new Date(value));
    }

    @TypeConverter
    public static Long dateToTimestamp(LocalDateTime date) {
        return date == null ? null : date.toDate().getTime();
    }
}
