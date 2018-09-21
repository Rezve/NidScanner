package com.rezve.nidscanner.db;

import android.arch.persistence.room.TypeConverter;
import android.util.Log;

import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date ( timestamp * 1000 );
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime()/1000;
    }
}
