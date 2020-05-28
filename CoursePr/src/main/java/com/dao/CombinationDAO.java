package com.dao;

import com.model.Clothes;
import com.model.Combination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CombinationDAO {

    @Autowired
    private ClothesDAO clothesDAO;
    @Autowired
    private PrintDAO printDAO;
    private JdbcTemplate jdbcTemplate;

    public CombinationDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate (Combination c){
        if(c.getId() > 0){
            String sql = "UPDATE Combination SET clothesID=?, printID=?, totalSum=?, combinedImageLink=? WHERE id='"+c.getId()+"';";
            jdbcTemplate.update(sql, c.getClothes().getId(), c.getPrint().getId(), c.getTotalSum(), c.getCombinedImageLink());
        } else {
            String sql = "INSERT INTO Combination (clothesID, printID, totalSum, combinedImageLink) VALUES (?,?,?,?)";
            jdbcTemplate.update(sql, c.getClothes().getId(), c.getPrint().getId(), c.getTotalSum(), c.getCombinedImageLink());
        }
    }


    public Combination getCombinationById (final Integer id){
        String sql = "SELECT * FROM Combination WHERE id=" + id;
        ResultSetExtractor<Combination> extractor = new ResultSetExtractor<Combination>() {
            @Override
            public Combination extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    int id = resultSet.getInt("id");
                    int clothesID = resultSet.getInt("clothesID");
                    int printID = resultSet.getInt("printID");
                    double totalSum = resultSet.getDouble("totalSum");
                    String combinedImageLink = resultSet.getString("combinedImageLink");

                    return new Combination(id, clothesDAO.getClothesById(clothesID), printDAO.getPrintById(printID), totalSum, combinedImageLink);
                }
                return null;
            }
        };
        return jdbcTemplate.query(sql, extractor);
    }

    public int delete (Integer id){
        String sql = "DELETE FROM Combination WHERE id=" + id;
        return jdbcTemplate.update(sql);
    }


    public List<Combination> combinationList (){
        String sql = "SELECT * FROM Combination";

        RowMapper<Combination> rowMapper = new RowMapper<Combination>() {
            @Override
            public Combination mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                int clothesID = resultSet.getInt("clothesID");
                int printID = resultSet.getInt("printID");
                double totalSum = resultSet.getDouble("totalSum");
                String combinedImageLink = resultSet.getString("combinedImageLink");

                return new Combination(id, clothesDAO.getClothesById(clothesID), printDAO.getPrintById(printID), totalSum, combinedImageLink);
            }
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

}
