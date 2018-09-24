package com.rezve.nidscanner.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rezve.nidscanner.models.History;
import com.rezve.nidscanner.models.Nid;

import java.util.List;

@Dao
public interface HistoryDao {

    @Insert
    public void insert(Nid nid);

    @Delete
    public void delete(Nid nid);

    @Query("SELECT * FROM nid ORDER BY createdAt DESC")
    public LiveData<List<Nid>> getAllHistory();
}
