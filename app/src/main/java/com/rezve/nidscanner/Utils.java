package com.rezve.nidscanner;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String dateToString(Date date, String pattern) {
        if(date == null) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
}
