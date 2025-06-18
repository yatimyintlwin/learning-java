package com.app.product.repository.impl;

import com.app.product.model.Product;
import com.app.product.repository.ProductRepository;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final DynamoDbClient dynamoDbClient;
    private final String tableName = "Products";
    private final String partitionKeyValue = "product";

    public ProductRepositoryImpl(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    @Override
    public void save(Product product) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("pk", AttributeValue.fromS(partitionKeyValue));
        item.put("id", AttributeValue.fromS(product.getId()));
        item.put("name", AttributeValue.fromS(product.getName()));
        item.put("description", AttributeValue.fromS(product.getDescription()));
        item.put("price", AttributeValue.fromN(product.getPrice().toString()));

        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .returnValues(ReturnValue.ALL_OLD)
                .build();

        dynamoDbClient.putItem(request);
    }

    @Override
    public void update(Product product) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("pk", AttributeValue.fromS(partitionKeyValue));
        key.put("id", AttributeValue.fromS(product.getId()));

        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":name", AttributeValue.fromS(product.getName()));
        expressionAttributeValues.put(":description", AttributeValue.fromS(product.getDescription()));
        expressionAttributeValues.put(":price", AttributeValue.fromN(product.getPrice().toString()));

        UpdateItemRequest request = UpdateItemRequest.builder()
                .tableName(tableName)
                .key(key)
                .updateExpression("SET #n = :name, description = :description, price = :price")
                .expressionAttributeNames(Map.of("#n", "name"))
                .expressionAttributeValues(expressionAttributeValues)
                .returnValues(ReturnValue.ALL_OLD)
                .build();

        dynamoDbClient.updateItem(request);
    }

    @Override
    public Product findByPkAndId(String pk, String id) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("pk", AttributeValue.fromS(pk));
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

    @Override
    public List<Product> getAll() {
        QueryRequest request = QueryRequest.builder()
                .tableName(tableName)
                .keyConditionExpression("pk = :pkVal")
                .expressionAttributeValues(Map.of(":pkVal", AttributeValue.fromS(partitionKeyValue)))
                .build();

        QueryResponse response = dynamoDbClient.query(request);

        List<Product> products = new ArrayList<>();
        for (Map<String, AttributeValue> item : response.items()) {
            products.add(mapToProduct(item));
        }
        return products;
    }

    @Override
    public void deleteByPkAndId(String pk, String id) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("pk", AttributeValue.fromS(pk));
        key.put("id", AttributeValue.fromS(id));

        DeleteItemRequest request = DeleteItemRequest.builder()
                .tableName(tableName)
                .key(key)
                .build();

        dynamoDbClient.deleteItem(request);
    }

    private Product mapToProduct(Map<String, AttributeValue> item) {
        return Product.builder()
                .pk(item.get("pk").s())
                .id(item.get("id").s())
                .name(item.get("name").s())
                .description(item.get("description").s())
                .price(Double.parseDouble(item.get("price").n()))
                .build();
    }

}
