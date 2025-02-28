package com.example.transactiontrack.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Transaction.class}, version = 1)
public abstract class TransactionDatabase extends RoomDatabase {
    public abstract TransactionDAO getTransactionDAO();
    private static TransactionDatabase dbInstance;
    public static synchronized TransactionDatabase getInstance(Context context) {
        if(dbInstance == null) {
            dbInstance = Room.databaseBuilder(context.getApplicationContext(),
                            TransactionDatabase.class, "transaction_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return dbInstance;
    }
}
