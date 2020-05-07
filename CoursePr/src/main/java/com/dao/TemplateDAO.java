package com.dao;

import com.model.Image;
import com.model.Template;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TemplateDAO {
    private JdbcTemplate jdbcTemplate;

    public TemplateDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate (Template t){
        if(t.getId() > 0){
            String sql = "UPDATE Template SET location=?, price=? WHERE id='"+t.getId()+"';";
            jdbcTemplate.update(sql, t.getLocation(), t.getPrice(), t.getId());
        } else {
            String sql = "INSERT INTO Template (location, price) VALUES (?,?)";
            jdbcTemplate.update(sql, t.getLocation(), t.getPrice());
        }
    }

    public List<Template> templateList (){
        String sql = "SELECT * FROM Template";

        RowMapper<Template> rowMapper = new RowMapper<Template>() {
            @Override
            public Template mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                String location = resultSet.getString("location");
                double price = resultSet.getDouble("price");

                return new Template(id, location, price);
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public int delete (Integer id){
        String sql = "DELETE FROM Template WHERE id=" + id;
        return jdbcTemplate.update(sql);
    }

    public Template getTemplateById (final Integer id){
        String sql = "SELECT * FROM Template WHERE id=" + id;
        ResultSetExtractor<Template> extractor = new ResultSetExtractor<Template>() {
            @Override
            public Template extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    String location = resultSet.getString("location");
                    double price = resultSet.getDouble("price");
                    return new Template(id, location, price);
                }
                return null;
            }
        };
        return jdbcTemplate.query(sql, extractor);
    }
}
