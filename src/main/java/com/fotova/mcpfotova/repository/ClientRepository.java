package com.fotova.mcpfotova.repository;

import com.fotova.mcpfotova.dto.ClientDTO;
import com.fotova.mcpfotova.dto.DetailOrdersClientDTO;
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

    public List<DetailOrdersClientDTO> getDetailOrdersClient() {
        String sql = """
            SELECT
                c.email as email,
                COUNT(DISTINCT o.id) AS total_orders,
                SUM(op.quantity_product * p.price) AS total_amount_euro,
                MAX(o.create_at) AS last_order_date
            FROM client_entity c
                     JOIN order_entity o ON o.client_id = c.id
                     JOIN order_product_entity op ON op.order_id = o.id
                     JOIN product_entity p ON p.id= op.product_id
            GROUP BY c.id, c.email;
        """;

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new DetailOrdersClientDTO(
                        rs.getString("email"),
                        rs.getInt("total_orders"),
                        rs.getInt("total_amount_euro"),
                        rs.getTimestamp("last_order_date").toLocalDateTime()
                )
        );
    }
}
