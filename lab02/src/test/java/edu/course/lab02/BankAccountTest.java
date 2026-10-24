
package edu.course.lab02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void shouldWithdrawMoney() {
        BankAccount account = new BankAccount(1000);

        account.withdraw(300);

        assertEquals(700, account.getBalance());
    }

    @Test
    void shouldThrowExceptionWhenWithdrawingMoreThanBalance() {
        BankAccount account = new BankAccount(1000);

        assertThrows(IllegalStateException.class, () -> account.withdraw(1500));
    }

    @Test
    void shouldNotAllowNegativeInitialBalance() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(-100));
    }

    @Test
    void shouldNotAllowNonPositiveDeposit() {
        BankAccount account = new BankAccount(1000);

        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
    }

    @Test
    void shouldNotAllowNonPositiveWithdrawal() {
        BankAccount account = new BankAccount(1000);

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-100));
    }
}
