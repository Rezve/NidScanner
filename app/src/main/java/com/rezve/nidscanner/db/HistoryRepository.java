package com.rezve.nidscanner.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.rezve.nidscanner.models.History;

import java.util.List;

public class HistoryRepository {
    private enum TASK { INSERT, DELETE };

    private HistoryDao historyDao;
    private LiveData<List<History>> historyList;

    public HistoryRepository(Application application) {
        historyDao = ScannerDb.getDatabase(application).historyDao();
        historyList = historyDao.getAllHistory();
    }

    public void insert(History history) {
        new DbTask(historyDao, TASK.INSERT).execute(history);
    }

    public void delete(History history) {
        new DbTask(historyDao, TASK.DELETE).execute(history);
    }

    public LiveData<List<History>> getHistoryList() {
        return historyList;
    }

    private static class DbTask extends AsyncTask<History, Void, Void> {
        HistoryDao historyDao;
        private TASK task;

        public DbTask(HistoryDao historyDao, TASK task) {
            this.historyDao = historyDao;
            this.task = task;
        }

        @Override
        protected Void doInBackground(History... histories) {
            if (task == TASK.INSERT) {
                historyDao.insert(histories[0]);
            } else if (task == TASK.DELETE) {
                historyDao.delete(histories[0]);
            }
            return null;
        }
    }
}
