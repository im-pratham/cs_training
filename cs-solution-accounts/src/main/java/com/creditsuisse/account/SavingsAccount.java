package com.creditsuisse.account;

import java.util.Currency;

public class SavingsAccount extends Account implements CurrencyAccount {

    @Override
    public void withdraw(int amount) {
        super.withdraw(amount);

        addToCollectedFees(1);
    }

    @Override
    public void deposit(int amount, Currency currency) {
    }

}
