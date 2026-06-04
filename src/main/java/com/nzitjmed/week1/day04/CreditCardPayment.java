package com.nzitjmed.week1.day04;

import java.util.UUID;

public class CreditCardPayment extends CardPayment {
    public CreditCardPayment(String cardNumber, String expiryDate, String cvv) {
        super(cardNumber, expiryDate, cvv);  // Fixed parameter order
    }

    @Override
    public PaymentResult process(double amount) {
        if (!validateCardNumber()) {
            return new PaymentResult(false, null, "Invalid card number");
        }
        if (isExpired()) {
            return new PaymentResult(false, null, "Card has expired");
        }

        double fee = (amount > 1000) ? amount * 0.02 : 0;  // Fixed: 2% fee
        double total = amount + fee;
        String transactionId = "CC-" + UUID.randomUUID().toString().substring(0, 8);
        // Message MUST contain "fee" when fee > 0, and MUST NOT contain "fee" when fee == 0
        String message;
        if (fee > 0) {
            message = String.format("Paid $%.2f (fee: $%.2f) using Credit Card", amount, fee);
        } else {
            message = String.format("Paid $%.2f using Credit Card", amount);
        }

        return new PaymentResult(true, transactionId, message);

    }

    @Override
    public String getProviderName() {
        return "Credit Card";
    }

}
