package com.example.springboot.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductDto {
    private UUID id;
    private String name;
    private BigDecimal value;

    // Getters e Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}

