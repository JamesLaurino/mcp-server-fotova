package com.fotova.mcpfotova.repository;

import com.fotova.mcpfotova.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ClientDTO> getAllClient() {
        String sql = """
            SELECT * FROM client_entity;
        """;

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new ClientDTO(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getBoolean("is_active"),
                        rs.getInt("address_id")
                )
        );
    }
}
