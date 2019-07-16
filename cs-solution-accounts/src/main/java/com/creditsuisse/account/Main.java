package com.creditsuisse.account;

import java.util.Currency;

public class Main {
    public static void main(String[] args) {
        Account account = new CheckingAccount();
        account.deposit(1_23);
        System.out.println(account);

        Currency c = Currency.getInstance("GBP");
        System.out.println(c.getDisplayName());
    }
}
