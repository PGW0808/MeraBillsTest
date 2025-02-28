package com.example.transactiontrack.model;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TransactionDAO {

    @Insert
    void insert(Transaction transaction);

    @Delete
    void delete(Transaction transaction);

    @Query("SELECT * FROM transaction_table")
    LiveData<List<Transaction>> getAllTransactions();
}