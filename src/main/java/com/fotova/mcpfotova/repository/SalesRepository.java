package com.fotova.mcpfotova.repository;

import com.fotova.mcpfotova.dto.SalesSummaryDTO;
import com.fotova.mcpfotova.dto.SalesDetailDTO;
import com.fotova.mcpfotova.dto.CategorySalesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class SalesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SalesSummaryDTO getSalesSummary(LocalDateTime startDate, LocalDateTime endDate) {

        String sql = """
            WITH order_totals AS (
                SELECT
                    o.id AS order_id,
                    o.is_done,
                    o.create_at,
                    SUM(op.quantity_product * p.price) AS order_total
            FROM order_entity o
                INNER JOIN order_product_entity op ON o.id = op.order_id
                INNER JOIN product_entity p ON op.product_id = p.id
                WHERE o.create_at BETWEEN ? AND ?
                GROUP BY o.id, o.is_done, o.create_at
                )
            SELECT
                COALESCE(SUM(ot.order_total), 0) AS total_revenue,
                COUNT(ot.order_id) AS total_orders,
                COALESCE(AVG(ot.order_total), 0) AS avg_order_value,
                SUM(CASE WHEN ot.is_done = true THEN 1 ELSE 0 END) AS completed_orders,
                SUM(CASE WHEN ot.is_done = false THEN 1 ELSE 0 END) AS pending_orders
            FROM order_totals ot
        """;

        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{startDate, endDate},
                (rs, rowNum) -> new SalesSummaryDTO(
                        rs.getDouble("total_revenue"),
                        rs.getInt("total_orders"),
                        rs.getDouble("avg_order_value"),
                        rs.getInt("completed_orders"),
                        rs.getInt("pending_orders")
                )
        );
    }

    public List<SalesDetailDTO> getSalesDetails(LocalDateTime startDate, LocalDateTime endDate) {
        String sql = """
            SELECT
                o.id AS order_id,
                c.username AS client_username,
                p.name AS product_name,
                op.quantity_product AS quantity,
                p.price AS unit_price,
                (op.quantity_product * p.price) AS total_price,
                o.create_at AS order_date,
                o.is_done AS is_completed,
                cat.name AS category_name
            FROM order_entity o
            INNER JOIN client_entity c ON o.client_id = c.id
            INNER JOIN order_product_entity op ON o.id = op.order_id
            INNER JOIN product_entity p ON op.product_id = p.id
            INNER JOIN category_entity cat ON p.category_id = cat.id
            WHERE o.create_at BETWEEN ? AND ?
            ORDER BY o.create_at DESC, o.id, p.name
        """;

        return jdbcTemplate.query(
                sql,
                new Object[]{startDate, endDate},
                (rs, rowNum) -> new SalesDetailDTO(
                        rs.getInt("order_id"),
                        rs.getString("client_username"),
                        rs.getString("product_name"),
                        rs.getInt("quantity"),
                        rs.getDouble("unit_price"),
                        rs.getDouble("total_price"),
                        rs.getTimestamp("order_date").toLocalDateTime(),
                        rs.getBoolean("is_completed"),
                        rs.getString("category_name")
                )
        );
}

    public List<CategorySalesDTO> getSalesByCategory(LocalDateTime startDate, LocalDateTime endDate) {
        String sql = """
            SELECT
                cat.name AS category_name,
                SUM(op.quantity_product) AS total_units_sold,
                SUM(op.quantity_product * p.price) AS total_revenue,
                COUNT(DISTINCT o.id) AS number_of_orders
            FROM order_entity o
            INNER JOIN order_product_entity op ON o.id = op.order_id
            INNER JOIN product_entity p ON op.product_id = p.id
            INNER JOIN category_entity cat ON p.category_id = cat.id
            WHERE o.create_at BETWEEN ? AND ?
            GROUP BY cat.id, cat.name
            ORDER BY total_revenue DESC
        """;

        return jdbcTemplate.query(
                sql,
                new Object[]{startDate, endDate},
                (rs, rowNum) -> new CategorySalesDTO(
                        rs.getString("category_name"),
                        rs.getInt("total_units_sold"),
                        rs.getDouble("total_revenue"),
                        rs.getInt("number_of_orders")
                )
        );
    }

    public List<SalesDetailDTO> getRecentSales(int limit) {
        String sql = """
            SELECT
                o.id AS order_id,
                c.username AS client_username,
                p.name AS product_name,
                op.quantity_product AS quantity,
                p.price AS unit_price,
                (op.quantity_product * p.price) AS total_price,
                o.create_at AS order_date,
                o.is_done AS is_completed,
                cat.name AS category_name
            FROM order_entity o
            INNER JOIN client_entity c ON o.client_id = c.id
            INNER JOIN order_product_entity op ON o.id = op.order_id
            INNER JOIN product_entity p ON op.product_id = p.id
            INNER JOIN category_entity cat ON p.category_id = cat.id
            ORDER BY o.create_at DESC
            LIMIT ?
        """;

        return jdbcTemplate.query(
                sql,
                new Object[]{limit},
                (rs, rowNum) -> new SalesDetailDTO(
                        rs.getInt("order_id"),
                        rs.getString("client_username"),
                        rs.getString("product_name"),
                        rs.getInt("quantity"),
                        rs.getDouble("unit_price"),
                        rs.getDouble("total_price"),
                        rs.getTimestamp("order_date").toLocalDateTime(),
                        rs.getBoolean("is_completed"),
                        rs.getString("category_name")
                )
        );
    }
}
