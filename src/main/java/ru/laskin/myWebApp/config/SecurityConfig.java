package ru.laskin.myWebApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.laskin.myWebApp.model.User;
import ru.laskin.myWebApp.sequrity.AuthProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthProvider authProvider;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                // включаем защиту от CSRF атак
                csrf().disable()
                // указываем правила запросов
                // по которым будет определятся доступ к ресурсам и остальным данным
                .authorizeRequests()
                .antMatchers("/login", "/registration").anonymous()
                .antMatchers("/allUsers", "/greeting").authenticated()
                //редиректим залогированного пользователя на нужную страницу
                // при его попытке попасть на страницу, к которой доступ запрещен
                .and().exceptionHandling().accessDeniedPage("/greeting")
                .and();

        http.formLogin()
                // указываем страницу с формой логина
                .loginPage("/login")
                // указываем action с формы логина
                .loginProcessingUrl("/login/process")
                // указываем URL при неудачном логине
                .failureUrl("/login?error=true")
                //указываем URL при удачном логине
                .defaultSuccessUrl("/allUsers")
                // Указываем параметры логина и пароля с формы логина
                .usernameParameter("login")
                .passwordParameter("password");
                // даем доступ к форме логина всем
//                .permitAll();

        http.logout()
                // разрешаем делать логаут всем
                .permitAll()
                // указываем URL логаута
                .logoutUrl("/logout")
                // указываем URL при удачном логауте
                .logoutSuccessUrl("/login?logout")
                // делаем не валидной текущую сессию
                .invalidateHttpSession(true);
    }
}
