package com.dao;

import com.model.Clothes;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClothesDAOTest {
    private DriverManagerDataSource dataSource;
    private ClothesDAO clothesDAO;

    @BeforeEach
    void setupBeforeEach(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/course?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        clothesDAO = new ClothesDAO(dataSource);
    }


    @org.junit.jupiter.api.Test
    void getClothesById() {
        Integer id = 4;
        Clothes clothes = clothesDAO.getClothesById(id);
        if (clothes != null){
            assertNotNull(clothes);
        }
    }

    @org.junit.jupiter.api.Test
    void delete() {
        Integer id = 3;
        int result = clothesDAO.delete(id);

        assertTrue(result > 0);
    }

    @org.junit.jupiter.api.Test
    void clothesList() {
        List<Clothes> listClothes = clothesDAO.clothesList();

        assertTrue(!listClothes.isEmpty());
    }
}