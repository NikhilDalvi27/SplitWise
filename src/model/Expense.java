package model;

import java.util.List;

public abstract class Expense {
    private List<Split> splits;
    private  User paidBy;
    private ExpenseMeta expenseMeta;

    private double amount;

    public Expense(double amount, User paidBy, List<Split> splits, ExpenseMeta expenseMeta) {
        this.splits = splits;
        this.paidBy = paidBy;
        this.amount = amount;
        this.expenseMeta = expenseMeta;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public ExpenseMeta getExpenseMeta() {
        return expenseMeta;
    }

    public void setExpenseMeta(ExpenseMeta expenseMeta) {
        this.expenseMeta = expenseMeta;
    }

    public abstract boolean validate();  // here switch case depending upon the ExpenseType will make the code dirty
}
