package com.rezve.nidscanner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    private static final int SMART_STRING_PADDING = 2;
    private static final int OLD_NAME_PADDING = 6;
    private static final int OLD_NID_NO_PADDING = 5;
    private static final int OLD_DOB_PADDING = 5;

    public enum CARD_TYPE { SMART_NID_CARD, OLD_NID_CARD, UNKNOWN }

    public static String getName(CARD_TYPE cardType, String rawData) {
        if ( cardType == CARD_TYPE.SMART_NID_CARD ) {
            return rawData.substring(rawData.indexOf("NM") + SMART_STRING_PADDING, rawData.indexOf("NW"));
        } else if ( cardType == CARD_TYPE.OLD_NID_CARD ) {
            return rawData.substring(rawData.indexOf("<name>") + OLD_NAME_PADDING, rawData.indexOf("</name>"));
        } else {
            return null;
        }
    }

    public static String getNidNo(CARD_TYPE cardType, String rawData) {
        if ( cardType == CARD_TYPE.SMART_NID_CARD ) {
            return rawData.substring(rawData.indexOf("NW") + SMART_STRING_PADDING, rawData.indexOf("OL"));
        } else if ( cardType == CARD_TYPE.OLD_NID_CARD ) {
            return rawData.substring(rawData.indexOf("<pin>") + OLD_NID_NO_PADDING, rawData.indexOf("</pin>"));
        } else {
            return null;
        }
    }

    public static String getDateOfBirth(CARD_TYPE cardType, String rawData) {
        if ( cardType == CARD_TYPE.SMART_NID_CARD ) {
            String date = rawData.substring(rawData.indexOf("BR") + SMART_STRING_PADDING, rawData.indexOf("PE"));
            return formatDate(date, "yyyyMMdd");
        } else if ( cardType == CARD_TYPE.OLD_NID_CARD ) {
            String date = rawData.substring(rawData.indexOf("<DOB>") + OLD_DOB_PADDING, rawData.indexOf("</DOB>"));
            return formatDate(date, "dd MMM yyyy");
        } else {
            return null;
        }
    }

    public static String getIssueDate(CARD_TYPE cardType, String rawData) {
        if ( cardType == CARD_TYPE.SMART_NID_CARD ) {
            String date = rawData.substring(rawData.indexOf("DT") + SMART_STRING_PADDING, rawData.indexOf("PK"));
            return formatDate(date, "yyyyMMdd");
        } else if ( cardType == CARD_TYPE.OLD_NID_CARD ) {
            return "N/A";
        } else {
            return null;
        }
    }

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
