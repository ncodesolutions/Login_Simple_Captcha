/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.epp.conf;

import java.util.Arrays;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.firewall.StrictHttpFirewall;


@Configuration
@EnableWebSecurity(debug = false)
public class WebSecurityConfig {
//        extends WebSecurityConfigurerAdapter {

    private static final Logger logger = Logger.getLogger(WebSecurityConfig.class);

//    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("in WebSecurityConfig");
        http.headers().httpStrictTransportSecurity().includeSubDomains(true).maxAgeInSeconds(31536000);
        http.authorizeRequests().antMatchers("/**").permitAll();
        http.cors();
        http.csrf();
        http.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));
    }

    @Bean
    public StrictHttpFirewall httpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowSemicolon(true);
        firewall.setAllowedHttpMethods(Arrays.asList("GET", "POST"));
        return firewall;
    }
}
