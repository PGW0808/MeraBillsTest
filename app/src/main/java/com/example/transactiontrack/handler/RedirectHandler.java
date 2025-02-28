package com.example.transactiontrack.handler;

import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.example.transactiontrack.AddNewTransaction;

public class RedirectHandler {
    private final FragmentActivity activity;

    public RedirectHandler(FragmentActivity activity) {
        this.activity = activity;
    }

    public void onAddClicked(View view) {
        AddNewTransaction dialog = new AddNewTransaction();
        dialog.show(activity.getSupportFragmentManager(), "AddTransactionDialog");
    }
}
