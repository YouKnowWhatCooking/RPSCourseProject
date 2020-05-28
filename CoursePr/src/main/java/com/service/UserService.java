package com.service;

import com.dao.UsersDAO;
import com.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserService {

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersDAO.saveOrUpdate(user);
    }
}
