package com.fotova.mcpfotova.service;

import com.fotova.mcpfotova.dto.SalesSummaryDTO;
import com.fotova.mcpfotova.repository.SalesRepository;
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
}
