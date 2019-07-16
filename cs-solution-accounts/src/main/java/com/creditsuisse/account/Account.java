package com.creditsuisse.account;

public class Account {
    private int balance;
    private int collectedFees;

    public void deposit(int amount) {
        assertPositive(amount);
        balance += amount;
    }

    public void withdraw(int amount) {
        assertPositive(amount);
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    protected void addToCollectedFees(int fee) {
        assertPositive(fee);
        collectedFees += fee;
    }

    public int getCollectedFees() {
        return collectedFees;
    }

    private static void assertPositive(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("negative amounts are not permitted");
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", collectedFees=" + collectedFees +
                '}';
    }
}
