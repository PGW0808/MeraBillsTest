package com.example.transactiontrack.handler;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.transactiontrack.MainActivity;
import com.example.transactiontrack.model.Transaction;
import com.example.transactiontrack.viewmodel.TransactionViewModel;

public class AddTransactionHandler {
    Transaction transactions;
    TransactionViewModel myViewModel;
    Context context;

    public AddTransactionHandler(Transaction transactions, Context context, TransactionViewModel viewModel) {
        this.transactions = transactions;
        this.context = context;
        this.myViewModel = viewModel;
    }

    public void onSubmitBtnclicked(View view) {
        myViewModel.insertTransaction(transactions);
        Toast.makeText(context, "Transaction added successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }


}
