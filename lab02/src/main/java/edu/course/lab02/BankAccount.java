package edu.course.lab02;

public class BankAccount {

    private int balance;

public BankAccount(int balance) {
    if (balance <0) {
        throw new IllegalArgumentException("Отрицательный, йоу");
    }

    this.balance = balance;
}

public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма пополнения ток положительная");
        }

        balance += amount;
    }

public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма для снятия ток положительная");
        }

        if (amount > balance) {
            throw new IllegalStateException("Мало мани");
        }

        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }
}
