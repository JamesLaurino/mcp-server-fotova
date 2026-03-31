package com.fotova.mcpfotova.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CategorySalesDTO {
    
    private String categoryName;
    private int totalUnitsSold;
    private double totalRevenue;
    private int numberOfOrders;
    
    public CategorySalesDTO(String categoryName, int totalUnitsSold, 
                           double totalRevenue, int numberOfOrders) {
        this.categoryName = categoryName;
        this.totalUnitsSold = totalUnitsSold;
        this.totalRevenue = totalRevenue;
        this.numberOfOrders = numberOfOrders;
    }
}