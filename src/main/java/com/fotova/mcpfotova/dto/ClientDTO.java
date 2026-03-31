package com.fotova.mcpfotova.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClientDTO {

    private String username;
    private String password;
    private String email;
    private Boolean is_active;
    private Integer address_id;

    public ClientDTO(String username, String password, String email, Boolean is_active, Integer address_id) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.is_active = is_active;
        this.address_id = address_id;
    }
}