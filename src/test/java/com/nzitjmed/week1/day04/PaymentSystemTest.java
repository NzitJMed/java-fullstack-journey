package com.nzitjmed.week1.day04;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PaymentSystemTest {
    // ============================================
    // TEST 1: Credit card fee for amount > $1000
    // ============================================
    @Test
    @DisplayName("Credit card should charge 2% fee for amounts over 1000")
    void testCreditCardFee() {
        // Arrange
        CreditCardPayment cc = new CreditCardPayment("1234567890123456", "12/30", "123");
        // Act
        PaymentResult result = cc.process(1500.0);
        // Assert
        assertTrue(result.success());
        assertTrue(result.message().contains("fee"));
        assertTrue(result.message().contains("30.00")); // 2% of 1500 = 30
    }

    // ============================================
    // TEST 2: Credit card no fee for amount <= $1000
    // ============================================
    @Test
    @DisplayName("Credit card should not charge fee for amounts 1000 or less")
    void testCreditCardNoFee() {
        CreditCardPayment cc = new CreditCardPayment("1234567890123456", "12/30", "123");
        PaymentResult result = cc.process(500.00);
        assertTrue(result.success());
        assertFalse(result.message().contains("fee")); // No fee mentioned
    }

    // ============================================
    // TEST 3: Debit card rejects if amount > balance
    // ============================================
    @Test
    @DisplayName("Debit card should reject if amount exceeds available balance")
    void testDebitCardRejection() {
        DebitCardPayment dc = new DebitCardPayment("9876543210987654", "11/30", "456", 1000.0);

        PaymentResult result = dc.process(1500.0);

        assertFalse(result.success());
        assertTrue(result.message().contains("Insufficient"));
    }

    // ============================================
    // TEST 4: Debit card accepts if sufficient balance
    // ============================================
    @Test
    @DisplayName("Debit card should accept if sufficient balance")
    void testDebitCardSuccess() {
        DebitCardPayment dc = new DebitCardPayment("9876543210987654", "11/30", "456", 5000.0);

        PaymentResult result = dc.process(1500.0);

        assertTrue(result.success());
        assertTrue(result.message().contains("3500")); // 5000 - 1500 = 3500 remaining
    }

    // ============================================
    // TEST 5: PayPal invalid email throws exception
    // ============================================
    @Test
    @DisplayName("PayPal should throw exception for invalid email")
    void testPayPalInvalidEmail() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new PayPalPayment("invalid-email");
        });

        assertTrue(ex.getMessage().contains("Invalid"));
    }

    // ============================================
    // TEST 6: PayPal valid email processes
    // ============================================
    @Test
    @DisplayName("PayPal should process with valid email")
    void testPayPalValidEmail() {
        PayPalPayment paypal = new PayPalPayment("user@example.com");

        PaymentResult result = paypal.process(200.0);

        assertTrue(result.success());
        assertTrue(result.message().contains("user@example.com"));
    }

    // ============================================
    // TEST 7: Crypto converts to BTC
    // ============================================
    @Test
    @DisplayName("Crypto should convert amount to BTC")
    void testCryptoConversion() {
        CryptoPayment crypto = new CryptoPayment("1A2b3C4d5E6f7G8h");

        PaymentResult result = crypto.process(100.0);

        assertTrue(result.success());
        assertTrue(result.message().contains("BTC"));
        assertTrue(result.message().contains("volatile")); // Warning included
    }

    // ============================================
    // TEST 8: PaymentProcessor tracks history
    // ============================================
    @Test
    @DisplayName("PaymentProcessor should track payment history")
    void testProcessorHistory() {
        PaymentProcessor processor = new PaymentProcessor();
        CreditCardPayment cc = new CreditCardPayment("1234567890123456", "12/30", "123");

        processor.registerMethod(cc);
        processor.processPayment(cc, 500.0);

        assertEquals(1, processor.getHistory().size());
    }

    // ============================================
    // TEST 9: PaymentProcessor rejects null method
    // ============================================
    @Test
    @DisplayName("PaymentProcessor should reject null payment method")
    void testProcessorNullMethod() {
        PaymentProcessor processor = new PaymentProcessor();

        assertThrows(IllegalArgumentException.class, () -> {
            processor.registerMethod(null);
        });
    }

    // ============================================
    // TEST 10: Polymorphism — same interface, different implementations
    // ============================================
    @Test
    @DisplayName("Polymorphism: process any PaymentMethod uniformly")
    void testPolymorphism() {
        PaymentMethod cc = new CreditCardPayment("1234567890123456", "12/30", "123");
        PaymentMethod paypal = new PayPalPayment("user@test.com");
        PaymentMethod crypto = new CryptoPayment("1A2b3C4d5E6f7G8h");

        // All implement same interface, all return PaymentResult
        assertNotNull(cc.process(100.0));
        assertNotNull(paypal.process(100.0));
        assertNotNull(crypto.process(100.0));

        // Each has its own provider name
        assertEquals("Credit Card", cc.getProviderName());
        assertEquals("PayPal", paypal.getProviderName());
        assertEquals("Crypto", crypto.getProviderName());
    }

}
