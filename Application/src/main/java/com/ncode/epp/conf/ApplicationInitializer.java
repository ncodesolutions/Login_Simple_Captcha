/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.epp.conf;

import java.io.File;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.Properties;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.SessionTrackingMode;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

//    private int maxUploadSizeInMb = 50 * 1024 * 1024; // 50 MB approx
    private int maxUploadSizeInMb = 2 * 1024 * 1024; // 1 MB approx

    private static final Logger logger = Logger.getLogger(ApplicationInitializer.class);

    @Override
    protected Class<?>[] getRootConfigClasses() {
        logger.info("getRootConfigClasses");
        return new Class[]{};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        logger.info("WebMvcConfiguration.class");
        return new Class[]{WebMvcConfiguration.class, WebSecurityConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"", "/"};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        try {
            logger.info("onStartup START");
            InputStream propinputStream = this.getClass().getResourceAsStream("/AppConfig.properties");
            Properties prop = new Properties();
            prop.load(propinputStream);
            String domainName = prop.getProperty("domainName").trim();
            super.onStartup(servletContext);
            servletContext.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));
            servletContext.getSessionCookieConfig().setHttpOnly(true);
            boolean isSecure = Boolean.parseBoolean(prop.getProperty("isSecure").trim());
            servletContext.getSessionCookieConfig().setSecure(isSecure); // allowing on HTTPS:// only access
            servletContext.getSessionCookieConfig().setDomain(domainName); // set your live application name
//            servletContext.getSessionCookieConfig().setPath(domainName+"/"); // removed as per dinesh email on 14-05-2022
            servletContext.getSessionCookieConfig().setPath(domainName + "/EPP"); // removed as per dinesh email on 14-05-2022
            servletContext.addListener(new UserHttpSessionListner());
            propinputStream.close();
            logger.info("onStartup END");
        } catch (Exception ex) {
            logger.info("Exception ", ex);
        }
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {

        // upload temp file will put here
        File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));

        // register a MultipartConfigElement
        MultipartConfigElement multipartConfigElement
                = new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
                        maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);

        registration.setMultipartConfig(multipartConfigElement);

    }

}
