package com.fotova.mcpfotova.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class SalesDetailDTO {
    
    private int orderId;
    private String clientUsername;
    private String productName;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private LocalDateTime orderDate;
    private boolean isCompleted;
    private String categoryName;
    
    public SalesDetailDTO(int orderId, String clientUsername, String productName, 
                          int quantity, double unitPrice, double totalPrice,
                          LocalDateTime orderDate, boolean isCompleted, String categoryName) {
        this.orderId = orderId;
        this.clientUsername = clientUsername;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.isCompleted = isCompleted;
        this.categoryName = categoryName;
    }
}