package com.fotova.mcpfotova.tool;

import com.fotova.mcpfotova.dto.SalesSummaryDTO;
import com.fotova.mcpfotova.service.SalesService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
    public SalesSummaryDTO getSalesSummary(
            String startDate,
            String endDate
    ) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);

        return salesService.getSalesSummary(start, end);
    }
}
