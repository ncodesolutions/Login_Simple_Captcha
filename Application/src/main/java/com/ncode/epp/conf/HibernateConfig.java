/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.epp.conf;


import java.io.IOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

//    private static final Logger logger = Logger.getLogger(HibernateConfig.class);
//
//    @Autowired(required = true)
//    private ApplicationContext context;
//
//    @Bean
//    public LocalSessionFactoryBean getSessionFactory() throws IOException {
//        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
//        factoryBean.setConfigLocation(context.getResource("classpath:hibernate.cfg.xml"));
//        logger.info("factoryBean" + factoryBean);
//        return factoryBean;
//    }
//
//    @Bean
//    public HibernateTransactionManager getTransactionManager1() throws IOException {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(this.getSessionFactory().getObject());
//        logger.info("transactionManager" + transactionManager);
//        return transactionManager;
//    }

}
