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

    public static String getName(String rawData) {
        // Normal PDF417 data
        if (rawData.length() < 6) {
            return rawData;
        }
        if (!rawData.contains("\u001D")) {
            return rawData.substring(0, 6);
        }

        // Old NID card
        if (rawData.indexOf("\u001D") > 15) {
            return rawData.substring(0, 6);
        }

        // Smart card
        return rawData.substring(6, rawData.indexOf("\u001D"));
    }
}
