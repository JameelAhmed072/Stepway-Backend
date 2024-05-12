package com.example.Stepway.security;

import com.example.Stepway.Service.impl.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class SecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailService myUserDetailsService;
    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Override   ///   ====>>>      authentication
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf()
//                .disable()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.POST , "/api/login").permitAll()
////                .antMatchers(HttpMethod.POST , "/api/students").permitAll()
//                .antMatchers("/v3/api-docs").permitAll()
//                .antMatchers("/v2/api-docs").permitAll()
//                .antMatchers("/swagger-resources/**").permitAll()
//                .antMatchers("/swagger-ui/**").permitAll()
//                .antMatchers("/webjars/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//    }
@Override         ///   ====>>>      authorization
protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
            .authorizeRequests()
            // Permit access to login endpoint
            .antMatchers(HttpMethod.POST, "/api/login").permitAll()
            .antMatchers(HttpMethod.POST, "/api/user").permitAll()
            // Permit access to public API documentation and Swagger UI
            .antMatchers(
                    "/v3/api-docs",
                    "/v2/api-docs",
                    "/swagger-resources/**",
                    "/swagger-ui/**",
                    "/webjars/**"
            ).permitAll()
            // Role-based access control for specific endpoints
            .antMatchers("/api/student/**").hasRole("STUDENT")
            .antMatchers("/api/teacher/**").hasRole("TEACHER")
            .antMatchers("/api/admin/**").hasRole("ADMIN")
            // All other requests need to be authenticated
            .anyRequest().authenticated()
            .and()
            // Configure session management
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    // Add JWT filter
    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
}
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
