/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ncode.epp.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

public class DataEncDec {

    private static final Logger logger = Logger.getLogger(DataEncDec.class);

    public static String securityKeyReq = "";
    public static String securityKeyRes = "";
    public static String securityKeyDb = "";

//UMANG START
    public static String securityKeyDb1 = "Vision4@Gnfc"; // used for customer email verification, email verification link send over email
//UMANG END

    private static SecretKeySpec secretKey;
    private static byte[] key;

    public static void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-256");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            logger.info("exception", e);
        } catch (UnsupportedEncodingException e) {
            logger.info("exception", e);
        }
    }

    public static String encrypt(String strToEncrypt, String securityKey) {
        try {
            setKey(securityKey);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            logger.info("Exception", e);
            return null;
        }

    }

    public static String decrypt(String strToDecrypt, String securityKey) {
        try {
            setKey(securityKey);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));

        } catch (Exception e) {
            logger.info("Exception", e);
            return null;
        }

    }

    public static String encrypt_resp(String strToEncrypt, String securityKey) {
        try {
            String key = Base64Service.encode(securityKey.getBytes());
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"));
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            logger.info("Exception", e);
            return null;
        }

    }

    public static String decrypt_req(String strToDecrypt, String securityKey) {
        try {
            String key = Base64Service.encode(securityKey.getBytes());
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"));
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));

        } catch (Exception e) {
            logger.info("Exception", e);
            return null;
        }

    }

//    public static void main(String[] args) {
//        JSONObject jSONObjectReq1 = new JSONObject();
//
////        jSONObjectReq1.put("kycid", "nisha123");
////        jSONObjectReq1.put("sharedto", "dsc.webseva@gmail.com");
////        jSONObjectReq1.put("productid", "26");
////        jSONObjectReq1.put("emailid", "dsc.webseva@gmail.com");
//        jSONObjectReq1.put("npayapplicantid", "42975309");
////        jSONObjectReq1.put("compid", "310086");
////        jSONObjectReq1.put("UniqueHash", "IIMEDLHFMKIGDMq5p.9ro5r8n@tznvy.p1z@IIMEDL");
////        jSONObjectReq1.put("userid", "27280");
//        jSONObjectReq1.put("deviceId", "118.185.8.52");
//        String encryptedString = DataEncDec.encrypt_resp(jSONObjectReq1.toString(), DataEncDec.securityKeyReq);
//        System.out.println("encryptedString " + encryptedString);
//
//        JSONObject jSONObjectReq = new JSONObject();
//        jSONObjectReq.put("encData", "C00MaJ704mifLE5bpYBfrD1gmEnwl7aVbmv9QC/vNqvE3GqzIVWIMknNDDmIRRRDX891BMJ3QyHwSAXq6y7feA==");
////
////        System.out.println("res" + DataEncDec.decrypt_req("7HhEGN/GA+G7s7dPWBfnn8WNsYFEgVMul/Wihnfq4KsM9gGO3aW1+btvcUKvtLt3", DataEncDec.securityKeyRes));
//        System.out.println("res" + DataEncDec.decrypt_req("7HhEGN/GA+G7s7dPWBfnnzAbEPzrug4+RtqxrcR3LCuHNGsyPInASx3Iq4rRArIVIrSNNvGg0g7gjd6j4eIgV+1SuxmU+GeXHYoc3JTwYlZrN6HwC2KXkCDKpfwfa3vCT35dSQKtfTi36LYWekYvbZVFgvkW8CQpVgGrXmGBZVyBS5MwM5Vz/rnnS2uen0M//uRoksfPbMVfmn5Jas4ZzdQ8DcS0sHAr9WlEDJokLYC5njAcnxhtDfedNAsxAPXSweVLipvo7UlngN5R48TetTjJf9os14I9ls7k63Co1etBtBOp/9LoNzChPxjJrnhlMhZPWBva2u3whaTp8cqLVJswcGIYqm+2/f1ZBv5da//sBDvf/DIBgQejgpGkHiB5", DataEncDec.securityKeyRes));
//
//    }
}
