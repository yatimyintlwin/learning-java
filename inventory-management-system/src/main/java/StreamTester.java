import java.util.Arrays;
import java.util.List;

public class StreamTester {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Laptop", 1200),
                new Product("Milk", 22),
                new Product("Smartphone", 239.7),
                new Product("Notebook", 17.9),
                new Product("Bread", 15.8)
        );

        List<Product> filteredProducts = products.stream()
                .filter(product -> product.getPrice() > 100)
                .toList();
        System.out.println("Filtered products: " + filteredProducts);

        List<String> upperCaseNames = products.stream()
                .map(product -> product.getName().toUpperCase())
                .toList();
        System.out.println("Uppercase product names: " + upperCaseNames);

        double totalPrice = products.stream()
                .mapToDouble(Product::getPrice)
                .reduce(0.0, Double::sum);
        System.out.println("Total price is: " + totalPrice);
    }
}
