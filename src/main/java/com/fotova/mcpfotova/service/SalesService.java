package com.fotova.mcpfotova.service;

import com.fotova.mcpfotova.dto.CategorySalesDTO;
import com.fotova.mcpfotova.dto.SalesDetailDTO;
import com.fotova.mcpfotova.dto.SalesSummaryDTO;
import com.fotova.mcpfotova.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;

    public SalesSummaryDTO getSalesSummary(String startDate, String endDate) {
        return salesRepository.getSalesSummary(startDate, endDate);
    }

    public List<SalesDetailDTO> getSalesDetails(String startDate, String endDate) {
        return salesRepository.getSalesDetails(startDate,endDate);
    }
    public List<CategorySalesDTO> getSalesByCategory(String startDate, String endDate) {
        return salesRepository.getSalesByCategory(startDate,endDate);
    }

    public List<SalesDetailDTO> getRecentSales(int limit) {
        return salesRepository.getRecentSales(limit);
    }
}
