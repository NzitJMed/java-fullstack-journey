package com.nzitjmed.week1.day03;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    @org.testng.annotations.Test
    @DisplayName("Should create account with valid parameters")
    void testCreateAccount() {
        Account acc = new Account("ACC-001", "Alice", 1000.0);
        assertEquals("ACC-001", acc.getAccountNumber());
        assertEquals("Alice", acc.getOwnerName());
        assertEquals(1000.0, acc.getBalance());
    }

    @Test
    @DisplayName("Should deposit positive amount")
    void testDeposit() {
        Account acc = new Account("ACC-002", "Bob", 500.0);
        acc.deposit(200.0);
        assertEquals(700.0, acc.getBalance());
    }

    @Test
    @DisplayName("Should throw InsufficientFundsException when withdrawing too much")
    void testWithdrawInsufficientFunds() {
        Account acc = new Account("ACC-003", "Charlie", 100.0);
        assertThrows(InsufficientFundsException.class, () -> acc.withdraw(200.0));
    }

    @Test
    @DisplayName("Should transfer funds atomically between accounts")
    void testTransferTo() throws InsufficientFundsException {
        Account alice = new Account("ACC-004", "Alice", 1000.0);
        Account bob = new Account("ACC-005", "Bob", 500.0);

        alice.transferTo(bob, 300.0);

        assertEquals(700.0, alice.getBalance());
        assertEquals(800.0, bob.getBalance());
    }

    @Test
    @DisplayName("Should consider accounts equal if account numbers match")
    void testEquals() {
        Account a1 = new Account("ACC-006", "Alice", 100.0);
        Account a2 = new Account("ACC-006", "Bob", 200.0); // different owner, same number
        assertEquals(a1, a2);
    }

    @Test
    @DisplayName("Should sort accounts by creation time")
    void testCompareTo() {
        Account early = new Account("ACC-007", "Early", 100.0);
        // Small delay or use fixed times in constructor
        Account late = new Account("ACC-008", "Late", 100.0);
        assertTrue(early.compareTo(late) < 0);
    }


}
