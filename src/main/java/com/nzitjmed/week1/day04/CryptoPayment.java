package com.nzitjmed.week1.day04;

import jdk.dynalink.beans.StaticClass;

import java.util.UUID;
// CryptoPayment class converts amount to BTC with volatility warning
public class CryptoPayment implements  PaymentMethod{
    private final String walletAddress;
    private static final double BTC_CONVERSION_RATE= 0.000025; // example fixed rate
    public CryptoPayment(String walletAddress) {
        if (walletAddress == null || walletAddress.isBlank()) {
            throw new IllegalArgumentException("WalletAddress cannot be null or blank");
        }
        this.walletAddress = walletAddress;
    }
    @Override
    public PaymentResult process(double amount){
        double btcAmount=amount * BTC_CONVERSION_RATE;
        String transactionId = "CRYPTO-" + UUID.randomUUID().toString().substring(0,8);
        String message =String.format("Paid $%.2f USD (%.6f BTC) using Crypto wallet %s. Note: Cryptocurrency rates are volatile.",amount,btcAmount,walletAddress);
        return  new PaymentResult(true,transactionId,message);
    }

    @Override
    public String getProviderName() {
        return "Crypto";
    }
    public String getWalletAddress() {
        return walletAddress;
    }
}
