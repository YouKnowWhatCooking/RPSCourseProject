package com.dao;

import com.model.Image;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ImageDAO {

    private JdbcTemplate jdbcTemplate;

    public ImageDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate (Image i){
        if(i.getId() > 0){
            String sql = "UPDATE Image SET title=?, theme=?, link=?, author=?, status=? WHERE id='"+i.getId()+"';";
            jdbcTemplate.update(sql, i.getTitle(), i.getTheme(), i.getLink(), i.getAuthor(), i.getStatus());
        } else {
            String sql = "INSERT INTO Image (title, theme, link, author, status) VALUES (?,?,?,?,?)";
            jdbcTemplate.update(sql, i.getTitle(), i.getTheme(), i.getLink(), i.getAuthor(), i.getStatus());
        }
    }

    public Image getImageById (final Integer id){
        String sql = "SELECT * FROM Image WHERE id=" + id;
        ResultSetExtractor<Image> extractor = new ResultSetExtractor<Image>() {
            @Override
            public Image extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    String title = resultSet.getString("title");
                    String theme = resultSet.getString("theme");
                    String link = resultSet.getString("link");
                    String author = resultSet.getString("author");
                    String status = resultSet.getString("status");
                    return new Image(id, title, theme, link, author, status);
                }
                return null;
            }
        };
        return jdbcTemplate.query(sql, extractor);
    }

    public int delete (Integer id){
        String sql = "DELETE FROM Image WHERE id=" + id;
        return jdbcTemplate.update(sql);
    }


    public List<String> getImageThemes(){
        String sql = "SELECT DISTINCT Theme FROM Image";

        RowMapper<String> rowMapper = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                String theme = resultSet.getString("theme");
                return theme;
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Image> imageList (){
        String sql = "SELECT * FROM Image";

        RowMapper<Image> rowMapper = new RowMapper<Image>() {
            @Override
            public Image mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String theme = resultSet.getString("theme");
                String link = resultSet.getString("link");
                String author = resultSet.getString("author");
                String status = resultSet.getString("status");
                return new Image(id, title, theme, link, author, status);
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Image> getImageByTheme(String theme){
        String sql = "SELECT * FROM Image WHERE theme='"+theme+"';";

        RowMapper<Image> rowMapper = new RowMapper<Image>() {
            @Override
            public Image mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String theme = resultSet.getString("theme");
                String link = resultSet.getString("link");
                String author = resultSet.getString("author");
                String status = resultSet.getString("status");
                return new Image(id, title, theme, link, author, status);

            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }
}
