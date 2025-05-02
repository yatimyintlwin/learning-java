package com.order;

public class InStoreOrder extends Order {
    public InStoreOrder(String orderId) {
        super(orderId);
    }

    @Override
    public void process() {
        System.out.println("Processing order: InStoreOrder");
        throw new InventoryException("Inventory error for in-store order: " + orderId);
    }
}
