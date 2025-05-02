package com.order;

abstract class Order {
    String orderId;

    public Order(String orderId){
        this.orderId = orderId;
    }

    public abstract void process() throws OrderException;
}
