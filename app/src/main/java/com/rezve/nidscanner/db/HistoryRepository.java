package com.rezve.nidscanner.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.rezve.nidscanner.models.History;
import com.rezve.nidscanner.models.Nid;

import java.util.List;

public class HistoryRepository {
    private enum TASK { INSERT, DELETE };

    private HistoryDao historyDao;
    private LiveData<List<Nid>> historyList;

    public HistoryRepository(Application application) {
        historyDao = ScannerDb.getDatabase(application).historyDao();
        historyList = historyDao.getAllHistory();
    }

    public void insert(Nid nid) {
        new DbTask(historyDao, TASK.INSERT).execute(nid);
    }

    public void delete(Nid nid) {
        new DbTask(historyDao, TASK.DELETE).execute(nid);
    }

    public LiveData<List<Nid>> getHistoryList() {
        return historyList;
    }

    private static class DbTask extends AsyncTask<Nid, Void, Void> {
        HistoryDao historyDao;
        private TASK task;

        public DbTask(HistoryDao historyDao, TASK task) {
            this.historyDao = historyDao;
            this.task = task;
        }

        @Override
        protected Void doInBackground(Nid... histories) {
            if (task == TASK.INSERT) {
                historyDao.insert(histories[0]);
            } else if (task == TASK.DELETE) {
                historyDao.delete(histories[0]);
            }
            return null;
        }
    }
}
