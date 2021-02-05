package ee.bcs.valiit.tasks.service;

import ee.bcs.valiit.tasks.solution.JwtTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityCofiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //.antMatchers("/", "/home")
                //.permitAll()
                //.anyRequest().permitAll()
                .and()
                .authorizeRequests()
                //.anyRequest().permitAll()
                //.antMatchers("/api/**").authenticated()
                .antMatchers("/public/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
                //.defaultSuccessUrl("/index.html", true) //ei tööta hetkel
                //.successForwardUrl("/index.html") //suunab lehele pärast edukat sisselogimist
        http.csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
