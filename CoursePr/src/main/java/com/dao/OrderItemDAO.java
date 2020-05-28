package com.dao;

import com.model.Clothes;
import com.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderItemDAO {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ClothesDAO clothesDAO;
    @Autowired
    private PrintDAO printDAO;
    public OrderItemDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate (OrderItem oi){
        if(oi.getId() > 0){
            String sql = "UPDATE OrderItem SET orderID=?, clothesID=?, printID=?, quantity=? WHERE id='"+oi.getId()+"';";
            jdbcTemplate.update(sql, oi.getOrderID(), oi.getClothes().getId(), oi.getPrint().getId(), oi.getQuantity());
        } else {
            String sql = "INSERT INTO OrderItem (orderID, clothesID, printID, quantity) VALUES (?,?,?,?)";
            System.out.println(oi.getOrderID());
            System.out.println(oi.getClothes().getId());
            System.out.println(oi.getPrint().getId());
            System.out.println(oi.getQuantity());
            jdbcTemplate.update(sql, oi.getOrderID(), oi.getClothes().getId(), oi.getPrint().getId(), oi.getQuantity());
        }
    }

    public List<OrderItem> getContentByOrderID (final Integer id){
        String sql = "SELECT * FROM OrderItem WHERE orderID=" + id;
        RowMapper<OrderItem> rowMapper = new RowMapper<OrderItem>() {
            @Override
            public OrderItem mapRow(ResultSet resultSet, int i) throws SQLException {
                int orderID = resultSet.getInt("orderID");
                int clothesID = resultSet.getInt("clothesID");
                int printID = resultSet.getInt("printID");
                int quantity = resultSet.getInt("quantity");
                return new OrderItem(id, orderID, clothesDAO.getClothesById(clothesID), printDAO.getPrintById(printID), quantity);
            }
        };
        return jdbcTemplate.query(sql, rowMapper);

    }

}
