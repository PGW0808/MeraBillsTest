package com.example.transactiontrack;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.transactiontrack.databinding.ActivityAddNewTransactionBinding;
import com.example.transactiontrack.handler.AddTransactionHandler;
import com.example.transactiontrack.model.Transaction;
import com.example.transactiontrack.viewmodel.TransactionViewModel;

public class AddNewTransaction extends DialogFragment {

    private AddTransactionHandler handlers;
    private ActivityAddNewTransactionBinding addTransactionBinding;
    private Transaction transaction;
    private TransactionViewModel transactionViewModel;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_add_new_transaction, null);
        builder.setView(view);

        // Set up Data Binding
        addTransactionBinding = DataBindingUtil.bind(view);
        if (addTransactionBinding == null) {
            throw new IllegalStateException("Binding is null");
        }

        // Initialize ViewModel
        transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);

        // Initialize empty Transaction object
        transaction = new Transaction("", "", ""); // Default method as "Cash"

        // Initialize Handler
        handlers = new AddTransactionHandler(transaction, requireActivity(), transactionViewModel);

        // Bind objects to XML
        addTransactionBinding.setTransaction(transaction);
        addTransactionBinding.setAddTransactionHandler(handlers);

        // Setup Spinner
        setupMethodSpinner();

        return builder.create();
    }

    private void setupMethodSpinner() {
        // Define options for the dropdown
        String[] methods = {"Cash", "Bank Transfer", "Credit Card"};

        // Create ArrayAdapter with predefined methods
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, methods);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set adapter to Spinner
        addTransactionBinding.spinnerMethod.setAdapter(adapter);

        // Set default selection
        addTransactionBinding.spinnerMethod.setSelection(0); // Default: Cash

        // Handle selection event
        addTransactionBinding.spinnerMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                transaction.setMethod(methods[position]); // Update transaction method
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                transaction.setMethod(""); // Default if nothing selected
            }
        });
    }
}
