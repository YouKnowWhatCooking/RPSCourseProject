package com.dao;

import com.model.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class OrderDAO {
    private JdbcTemplate jdbcTemplate;

    public OrderDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate (Order o){
        if(o.getId() > 0){
            String sql = "UPDATE Orders SET date=?, clientID=?, status=?, cost=?, email=?, address=? WHERE id='"+o.getId()+"';";
            jdbcTemplate.update(sql, o.getDate(), o.getClient(), o.getStatus(), o.getCost(), o.getEmail(), o.getAddress());
        } else {
            String sql = "INSERT INTO Orders (date, clientID, status, cost, email, address) VALUES (?,?,?,?,?,?)";
            jdbcTemplate.update(sql, o.getDate(), o.getClient(), o.getStatus(), o.getCost(), o.getEmail(), o.getAddress());
        }
    }

    public Order getOrderById (final Integer id){
        String sql = "SELECT * FROM Orders WHERE id=" + id;
        ResultSetExtractor<Order> extractor = new ResultSetExtractor<Order>() {
            @Override
            public Order extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    String date = resultSet.getString("date");
                    int client = resultSet.getInt("clientID");
                    String status = resultSet.getString("status");
                    double cost = resultSet.getDouble("cost");
                    String email = resultSet.getString("email");
                    String address = resultSet.getString("address");
                    return new Order(id, date, client, status, email, address, cost);
                }
                return null;
            }
        };
        return jdbcTemplate.query(sql, extractor);
    }


    public List<Order> ordersOfCurrentUser (final Integer clientId){
        String sql = "SELECT * FROM Orders WHERE clientID =" + clientId;

        RowMapper<Order> rowMapper = new RowMapper<Order>() {
            @Override
            public Order mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                String date = resultSet.getString("date");
                String status = resultSet.getString("status");
                double cost = resultSet.getDouble("cost");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                return new Order(id, date, clientId, status, email, address, cost);

            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }


    public int delete (Integer id){
        String sql = "DELETE FROM Orders WHERE id=" + id;
        return jdbcTemplate.update(sql);
    }

    public int getLastID (){
        int result = jdbcTemplate.queryForObject("SELECT id FROM Orders ORDER BY id DESC LIMIT 1", Integer.class);
        return result;
    }


    public List<Order> orderList (){
        String sql = "SELECT * FROM Orders";

        RowMapper<Order> rowMapper = new RowMapper<Order>() {
            @Override
            public Order mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                String date = resultSet.getString("date");
                int clientId = resultSet.getInt("clientID");
                String status = resultSet.getString("status");
                double cost = resultSet.getDouble("cost");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");

                return new Order(id, date, clientId, status, email, address, cost);

            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }
}
