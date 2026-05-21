package com.fotova.mcpfotova.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter @Getter
public class DetailOrdersClientDTO {
    private String email;
    private Integer totalOrders;
    private Integer totalAmountEuro;
    private LocalDateTime lastOrderCreated;

    public DetailOrdersClientDTO(String email, Integer totalOrders, Integer totalAmountEuro, LocalDateTime lastOrderCreated) {
        this.email = email;
        this.totalOrders = totalOrders;
        this.totalAmountEuro = totalAmountEuro;
        this.lastOrderCreated = lastOrderCreated;
    }
}
