package com.fotova.mcpfotova.tool;

import com.fotova.mcpfotova.dto.CategorySalesDTO;
import com.fotova.mcpfotova.dto.SalesDetailDTO;
import com.fotova.mcpfotova.dto.SalesSummaryDTO;
import com.fotova.mcpfotova.service.SalesService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SalesTools {
    @Autowired
    private SalesService salesService;

    @Tool(
            name = "get_sales_summary",
            description = """
        Get a summary of sales including:
        - total revenue
        - total number of orders
        - average order value
        - number of completed and pending orders
    """
    )
    public Map<String, Object> getSalesSummary() {
        SalesSummaryDTO dto = salesService.getSalesSummary();
        return Map.of(
                "totalRevenue", dto.getTotalRevenue(),
                "totalOrders", dto.getTotalOrders(),
                "avgOrderValue", dto.getAverageOrderValue(),
                "completedOrders", dto.getCompletedOrders(),
                "pendingOrders", dto.getPendingOrders()
        );
    }

    @Tool(
            name = "get_sales_details",
            description = """
        Get a detailed list of individual sales transactions.
        Returns order ID, client username, product, quantity, prices, date, completion status, and category.
    """
    )
    public List<Map<String, Object>> getSalesDetails() {
        List<SalesDetailDTO> details = salesService.getSalesDetails();

        return details.stream()
                .map(d -> Map.<String, Object>of(
                        "orderId", d.getOrderId(),
                        "clientUsername", d.getClientUsername(),
                        "productName", d.getProductName(),
                        "quantity", d.getQuantity(),
                        "unitPrice", d.getUnitPrice(),
                        "totalPrice", d.getTotalPrice(),
                        "orderDate", d.getOrderDate().toString(), // string ISO
                        "isCompleted", d.isCompleted(),
                        "categoryName", d.getCategoryName()
                ))
                .collect(Collectors.toList());
    }

    @Tool(
            name = "get_sales_by_category",
            description = """
        Get sales performance broken down by product category.
        Returns category name, total units sold, total revenue, and number of orders.
    """
    )
    public List<Map<String, Object>> getSalesByCategory() {
        List<CategorySalesDTO> categories = salesService.getSalesByCategory();

        return categories.stream()
                .map(c -> Map.<String, Object>of(
                        "categoryName", c.getCategoryName(),
                        "totalUnitsSold", c.getTotalUnitsSold(),
                        "totalRevenue", c.getTotalRevenue(),
                        "numberOfOrders", c.getNumberOfOrders()
                ))
                .collect(Collectors.toList());
    }

    @Tool(
            name = "get_recent_sales",
            description = """
            Get a list of the most recent sales transactions.
            Returns the latest orders up to the specified limit (e.g., the last 5 or 10 orders).
            
            Use this when the user asks about:
            - the latest orders
            - recent transactions
            - "what did we just sell?"
            """
    )
    public List<SalesDetailDTO> getRecentSales(int limit) {
        return salesService.getRecentSales(limit);
    }
}
