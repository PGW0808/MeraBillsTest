package com.example.transactiontrack.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Handler;

public class Repository {
    private final TransactionDAO transactionDAO;
    ExecutorService executorService;
    Handler handler;

    public Repository(Application application) {
        TransactionDatabase db = TransactionDatabase.getInstance(application);
        transactionDAO = db.getTransactionDAO();
        executorService = Executors.newSingleThreadExecutor();
//        handler = new Handler(Looper.getMainLooper());
    }

    public void insertTransaction(Transaction transaction) {
        executorService.execute(() -> transactionDAO.insert(transaction));
    }

    public void deleteTransaction(Transaction transaction) {
        executorService.execute(() -> transactionDAO.delete(transaction));
    }

    public LiveData<List<Transaction>> getAllTransactions() {
        return transactionDAO.getAllTransactions();
    }
}
