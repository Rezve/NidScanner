package com.rezve.nidscanner.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import com.rezve.nidscanner.db.DateConverter;

import java.util.Date;

@Entity
public class History {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String details;
    @TypeConverters( DateConverter.class )
    private Date createdAt;

    public History(String name, String details, Date createdAt) {
        this.name = name;
        this.details = details;
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

    public String getDetails() {
        return details;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
