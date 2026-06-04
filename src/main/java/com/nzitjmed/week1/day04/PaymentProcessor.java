package com.nzitjmed.week1.day04;

import java.util.ArrayList;
import java.util.List;

public class PaymentProcessor {
    private final List<PaymentMethod> methods = new ArrayList<>();
    private final List<PaymentResult> history = new ArrayList<>();

    public void registerMethod(PaymentMethod method) {
        if (method == null) {
            throw new IllegalArgumentException("Payment method cannot be null");
        }
        methods.add(method);
    }

    public PaymentResult processPayment(PaymentMethod method, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (!methods.contains(method)) {  // Fixed: ! added
            throw new IllegalArgumentException("Payment method not registered");
        }

        PaymentResult result = method.process(amount);
        history.add(result);
        return result;
    }

    public List<PaymentResult> getHistory() {
        return new ArrayList<>(history);
    }

    public void printSummary() {
        long successCount = history.stream().filter(PaymentResult::success).count();
        long failedCount = history.size() - successCount;

        System.out.println("=== Payment Summary ===");
        System.out.println("Total transactions: " + history.size());
        System.out.println("Successful: " + successCount);
        System.out.println("Failed: " + failedCount);
    }
}
