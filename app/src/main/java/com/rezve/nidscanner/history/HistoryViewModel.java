package com.rezve.nidscanner.history;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.rezve.nidscanner.db.HistoryRepository;
import com.rezve.nidscanner.models.History;

import java.util.List;

public class HistoryViewModel extends AndroidViewModel {
    private HistoryRepository repository;
    private LiveData<List<History>> historyList;

    public HistoryViewModel(@NonNull Application application) {
        super(application);
        repository = new HistoryRepository(application);
        historyList = repository.getHistoryList();
    }

    public void insert(History history) {
        repository.insert(history);
    }

    public void delete(History history) {
        repository.delete(history);
    }

    public LiveData<List<History>> getHistoryList() {
        return historyList;
    }
}
