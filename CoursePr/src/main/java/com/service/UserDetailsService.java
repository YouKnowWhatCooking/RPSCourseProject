package com.service;

import com.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import com.dao.UsersDAO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsService  implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UsersDAO usersDAO;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Users users = usersDAO.findByUserName(login);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(users.getRole()));

        return new org.springframework.security.core.userdetails.User(users.getLogin(), users.getPassword(), grantedAuthorities);
    }
}
