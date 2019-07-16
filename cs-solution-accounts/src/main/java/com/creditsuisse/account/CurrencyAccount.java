package com.creditsuisse.account;

import java.util.Currency;

public interface CurrencyAccount {
    void deposit(int amount, Currency currency);
}
