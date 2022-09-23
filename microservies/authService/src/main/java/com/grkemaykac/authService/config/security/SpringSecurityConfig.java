package com.grkemaykac.authService.config.security;

import com.grkemaykac.authService.auth.service.userService;
import com.grkemaykac.authService.config.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtFilter jwtFilter;
    @Autowired
    private userService UserService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(UserService);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/auth/actuator/health").permitAll();//Auth. gerekmeden erişilebilen url'ler.AdminServer'için actuator serbest bırakıldı.
        http.authorizeRequests().antMatchers("/auth/register").permitAll();//Auth. gerekmeden erişilebilen url'ler.
        http.authorizeRequests().antMatchers("/auth/login").permitAll();//Auth. gerekmeden erişilebilen url'ler.
        http.authorizeRequests().antMatchers("/auth/activeUser").permitAll();//Auth. gerekmeden erişilebilen url'ler.
        //http.authorizeRequests().antMatchers("/authWithRole/**").access("hasRole('ROLE_admin')"); //admin rolune sahip birinin bu url'e erişebileceğini belirttik.
        //http.authorizeRequests().antMatchers("/car/**").access("hasRole('ROLE_client')"); //admin rolune sahip birinin bu url'e erişebileceğini belirttik.        http.authorizeRequests().anyRequest().authenticated();//Tüm url'ler için authenticationa ihtiyaç duyulmasını sağlar.
        http.authorizeRequests().anyRequest().authenticated();
        http.sessionManagement();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //Session oluşturmayı ortadan kaldırıyoruz. Bunu JWT ile sağlayacağız.
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
