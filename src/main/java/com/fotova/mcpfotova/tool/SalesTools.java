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
        Get a summary of sales for a given period include:
        - total revenue
        - total number of orders
        - average order value
        - number of completed and pending orders

        The date format must be of the type : yyyy-mm-dd
    """
    )
    public Map<String, Object> getSalesSummary(String startDate, String endDate) {
        SalesSummaryDTO dto = salesService.getSalesSummary(startDate, endDate);
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
        Get a detailed list of individual sales transactions for a given period.
        Returns order ID, client username, product, quantity, prices, date, completion status, and category.
        The date format must be of the type : yyyy-mm-dd
    """
    )
    public List<Map<String, Object>> getSalesDetails(String startDate, String endDate) {
        List<SalesDetailDTO> details = salesService.getSalesDetails(startDate,endDate);

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
        Get sales performance broken down by product category for a given period.
        Returns category name, total units sold, total revenue, and number of orders.
        The date format must be of the type : yyyy-mm-dd
    """
    )
    public List<Map<String, Object>> getSalesByCategory(String startDate, String endDate) {
        List<CategorySalesDTO> categories = salesService.getSalesByCategory(startDate,endDate);

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
