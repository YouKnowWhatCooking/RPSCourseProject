package com.dao;

import com.model.Combination;
import com.model.Print;
import com.model.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PrintDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TemplateDAO templateDAO;
    @Autowired
    private ImageDAO imageDAO;

    public PrintDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate (Print p){
        if(p.getId() > 0){
            String sql = "UPDATE Print SET templateID=?, imageID=? WHERE id='"+p.getId()+"';";
            jdbcTemplate.update(sql, p.getTemplate().getId(), p.getImage().getId());
        } else {
            String sql = "INSERT INTO Print (templateID, imageID) VALUES (?,?)";
            jdbcTemplate.update(sql, p.getTemplate().getId(), p.getImage().getId());
        }
    }
    public int delete (Integer id){
        String sql = "DELETE FROM print WHERE id=" + id;
        return jdbcTemplate.update(sql);
    }


    public List<Print> printList (){
        String sql = "SELECT * FROM Print";

        RowMapper<Print> rowMapper = new RowMapper<Print>() {
            @Override
            public Print mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                int templateID = resultSet.getInt("templateID");
                int imageID = resultSet.getInt("imageID");

                return new Print(id, templateDAO.getTemplateById(templateID), imageDAO.getImageById(imageID));
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Print getPrintById (final Integer id){
        String sql = "SELECT * FROM Print WHERE id=" + id;
        ResultSetExtractor<Print> extractor = new ResultSetExtractor<Print>() {
            @Override
            public Print extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    int templateId = resultSet.getInt("templateId");
                    int imageId = resultSet.getInt("imageId");
                    return new Print(id, templateDAO.getTemplateById(templateId), imageDAO.getImageById(imageId));
                }
                return null;
            }
        };
        return jdbcTemplate.query(sql, extractor);
    }

    public int getLastID (){
        int result = jdbcTemplate.queryForObject("SELECT id FROM Print ORDER BY id DESC LIMIT 1", Integer.class);
        return result;
    }
}
