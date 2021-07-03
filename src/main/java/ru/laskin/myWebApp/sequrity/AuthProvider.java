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

    private final UserService service;
    private final PasswordEncoder passwordEncoder;

    public AuthProvider(UserService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        final User user = service.getUserByLogin(login);

        if (user == null){
            throw new UsernameNotFoundException("Пользователь не найден");
        }

        String password = authentication.getCredentials().toString();


        if (!passwordEncoder.matches(password, user.getPassword())){
            throw new BadCredentialsException("Пароль не совпадает");
        }

        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add((GrantedAuthority) user::getAdminRole);
        return new UsernamePasswordAuthenticationToken(user, password, authorityList);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
