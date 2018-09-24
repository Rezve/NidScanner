package com.rezve.nidscanner.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.rezve.nidscanner.db.DateConverter;

import java.util.Date;

@Entity
public class Nid {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String nidNo;
    private String dateOfBirth;
    private String issueDate;
    private String rawData;
    @TypeConverters( DateConverter.class )
    private Date createdAt;

    public Nid(String name, String nidNo, String dateOfBirth, String issueDate, String rawData, Date createdAt) {
        this.name = name;
        this.nidNo = nidNo;
        this.dateOfBirth = dateOfBirth;
        this.issueDate = issueDate;
        this.rawData = rawData;
        this.createdAt = createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

    public String getRawData() {
        return rawData;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
