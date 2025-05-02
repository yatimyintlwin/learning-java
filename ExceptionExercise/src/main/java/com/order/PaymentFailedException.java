package com.order;

public class PaymentFailedException extends OrderException {
    public PaymentFailedException(String message) {
        super(message);
    }
}
