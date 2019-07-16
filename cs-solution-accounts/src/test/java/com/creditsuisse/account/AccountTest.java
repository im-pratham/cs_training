package com.creditsuisse.account;

import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void givenZeroBalanceEqualDepositAndWithdrawGivesZeroBalance() {
        Account a =  new Account();
        assertEquals(0, a.getBalance());
        a.deposit(100);
        a.withdraw(100);
        assertEquals(0, a.getBalance());
    }

    @Test
    public void givenZeroBalanceWithrawResultsInNegativeBalance() {
        Account a =  new Account();
        assertEquals(0, a.getBalance());
        a.withdraw(100);
        assertTrue(a.getBalance() < 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeDepositsNotAllowed() {
        Account a = new Account();
        a.deposit(-100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeWithdrawalNotAllowed() {
        Account a = new Account();
        a.withdraw(-100);
    }

}
