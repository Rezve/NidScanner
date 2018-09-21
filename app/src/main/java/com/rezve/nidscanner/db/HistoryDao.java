package com.rezve.nidscanner.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rezve.nidscanner.models.History;

import java.util.List;

@Dao
public interface HistoryDao {

    @Insert
    public void insert(History history);

    @Update
    public void update(History history);

    @Delete
    public void delete(History history);

    @Query("SELECT * FROM history")
    public LiveData<List<History>> getAllHistory();
}
