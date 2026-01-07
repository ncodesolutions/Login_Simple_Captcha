/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.epp.conf;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;


public class ApplicationSecurityInterceptor implements HandlerInterceptor {

    private static final Logger logger = Logger.getLogger(ApplicationSecurityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws IOException {
        try {

            return true; // URL based access need to code
        } catch (Exception ex) {
            ex.printStackTrace();
            response.reset();
            response.sendError(401, "Unauthorized request");
            return false;
        }
    }

}
