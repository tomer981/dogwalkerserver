//package com.mta.dogwalkerserver.controller;
////
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import javax.sql.DataSource;
//
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
////    @Autowired
////    UserDetailsService userDetailsService;
////
////
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userDetailsService);
////
////    }
//
//    @Autowired
//    DataSource dataSource;
//
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select userName,password "
//                        + "from users "
//                        + "where userName = ?")
//                .authoritiesByUsernameQuery("select userName,authority " //
//                        + "from authorities "
//                        + "where userName = ?");
//    }
//
//
//
//        @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/dogwalker").hasRole("DOGWALKER")
//                .antMatchers("/dogowner").hasAnyRole("DOGOWNER")
//                .antMatchers("/").permitAll()
//                .and().formLogin();
//
//    }
//
//    @Bean
//    public PasswordEncoder getPasswordEncoder() { return NoOpPasswordEncoder.getInstance(); }
//}
//
//
//
////import org.springframework.context.annotation.Bean;
////import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
////import org.springframework.security.crypto.password.NoOpPasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
////
////
////@EnableWebSecurity
////public class SecurityConfigutaion extends WebSecurityConfigurerAdapter {
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        //set your configuration on the auth object
////        auth.inMemoryAuthentication()
////                .withUser("blah")
////                .password("blah")
////                .roles("USER")
////                .and()
////                .withUser("foo")
////                .password("foo")
////                .roles("ADMIN");
////
////    }
////
////    @Bean
////    public PasswordEncoder getPasswordEncoder() {
////        return NoOpPasswordEncoder.getInstance();
////    }
////
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests()
////                .antMatchers("/admin").hasRole("ADMIN")
////                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
////                .antMatchers("/").permitAll()
////                .and().formLogin();
////
////            //    .antMatchers("/", "static/css", "static/js").permitAll()
////            //    .antMatchers("/**").hasRole("ADMIN")
////            //    .and().formLogin();
////    }
////}
////
