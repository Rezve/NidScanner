package com.rezve.nidscanner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public enum CARD_TYPE { SMART_NID_CARD, OLD_NID_CARD, UNKNOWN }

    public static CARD_TYPE getCardType(String rawData) {
        if (isSmartCard(rawData)) {
            return CARD_TYPE.SMART_NID_CARD;
        } else if (isOldCard(rawData)) {
            return CARD_TYPE.OLD_NID_CARD;
        } else {
            return CARD_TYPE.UNKNOWN;
        }
    }

    private static boolean isSmartCard(String rawData) {
        if (rawData.contains("<]?")) {
            return true;
        }
        return false;
    }

    private static boolean isOldCard(String rawData) {
        if (rawData.contains("<pin>")) {
            return true;
        }
        return false;
    }

    public static String dateToString(Date date, String pattern) {
        if(date == null) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static Date stringToDate(String stringDate, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = format.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String formatDate(String date, String pattern) {
        Date d = stringToDate(date.trim(), pattern);
        return dateToString(d, "dd MMMM yyyy");
    }
}
