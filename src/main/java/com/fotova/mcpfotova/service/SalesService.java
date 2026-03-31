package com.fotova.mcpfotova.service;

import com.fotova.mcpfotova.dto.SalesSummaryDTO;
import com.fotova.mcpfotova.dto.SalesDetailDTO;
import com.fotova.mcpfotova.dto.CategorySalesDTO;
import com.fotova.mcpfotova.repository.SalesRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;

    public SalesSummaryDTO getSalesSummary(LocalDateTime start, LocalDateTime end) {
        return salesRepository.getSalesSummary(start, end);
    }

    public List<SalesDetailDTO> getSalesDetails(LocalDateTime start, LocalDateTime end) {
        return salesRepository.getSalesDetails(start, end);
    }
    public List<CategorySalesDTO> getSalesByCategory(LocalDateTime start, LocalDateTime end) {
        return salesRepository.getSalesByCategory(start, end);
    }

    public List<SalesDetailDTO> getRecentSales(int limit) {
        return salesRepository.getRecentSales(limit);
    }
}
