package com.example.transactiontrack.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.transactiontrack.model.Repository;
import com.example.transactiontrack.model.Transaction;

import java.util.List;

public class TransactionViewModel extends AndroidViewModel {
    private final Repository myRepository;
    private final LiveData<Integer> totalAmount;

    public TransactionViewModel(@NonNull Application application) {
        super(application);
        this.myRepository = new Repository(application);

        // Automatically calculate total amount when transactions update
        this.totalAmount = Transformations.map(myRepository.getAllTransactions(), transactions -> {
            int total = 0;
            if (transactions != null) {
                for (Transaction transaction : transactions) {
                    total += Integer.parseInt(transaction.getAmount());
                }
            }
            return total;
        });
    }

    public LiveData<List<Transaction>> getAllTransactions() {
        return myRepository.getAllTransactions();
    }

    public LiveData<Integer> getTotalAmount() {
        return totalAmount;
    }

    public void insertTransaction(Transaction transaction) {
        myRepository.insertTransaction(transaction);
    }

    public void deleteTransaction(Transaction transaction) {
        myRepository.deleteTransaction(transaction);
    }
}
