/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.epp.controller;

import com.ncode.epp.common.AesUtil;
import com.ncode.epp.common.Base64Service;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.renderer.DefaultWordRenderer;


@Controller
public class Indexcontroller extends CommonClass {

    private static final Logger logger = Logger.getLogger(Indexcontroller.class);
    private static int _width = 150;
    private static int _height = 50;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView ApplicatonDefault() {
        try {
            logger.info(" Application default");
            ModelAndView mav = new ModelAndView();
            mav.setViewName("login");
            mav.addObject("Error", "");
            return mav;
        } catch (Exception ex) {
            logger.info("exception", ex);
            ModelAndView mav = new ModelAndView();
            mav.setViewName("login");
            return mav;
        }
    }

    @RequestMapping(value = "login.do", method = {RequestMethod.GET, RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView login() {
        try {
            logger.info(" Application default");
            ModelAndView mav = new ModelAndView();
            mav.setViewName("login");
            mav.addObject("Error", "");
            return mav;
        } catch (Exception ex) {
            logger.info("exception", ex);
            ModelAndView mav = new ModelAndView();
            mav.setViewName("login");
            return mav;
        }
    }

    @RequestMapping(value = "getCaptcha.do", method = RequestMethod.GET)
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(Captcha.NAME);
        List<Color> colors = new ArrayList<Color>();
        colors.add(Color.black);
        colors.add(Color.red);

        List<Font> fonts = new ArrayList<Font>();
        fonts.add(new Font("Arial", Font.ITALIC, 40));

        nl.captcha.Captcha captcha = new nl.captcha.Captcha.Builder(_width, _height)
                .addText(new DefaultWordRenderer(colors, fonts))
                .addBackground(new GradiatedBackgroundProducer(Color.white, Color.white))
                .gimp()
                //                .addBorder()
                .build();
        CaptchaServletUtil.writeImage(response, captcha.getImage());
        request.getSession().setAttribute("simpleCaptcha", captcha);
    }

    @RequestMapping(value = "LoginSubmitdata.do", method = RequestMethod.POST)
    public ModelAndView LoginSubmitdata(HttpServletRequest request) {
        try {
            AesUtil aesUtil = null;
            String salt = "";
            String iv = "";
            String key = "";

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String captcha = request.getParameter("captcha");

            if (username.trim().equalsIgnoreCase("")) {
                ModelAndView mav = new ModelAndView();
                mav.setViewName("login");
                mav.addObject("Error", "please enter valid username");
                return mav;
            }

            if (password.trim().equalsIgnoreCase("")) {
                ModelAndView mav = new ModelAndView();
                mav.setViewName("login");
                mav.addObject("Error", "please enter valid password");
                return mav;
            }

            if (captcha.trim().equalsIgnoreCase("")) {
                ModelAndView mav = new ModelAndView();
                mav.setViewName("login");
                mav.addObject("Error", "please enter valid captcha");
                return mav;
            }

            int iterationCount = Integer.parseInt("1000");
            int keySize = Integer.parseInt("128");
            salt = request.getParameter("salt").toString();
            iv = request.getParameter("iv").toString();
            key = new String(Base64Service.decode(request.getParameter("key").toString()));
            aesUtil = new AesUtil(keySize, iterationCount);

            if (username != null) {
                if (!username.equalsIgnoreCase("")) {
                    username = aesUtil.decrypt(salt, iv, key, username.trim());
                }
            }
            if (password != null) {
                if (!password.equalsIgnoreCase("")) {
                    password = aesUtil.decrypt(salt, iv, key, password.trim());
                }
            }
            if (captcha != null) {
                if (!captcha.equalsIgnoreCase("")) {
                    captcha = aesUtil.decrypt(salt, iv, key, captcha.trim());
                }
            }
            boolean checkCaptcha = validateCaptcha(request, captcha);

            if (checkCaptcha) {
                ModelAndView mav = new ModelAndView();
                mav.setViewName("HomePage");
                mav.addObject("Error", "Login success");
                return mav;
            } else {
                ModelAndView mav = new ModelAndView();
                mav.setViewName("login");
                mav.addObject("Error", "Invalid Credentials");
                return mav;
            }

        } catch (Exception ex) {
            logger.info("exception", ex);
            ModelAndView mav = new ModelAndView();
            mav.setViewName("login");
            mav.addObject("Error", "Technical error try after some time");
            return mav;
        }

    }

}
