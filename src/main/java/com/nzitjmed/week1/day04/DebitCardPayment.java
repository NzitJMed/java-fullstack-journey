package com.nzitjmed.week1.day04;

import java.util.UUID;
public class DebitCardPayment extends CardPayment{
    private double availableBalance;
    public DebitCardPayment(String cardNumber,String expiryDate, String cvv,double availableBalance) {
        super(cardNumber,expiryDate,cvv);
        if(availableBalance < 0) {
            throw new IllegalArgumentException("availableBalance cannot be negative");
        }
        this.availableBalance = availableBalance;
    }
    @Override
    public PaymentResult process(double amount) {
        if(!validateCardNumber()) {
            return new PaymentResult(false,null,"Invalid card number");
        }
        if(isExpired()){
            return new PaymentResult(false,null,"Debit card is expired");
        }
        if(amount >availableBalance) {
            return new PaymentResult(false,null,String .format("Insufficient balance: $%.2f available," +
                    "requested $%.2f",availableBalance,amount ));
        }
        availableBalance -= amount;
        String transactionId = "DC-" + UUID.randomUUID().toString().substring(0,8);
        return new PaymentResult(true,transactionId,String.format("Paid %.2f using Debit Card. Remaining balance: %.2f",amount,availableBalance));

    }
    @Override
    public  String getProviderName() {
        return "Debit Card ";
    }


}
