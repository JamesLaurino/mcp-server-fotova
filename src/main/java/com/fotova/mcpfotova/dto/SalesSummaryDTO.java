package com.fotova.mcpfotova.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SalesSummaryDTO {

    private double totalRevenue;
    private int totalOrders;
    private double averageOrderValue;
    private int completedOrders;
    private int pendingOrders;

    public SalesSummaryDTO(double totalRevenue, int totalOrders,
                           double averageOrderValue, int completedOrders,
                           int pendingOrders) {
        this.totalRevenue = totalRevenue;
        this.totalOrders = totalOrders;
        this.averageOrderValue = averageOrderValue;
        this.completedOrders = completedOrders;
        this.pendingOrders = pendingOrders;
    }
}