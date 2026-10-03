package edu.course.lab02;

public final class BankAccount {
    private int balance;

    public BankAccount(int initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        balance = initialBalance;
    }

    public void deposit(int amount) {
        requirePositive(amount);
        balance = Math.addExact(balance, amount);
    }

    public void withdraw(int amount) {
        requirePositive(amount);
        if (amount > balance) {
            throw new IllegalArgumentException("Withdrawal exceeds balance");
        }
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    private static void requirePositive(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }
}
