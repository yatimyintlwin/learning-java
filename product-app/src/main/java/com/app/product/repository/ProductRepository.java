package com.app.product.repository;

import com.app.product.model.Product;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.*;

@Repository
public class ProductRepository {
    private final DynamoDbClient dynamoDbClient;
    private final String tableName = "Products";

    public ProductRepository(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public void save(Product product) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("id", AttributeValue.fromS(product.getId()));
        item.put("name", AttributeValue.fromS(product.getName()));
        item.put("description", AttributeValue.fromS(product.getDescription()));
        item.put("price", AttributeValue.fromN(product.getPrice().toString()));

        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .build();

        dynamoDbClient.putItem(request);
    }

    public Product findById(String id) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("id", AttributeValue.fromS(id));

        GetItemRequest request = GetItemRequest.builder()
                .tableName(tableName)
                .key(key)
                .build();

        Map<String, AttributeValue> item = dynamoDbClient.getItem(request).item();

        if (item == null || item.isEmpty()) {
            return null;
        }
        return mapToProduct(item);
    }

    public List<Product> findAll() {
        ScanRequest request = ScanRequest.builder()
                .tableName(tableName)
                .build();

        ScanResponse response = dynamoDbClient.scan(request);

        List<Product> products = new ArrayList<>();
        for (Map<String, AttributeValue> item : response.items()) {
            products.add(mapToProduct(item));
        }
        return products;
    }

    public void deleteById(String id) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("id", AttributeValue.fromS(id));

        DeleteItemRequest request = DeleteItemRequest.builder()
                .tableName(tableName)
                .key(key)
                .build();
        dynamoDbClient.deleteItem(request);
    }

    private Product mapToProduct(Map<String, AttributeValue> item) {
        return Product.builder()
                .id(item.get("id").s())
                .name(item.get("name").s())
                .description(item.get("description").s())
                .price(Double.parseDouble(item.get("price").n()))
                .build();
    }
}