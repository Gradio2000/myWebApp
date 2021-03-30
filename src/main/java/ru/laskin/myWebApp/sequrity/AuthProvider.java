package ru.laskin.myWebApp.sequrity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private UserService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        final User user = service.getUserBylogin(login);
        if (user == null){
            throw new UsernameNotFoundException("Пользователь не найден");
        }

        String password = authentication.getCredentials().toString();

        if (!passwordEncoder.matches(password, user.getPassword())){
            throw new BadCredentialsException("Пароль не совпадает");
        }

        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getAdminRole();
            }
        });
        return new UsernamePasswordAuthenticationToken(user, null, authorityList);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}