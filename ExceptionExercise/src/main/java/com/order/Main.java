package com.order;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new OnlineOrder("ON123"));
        orders.add(new InStoreOrder("IS456"));

        for (Order order : orders) {
            try {
                order.process();
            } catch (PaymentFailedException e) {
                System.out.println("Caught specific exception: " + e.getMessage());
            } catch (OrderException e) {
                System.out.println("Caught general order exception: " + e.getMessage());
            } catch (InventoryException e) {
                System.out.println("Caught unchecked exception: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Caught unexpected exception: " + e.getMessage());
            }

            System.out.println("------------------------------");
        }
    }
}