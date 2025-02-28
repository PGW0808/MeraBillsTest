package com.example.transactiontrack.handler;

import android.content.Context;
import android.widget.Toast;

import com.example.transactiontrack.model.Transaction;
import com.example.transactiontrack.model.TransactionDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeleteTransactionHandler {
    private Context context;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public DeleteTransactionHandler(Context context) {
        this.context = context;
    }

    public void deleteTransaction(Transaction transaction) {
        executorService.execute(() -> {
            TransactionDatabase.getInstance(context).getTransactionDAO().delete(transaction);  // ✅ Fixed method name
            ((android.app.Activity) context).runOnUiThread(() ->
                    Toast.makeText(context, "Transaction Deleted", Toast.LENGTH_SHORT).show()
            );
        });
    }
}
