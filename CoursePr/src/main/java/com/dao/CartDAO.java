package com.dao;

import com.model.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CartDAO {
    private JdbcTemplate jdbcTemplate;
    public CartDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }



}
