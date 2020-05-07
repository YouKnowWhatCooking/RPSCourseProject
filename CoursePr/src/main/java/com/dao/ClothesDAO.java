package com.dao;

import com.model.Clothes;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClothesDAO {
    private JdbcTemplate jdbcTemplate;

    public ClothesDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate (Clothes c){
        if(c.getId() > 0){
            String sql = "UPDATE Clothes SET title=?, type=?, imageLink=?, price=? WHERE id='"+c.getId()+"';";
            jdbcTemplate.update(sql, c.getTitle(), c.getType(), c.getImageLink(), c.getPrice());
        } else {
            String sql = "INSERT INTO Clothes (title, type, imageLink, price) VALUES (?,?,?,?)";
            jdbcTemplate.update(sql, c.getTitle(), c.getType(), c.getImageLink(), c.getPrice());
        }
    }

    public Clothes getClothesById (final Integer id){
        String sql = "SELECT * FROM Clothes WHERE id=" + id;
        ResultSetExtractor<Clothes> extractor = new ResultSetExtractor<Clothes>() {
            @Override
            public Clothes extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    String title = resultSet.getString("title");
                    String type = resultSet.getString("type");
                    String imageLink = resultSet.getString("imageLink");
                    double price = resultSet.getDouble("price");
                    return new Clothes(id, title, type, imageLink, price);
                }
                return null;
            }
        };
        return jdbcTemplate.query(sql, extractor);
    }

    public List<String> getClothesTypes(){
        String sql = "SELECT DISTINCT Type FROM Clothes";

        RowMapper<String> rowMapper = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                String type = resultSet.getString("type");
                return type;
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public int delete (Integer id){
        String sql = "DELETE FROM Clothes WHERE id=" + id;
        return jdbcTemplate.update(sql);
    }


    public List<Clothes> clothesList (){
        String sql = "SELECT * FROM Clothes";

        RowMapper<Clothes> rowMapper = new RowMapper<Clothes>() {
            @Override
            public Clothes mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String type = resultSet.getString("type");
                String imageLink = resultSet.getString("imageLink");
                double price = resultSet.getDouble("price");

                return new Clothes(id, title, type, imageLink, price);
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Clothes> getClothesByType(String type){
        String sql = "SELECT * FROM Clothes WHERE type='"+type+"';";

        RowMapper<Clothes> rowMapper = new RowMapper<Clothes>() {
            @Override
            public Clothes mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String type = resultSet.getString("type");
                String imageLink = resultSet.getString("imageLink");
                double price = resultSet.getDouble("price");

                return new Clothes(id, title, type, imageLink, price);

            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }
}

