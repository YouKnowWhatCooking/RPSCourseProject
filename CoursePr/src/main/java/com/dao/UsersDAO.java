package com.dao;

import com.model.Clothes;
import com.model.Users;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsersDAO {
    private JdbcTemplate jdbcTemplate;

    public UsersDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate (Users u){
        if(u.getId() > 0){
            String sql = "UPDATE Users SET login=?, password=?, role=?, firstName=?, lastName=?, balance=?, email=?, address=? WHERE id='"+u.getId()+"';";
            jdbcTemplate.update(sql, u.getLogin(), u.getPassword(), u.getRole(), u.getFirstName(), u.getLastName(), u.getBalance(), u.getEmail(), u.getAddress());
        } else {
            String sql = "INSERT INTO Users (login, password, role, firstName, lastName, balance, email, address) VALUES (?,?,?,?,?,?,?,?)";
            jdbcTemplate.update(sql, u.getLogin(), u.getPassword(), u.getRole(), u.getFirstName(), u.getLastName(), 0.00, u.getEmail(), u.getAddress());
        }
    }


    public Users findByUserName(final String login){
        String sql = "SELECT * FROM Users WHERE login='" + login + "';";
        ResultSetExtractor<Users> extractor = new ResultSetExtractor<Users>() {
            @Override
            public Users extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    int id = resultSet.getInt("id");
                    String password = resultSet.getString("password");
                    String role = resultSet.getString("role");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    String email = resultSet.getString("email");
                    String address = resultSet.getString("address");
                    double balance = resultSet.getDouble("balance");
                    return new Users(id, login, password, role, firstName, lastName, email, address, balance);
                }
                return null;
            }
        };
        return jdbcTemplate.query(sql, extractor);
    }

    public void increaseBalance(Users user, double newBalance) {
        jdbcTemplate.execute("UPDATE Users SET balance='" + newBalance + "'WHERE id ='" + user.getId() + "';");
    }

    public List<Users> usersList (){
        String sql = "SELECT * FROM Users";

        RowMapper<Users> rowMapper = new RowMapper<Users>() {
            @Override
            public Users mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                double balance = resultSet.getDouble("balance");
                return new Users(id, login, password, role, firstName, lastName, email, address, balance);
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Users> getUsersByRole(final String role){
        String sql = "SELECT * FROM Users WHERE role='"+role+"';";

        RowMapper<Users> rowMapper = new RowMapper<Users>() {
            @Override
            public Users mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                double balance = resultSet.getDouble("balance");
                return new Users(id, login, password, role, firstName, lastName, email, address, balance);
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public int delete (Integer id){
        String sql = "DELETE FROM Users WHERE id=" + id;
        return jdbcTemplate.update(sql);
    }


    public Users getUserById (final Integer id){
        String sql = "SELECT * FROM Users WHERE id=" + id;
        ResultSetExtractor<Users> extractor = new ResultSetExtractor<Users>() {
            @Override
            public Users extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    String login = resultSet.getString("login");
                    String password = resultSet.getString("password");
                    String role = resultSet.getString("role");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    String email = resultSet.getString("email");
                    String address = resultSet.getString("address");
                    double balance = resultSet.getDouble("balance");
                    return new Users(id, login, password, role, firstName, lastName, email, address, balance);
                }
                return null;
            }
        };
        return jdbcTemplate.query(sql, extractor);
    }

    public List<String> getUsersRoles(){
        String sql = "SELECT DISTINCT Role FROM Users";

        RowMapper<String> rowMapper = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                String role = resultSet.getString("role");
                return role;
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }
}