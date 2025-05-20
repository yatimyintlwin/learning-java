import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoopTester {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Laptop", 1200),
                new Product("Milk", 22),
                new Product("Smartphone", 239.7),
                new Product("Notebook", 17.9),
                new Product("Bread", 15.8)
        );

        List<Product> filteredProducts = new ArrayList<>();
        for(Product product : products) {
            if(product.getPrice() > 100) {
                filteredProducts.add(product);
            }
        }
        System.out.println("Filtered products: " + filteredProducts);

        List<String> upperCaseNames = new ArrayList<>();
        for(Product product : products) {
            upperCaseNames.add(product.getName().toUpperCase());
        }
        System.out.println("Uppercase product names: " + upperCaseNames);

        double totalPrice = 0;
        for(Product product : products) {
           totalPrice += product.getPrice();
        }
        System.out.println("Total price is: " + totalPrice);
    }
}
