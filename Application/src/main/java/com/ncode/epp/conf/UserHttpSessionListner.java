/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.epp.conf;

import java.util.Enumeration;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.apache.log4j.Logger;


public class UserHttpSessionListner implements HttpSessionListener {

    private static final Logger logger = Logger.getLogger(UserHttpSessionListner.class);

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        event.getSession().setMaxInactiveInterval(30 * 60);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        try {
            HttpSession session = event.getSession(); //sessionCreated() is executed
            Enumeration enumeration = session.getAttributeNames();
            while (enumeration.hasMoreElements()) {
                String attributeName = (String) enumeration.nextElement();
                session.removeAttribute(attributeName);
            }
            session.invalidate();
        } catch (Exception ex) {
            logger.info("Error removing all attribute from sesion " + ex);
        }
    }
}
