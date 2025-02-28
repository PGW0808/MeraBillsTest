package com.example.transactiontrack;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.transactiontrack.databinding.ActivityMainBinding;
import com.example.transactiontrack.handler.DeleteTransactionHandler;
import com.example.transactiontrack.handler.RedirectHandler;
import com.example.transactiontrack.model.Transaction;
import com.example.transactiontrack.model.TransactionDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TransactionDatabase transactionDatabase;
    private ActivityMainBinding mainBinding;
    private RedirectHandler handlers;
    private DeleteTransactionHandler deleteHandler;
    private TransactionAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);


        // Use Data Binding
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        handlers = new RedirectHandler(this);
        mainBinding.setRedirect(handlers);

        // Initialize Delete Handler
        deleteHandler = new DeleteTransactionHandler(this);

        // Initialize Adapter with Delete Handler
        myAdapter = new TransactionAdapter(new ArrayList<>(), deleteHandler);
        mainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mainBinding.recyclerView.setAdapter(myAdapter);

        // Observe Transactions and Update UI
        transactionDatabase = TransactionDatabase.getInstance(this);
        transactionDatabase.getTransactionDAO().getAllTransactions().observe(this, transactions -> {
            myAdapter.setTransactionsArrayList(transactions);

            // Calculate Total Amount
            int totalAmount = 0;
            if (transactions != null) {
                for (Transaction transaction : transactions) {
                    try {
                        totalAmount += Integer.parseInt(transaction.getAmount().trim());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }

            // Update UI with Total Amount
            mainBinding.textViewTotalAmount.setText("Total Amount = ₹" + totalAmount);
        });

        ViewCompat.setOnApplyWindowInsetsListener(mainBinding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
