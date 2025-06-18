package com.app.product.model;

import lombok.*;

@Data
@Builder
public class Product {
    private String pk;
    private String id;
    private String name;
    private String description;
    private Double price;
}