/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.epp.controller;

import java.security.SecureRandom;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import nl.captcha.Captcha;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;


@Repository
public class CommonClass {

    private static final Logger logger = Logger.getLogger(CommonClass.class);
    private static final Random RANDOM = new SecureRandom();

    @Autowired
    public HttpServletRequest request;


    public JSONParser parser = new JSONParser();

    public boolean validateCaptcha(HttpServletRequest request, String answer) {
        boolean checkCaptcha = false;
        try {
            // for captcha validation issue please check domain name set inthe AppInitializer 
            Captcha captcha = (Captcha) request.getSession().getAttribute(Captcha.NAME);
            request.setCharacterEncoding("UTF-8"); // Do this so we can capture non-Latin chars

            if (captcha.isCorrect(answer)) {
                checkCaptcha = true;
            }

        } catch (Exception e) {
            logger.info("exception", e);
        }
        return checkCaptcha;
    }

    public String getOTP() {
        logger.info("getOTP");
        String ramdomOTP = "";
        String numberLetters = "1234567890";
        for (int i = 0; i < 6; i++) {
            int index = (int) (RANDOM.nextDouble() * numberLetters.length());
            ramdomOTP += numberLetters.substring(index, index + 1);
        }
        List<String> lettersAll = Arrays.asList(ramdomOTP.split(""));
        Collections.shuffle(lettersAll);
        String shuffled = "";
        for (String letter : lettersAll) {
            shuffled += letter;
        }
        ramdomOTP = shuffled;
        logger.info("OTP  " + ramdomOTP);
        return ramdomOTP;
    }

    public String getRandomNumber() {
        logger.info("getRandomNumber");

        String ramdomNumber = "";
        String numberLetters = "1234567890";
        for (int i = 0; i < 2; i++) {
            int index = (int) (RANDOM.nextDouble() * numberLetters.length());
            ramdomNumber += numberLetters.substring(index, index + 1);
        }
        List<String> lettersAll = Arrays.asList(ramdomNumber.split(""));
        Collections.shuffle(lettersAll);
        String shuffled = "";
        for (String letter : lettersAll) {
            shuffled += letter;
        }
        ramdomNumber = shuffled;
        logger.info("RamdomNumber  " + ramdomNumber);
        return ramdomNumber;
    }

    public static String getRoundAmountStr(Double amount, int fraPoint) throws ParseException {
        String amountStr = null;
        amount = amount == null ? 0 : amount;
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(fraPoint);
        nf.setMinimumFractionDigits(fraPoint);
        if (amount != null) {
            amountStr = nf.format(amount.doubleValue());
        }
        return amountStr;
    }

}
