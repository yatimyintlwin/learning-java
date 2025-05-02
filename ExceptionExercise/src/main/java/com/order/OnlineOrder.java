package com.order;

public class OnlineOrder extends Order{
    public OnlineOrder(String orderId) {
        super(orderId);
    }

    @Override
    public void process() throws PaymentFailedException {
        System.out.println("Processing order: OnlineOrder");

        throw new PaymentFailedException("Payment failed for online order: " + orderId);
    }
}