package com.nzitjmed.week1.day04;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class CardPayment implements PaymentMethod{
    protected final String cardNumber;
    protected final String expiryDate;
    protected final String cvv;

    public CardPayment(String cardNumber, String expiryDate, String cvv) {
        if (cardNumber == null || cardNumber.isBlank() || !cardNumber.matches("\\d{16}")) {
            throw new IllegalArgumentException("Card number must be exactly 16 digits.");
        }
        if (expiryDate == null || expiryDate.isBlank() || !expiryDate.matches("(0[1-9]|1[0-2])/\\d{2}")) {
            throw new IllegalArgumentException("Expiry date must be in MM/yy format.");
        }
        if (cvv == null || cvv.isBlank() || !cvv.matches("\\d{3,4}")) {
            throw new IllegalArgumentException("CVV must be 3 or 4 digits.");
        }

        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    protected boolean validateCardNumber() {
        return cardNumber.matches("\\d{16}");
    }

    protected boolean isExpired() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
            YearMonth expiry = YearMonth.parse(expiryDate, formatter);
            return expiry.isBefore(YearMonth.now());
        } catch (DateTimeParseException e) {
            return true;
        }
    }

}
