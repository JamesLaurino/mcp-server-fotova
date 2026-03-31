package com.fotova.mcpfotova.tool;

import com.fotova.mcpfotova.dto.CategorySalesDTO;
import com.fotova.mcpfotova.dto.SalesDetailDTO;
import com.fotova.mcpfotova.dto.SalesSummaryDTO;
import com.fotova.mcpfotova.service.SalesService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SalesTools {
    @Autowired
    private SalesService salesService;

    @Tool(
            name = "get_sales_summary",
            description = """
            Get a summary of sales for a given period.
            Returns total revenue, number of orders, average order value,
            and number of completed vs pending orders.
            
            Use this when the user asks about:
            - revenue
            - sales performance
            - number of orders
            - business overview
            """
    )
    public SalesSummaryDTO getSalesSummary(String startDate, String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        return salesService.getSalesSummary(start, end);
    }

    @Tool(
            name = "get_sales_details",
            description = """
            Get a detailed list of individual sales transactions for a given period.
            Returns specific order details like order ID, items purchased, amounts, and statuses.
            
            Use this when the user asks about:
            - detailed transaction history
            - specific orders within a timeframe
            - individual sales records
            """
    )
    public List<SalesDetailDTO> getSalesDetails(String startDate, String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        return salesService.getSalesDetails(start, end);
    }

    @Tool(
            name = "get_sales_by_category",
            description = """
            Get sales performance broken down by product category for a given period.
            Returns revenue and number of items sold grouped by category/department.
            
            Use this when the user asks about:
            - best selling categories
            - category performance
            - revenue per product type
            - which departments are performing best
            """
    )
    public List<CategorySalesDTO> getSalesByCategory(String startDate, String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        return salesService.getSalesByCategory(start, end);
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
        // Pas besoin de conversion de date ici, on passe directement l'entier
        return salesService.getRecentSales(limit);
    }
}
