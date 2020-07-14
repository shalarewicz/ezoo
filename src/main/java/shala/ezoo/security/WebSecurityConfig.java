package shala.ezoo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private DataSource ds;
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .authorizeRequests()
                .antMatchers("/", "/register").permitAll() //TODO Why isn't home page accessible without login
                .antMatchers("/resources/**").permitAll() // Allows access to CSS resources before login
                .antMatchers("/animal/**", "/feedingSchedule/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/account/**").hasRole("USER")
                .anyRequest().authenticated() // Deny-by-default
                .and()
            .logout()
                .logoutSuccessUrl("/");
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user")
                    .password("password")
                    .authorities("ROLE_USER")
                    .and()
               .withUser("admin")
                   .password("password")
                   .authorities("ROLE_USER", "ROLE_ADMIN")
                   .and()
               .and()
            .jdbcAuthentication()
                .dataSource(ds)
                .passwordEncoder(new BCryptPasswordEncoder());
                //TODO Custom queries if necessary
    }
    
    
    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}
