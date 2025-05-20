import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class InventoryManagementSystem {
    public static void main(String[] args) {
        List<Product> products = null;

        products.add(new Electronic("Laptop", 1200.99, 24));
        products.add(new Electronic("Smartphone", 899.49, 12));
        products.add(new Electronic("TV", 450.0, 36));

        products.add(new Grocery("Rice", 45.0, false));
        products.add(new Grocery("Milk", 3.99, true));
        products.add(new Grocery("Eggs", 2.49, true));


        try {
            List<Product> expensiveProducts = products.stream()
                    .filter(product -> product.getPrice() > 100)
                    .toList();
            System.out.println("Expensive Products: " + expensiveProducts);


            List<String> upperCaseNames = products.stream()
                    .map(product -> product.getName().toUpperCase())
                    .toList();
            System.out.println("Uppercase Product Names: " + upperCaseNames);


            double totalPrice = products.stream()
                    .mapToDouble(Product::getPrice)
                    .sum();
            System.out.println("Total Price of Products: " + totalPrice);


            Optional<Product> mostExpensive = products.stream()
                    .max(Comparator.comparingDouble(Product::getPrice));
            System.out.println("Most Expensive Product: " + mostExpensive);

        } catch (NullPointerException e) {
            System.out.println("Null value encountered: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
