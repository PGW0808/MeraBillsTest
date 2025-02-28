package com.example.transactiontrack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.transactiontrack.databinding.TransactionItemBinding;
import com.example.transactiontrack.handler.DeleteTransactionHandler;
import com.example.transactiontrack.model.Transaction;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {
    private List<Transaction> transactionsArrayList;
    private DeleteTransactionHandler deleteHandler;

    // Updated constructor
    public TransactionAdapter(List<Transaction> transactionsArrayList, DeleteTransactionHandler deleteHandler) {
        this.transactionsArrayList = transactionsArrayList;
        this.deleteHandler = deleteHandler;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TransactionItemBinding transactionItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.transaction_item,
                parent,
                false
        );
        return new TransactionViewHolder(transactionItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transaction transaction = transactionsArrayList.get(position);
        holder.bind(transaction);
    }

    @Override
    public int getItemCount() {
        return transactionsArrayList.size();
    }

    public void setTransactionsArrayList(List<Transaction> transactions) {
        this.transactionsArrayList.clear();
        this.transactionsArrayList.addAll(transactions);
        notifyDataSetChanged();
    }

    class TransactionViewHolder extends RecyclerView.ViewHolder {
        private final TransactionItemBinding binding;

        public TransactionViewHolder(@NonNull TransactionItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            // Handle delete button click
            binding.buttonDelete.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Transaction transactionToDelete = transactionsArrayList.get(position);
                    deleteHandler.deleteTransaction(transactionToDelete);
                }
            });
        }

        public void bind(Transaction transaction) {
            binding.setTransaction(transaction);
            binding.executePendingBindings();
        }
    }
}
