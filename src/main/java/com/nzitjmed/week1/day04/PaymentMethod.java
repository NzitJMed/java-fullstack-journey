package com.nzitjmed.week1.day04;
public interface PaymentMethod {

    PaymentResult process(double amount);
    String getProviderName();

}
