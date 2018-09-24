package com.rezve.nidscanner.history;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.rezve.nidscanner.db.HistoryRepository;
import com.rezve.nidscanner.models.History;
import com.rezve.nidscanner.models.Nid;

import java.util.List;

public class HistoryViewModel extends AndroidViewModel {
    private HistoryRepository repository;
    private LiveData<List<Nid>> historyList;

    public HistoryViewModel(@NonNull Application application) {
        super(application);
        repository = new HistoryRepository(application);
        historyList = repository.getHistoryList();
    }

    public void insert(Nid nid) {
        repository.insert(nid);
    }

    public void delete(Nid nid) {
        repository.delete(nid);
    }

    public LiveData<List<Nid>> getHistoryList() {
        return historyList;
    }
}
