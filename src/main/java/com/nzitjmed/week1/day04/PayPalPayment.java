package com.nzitjmed.week1.day04;

import java.util.UUID;
import java.util.regex.Pattern;

// PayPalPayment class with email validation, no fees
public class PayPalPayment implements PaymentMethod{
    private final String email;
    public PayPalPayment(String email) {
        if(email == null || email.isBlank() ||!isValidEmail(email)){
            throw new IllegalArgumentException("Invalid email address: "+email);
        }
        this.email = email;
    }
    private boolean isValidEmail(String email) {
        //Simple regex for email validation
        String emailRegex = "^[\\w-.]+@[\\w-.]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(emailRegex, email);
    }
    @Override
    public  PaymentResult process(double amount){
        String transactionId =  "PAYPAL-" + UUID.randomUUID().toString().substring(0,8);
        return  new PaymentResult(true, transactionId,String.format("Paid $%.2f using PayPal(email:%s).No fees applied.",amount,email));
    }

    @Override
    public String getProviderName() {
        return "PayPal";
    }

    public String getEmail() {
        return email;

    }

}
