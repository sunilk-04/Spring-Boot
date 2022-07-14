package com.prominentpixel.springbootsecurity.config;

import com.prominentpixel.springbootsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    @Autowired
    UserAuthenticationSuccessHandler userAuthenticationSuccessHandler;

    @Autowired
    private UserService userService;

    @Autowired
    UserLogoutHandler userLogoutHandler;

    @Autowired
    UserLogoutSuccessHandler userLogoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/employees/show*").hasAnyRole("MANAGER", "ADMIN")
                .antMatchers("/employees/save*").hasAnyRole("MANAGER", "ADMIN")
                .antMatchers("/employees/delete").hasRole("ADMIN")
                .antMatchers("/employees/**", "/loggedUsers", "/history").hasRole("EMPLOYEE")
                .antMatchers("/resources/**").permitAll()
                .and().formLogin().loginPage("/login").loginProcessingUrl("/authenticate")
                .successHandler(this.userAuthenticationSuccessHandler).permitAll()
                .and()
                .logout()
                .addLogoutHandler(this.userLogoutHandler)
                .logoutSuccessHandler(this.userLogoutSuccessHandler)
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(this.userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

}
