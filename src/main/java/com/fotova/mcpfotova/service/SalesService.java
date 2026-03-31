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

    public SalesSummaryDTO getSalesSummary() {
        return salesRepository.getSalesSummary();
    }

    public List<SalesDetailDTO> getSalesDetails() {
        return salesRepository.getSalesDetails();
    }
    public List<CategorySalesDTO> getSalesByCategory() {
        return salesRepository.getSalesByCategory();
    }

    public List<SalesDetailDTO> getRecentSales(int limit) {
        return salesRepository.getRecentSales(limit);
    }
}
