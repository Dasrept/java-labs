package edu.course.lab02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankAccountTest {
    @Test
    void withdrawsAndDepositsAmounts() {
        BankAccount account = new BankAccount(100);

        account.withdraw(35);
        account.deposit(10);

        assertEquals(75, account.getBalance());
    }

    @Test
    void rejectsNegativeInitialBalance() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(-1));
    }

    @Test
    void rejectsNonPositiveDeposit() {
        BankAccount account = new BankAccount(10);

        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-1));
    }

    @Test
    void rejectsNonPositiveWithdrawal() {
        BankAccount account = new BankAccount(10);

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-1));
    }

    @Test
    void rejectsWithdrawalAboveBalanceWithoutChangingBalance() {
        BankAccount account = new BankAccount(10);

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(11));

        assertEquals(10, account.getBalance());
    }

    @Test
    void rejectsDepositThatWouldOverflow() {
        BankAccount account = new BankAccount(Integer.MAX_VALUE);

        assertThrows(ArithmeticException.class, () -> account.deposit(1));

        assertEquals(Integer.MAX_VALUE, account.getBalance());
    }
}
