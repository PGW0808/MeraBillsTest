package com.example.transactiontrack.model;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "transaction_table")
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "amount")
    private String amount;

    @ColumnInfo(name = "method")
    private String method;

    @ColumnInfo(name = "provider")
    private String provider;

    public Transaction(String amount, String method, String provider) {
        this.amount = amount;
        this.method = method;
        this.provider = provider;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }



    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
