package com.rezve.nidscanner.parser;

import android.content.Context;

public class DataParser {
    private Context context;
    protected String name;
    protected String nidNo;
    protected String dateOfBirth;
    protected String issueDate;
    protected String rawData;

    public DataParser(Context context, String rawData) {
        this.context = context;
        this.rawData = rawData;
    }

    public String getName() {
        return name;
    }

    public String getNidNo() {
        return nidNo;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public String getString(int resourceId) {
        return context.getResources().getString(resourceId) + " ";
    }
}
