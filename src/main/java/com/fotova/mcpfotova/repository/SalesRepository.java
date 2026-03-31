package com.fotova.mcpfotova.repository;

import com.fotova.mcpfotova.dto.SalesSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class SalesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public SalesSummaryDTO getSalesSummary(LocalDateTime startDate, LocalDateTime endDate) {

        String sql = """
            SELECT 
                COALESCE(SUM(op.quantity_product * p.price), 0) AS total_revenue,
                COUNT(DISTINCT o.id) AS total_orders,
                COALESCE(AVG(order_total.total), 0) AS avg_order_value,
                SUM(CASE WHEN o.is_done = true THEN 1 ELSE 0 END) AS completed_orders,
                SUM(CASE WHEN o.is_done = false THEN 1 ELSE 0 END) AS pending_orders
            FROM order_entity o
            LEFT JOIN order_product_entity op ON o.id = op.order_id
            LEFT JOIN product_entity p ON op.product_id = p.id
            LEFT JOIN (
                SELECT 
                    o2.id,
                    SUM(op2.quantity_product * p2.price) AS total
                FROM order_entity o2
                LEFT JOIN order_product_entity op2 ON o2.id = op2.order_id
                LEFT JOIN product_entity p2 ON op2.product_id = p2.id
                WHERE o2.create_at BETWEEN ? AND ?
                GROUP BY o2.id
            ) order_total ON order_total.id = o.id
            WHERE o.create_at BETWEEN ? AND ?
        """;

        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{startDate, endDate, startDate, endDate},
                (rs, rowNum) -> new SalesSummaryDTO(
                        rs.getDouble("total_revenue"),
                        rs.getInt("total_orders"),
                        rs.getDouble("avg_order_value"),
                        rs.getInt("completed_orders"),
                        rs.getInt("pending_orders")
                )
        );
    }
}
