package com.rezve.nidscanner.parser;

import android.content.Context;

import com.rezve.nidscanner.R;
import com.rezve.nidscanner.Utils;
import com.rezve.nidscanner.parser.DataParser;

public class OldNidDataParser extends DataParser {
    private static final int SMART_STRING_PADDING = 2;
    private static final int OLD_NAME_PADDING = 6;
    private static final int OLD_NID_NO_PADDING = 5;
    private static final int OLD_DOB_PADDING = 5;

    public OldNidDataParser(Context context, String rawData) {
        super(context, rawData);
        parse();
    }

    private void parse() {
        setName();
        setNidNo();
        setDateOfBirth();
        setIssueDate();
    }

    private void setName() {
        if (rawData.contains("<name>") && rawData.contains("</name>")) {
            this.name = rawData.substring(rawData.indexOf("<name>") + OLD_NAME_PADDING, rawData.indexOf("</name>"));
        } else {
            this.name = "N/A";
        }
    }

    private void setNidNo() {
        if (rawData.contains("<pin>") && rawData.contains("</pin>")) {
            this.nidNo = rawData.substring(rawData.indexOf("<pin>") + OLD_NID_NO_PADDING, rawData.indexOf("</pin>"));
        } else {
            this.nidNo = "N/A";
        }
        this.nidNo = getString(R.string.nid_no) + this.nidNo;
    }

    private void setDateOfBirth() {
        if (rawData.contains("<DOB>") && rawData.contains("</DOB>")) {
            this.dateOfBirth = rawData.substring(rawData.indexOf("<DOB>") + OLD_DOB_PADDING, rawData.indexOf("</DOB>"));
            this.dateOfBirth = Utils.formatDate(this.dateOfBirth, "dd MMM yyyy");
        } else {
            this.dateOfBirth = "N/A";
        }
        this.dateOfBirth = getString(R.string.date_of_birth) + this.dateOfBirth;
    }

    private void setIssueDate() {
        this.issueDate = getString(R.string.issue_date) + "N/A";
    }
}
