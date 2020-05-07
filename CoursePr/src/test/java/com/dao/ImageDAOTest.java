package com.dao;

import com.model.Clothes;
import com.model.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ImageDAOTest {
    private DriverManagerDataSource dataSource;
    private ImageDAO imageDAO;

    @BeforeEach
    void setupBeforeEach() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/course?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        imageDAO = new ImageDAO(dataSource);
    }

    @Test
    void getImageById() {
        boolean bool = false;
        Integer id = 1;
        Image image = imageDAO.getImageById(id);
        if (image != null) {
            bool = true;
        }
        assertTrue(bool == true);
    }

    @Test
    void delete() {
        Integer id = 1;
        int result = imageDAO.delete(id);

        assertTrue(result > 0);
    }

    @Test
    void imageList() {
        List<Image> listImages = imageDAO.imageList();

        assertTrue(!listImages.isEmpty());
    }
}