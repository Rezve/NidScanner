package com.rezve.nidscanner.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.rezve.nidscanner.models.History;
import com.rezve.nidscanner.models.Nid;

@Database(entities = {Nid.class}, version = 1)
public abstract class ScannerDb extends RoomDatabase {
    public abstract HistoryDao historyDao();
    private static ScannerDb INSTANCE;

    static ScannerDb getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ScannerDb.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ScannerDb.class, "scanner_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
