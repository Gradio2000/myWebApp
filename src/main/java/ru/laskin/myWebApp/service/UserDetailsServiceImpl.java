package ru.laskin.myWebApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.laskin.myWebApp.model.User;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService service;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = service.getUserBylogin(login);
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority("USER"));
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), roles);
        return userDetails;
    }
}
