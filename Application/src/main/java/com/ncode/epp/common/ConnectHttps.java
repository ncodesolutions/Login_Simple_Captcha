package com.ncode.epp.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.log4j.Logger;
import sun.security.ssl.SSLContextImpl.TLSContext;

public class ConnectHttps {

    final static Logger logger = Logger.getLogger(ConnectHttps.class);

    public static String getResponseFromHttps(String asaUrl, String xml) {
        SSLContext sslcontext = null;
        TLSContext tlsc = null;
        String responseXML = "";
        try {
            TrustManager[] trustAllCerts = {new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };
            sslcontext = SSLContext.getInstance("TLS");

            sslcontext.init(new KeyManager[0], trustAllCerts, new SecureRandom());
        } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
            logger.info(new Date() + localNoSuchAlgorithmException.getMessage());

        } catch (KeyManagementException localKeyManagementException) {
            logger.info(new Date() + localKeyManagementException.getMessage());
        }

        String urlParameters = xml;
        byte[] bytes = urlParameters.getBytes();
        try {
            SSLSocketFactory factory = sslcontext.getSocketFactory();

            InputStream is = null;

            HostnameVerifier allHostsValid = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            String ip = asaUrl;
            URL url = new URL(ip);
            String tempUrl = url.toString();
            if (tempUrl.startsWith("http://")) {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);
                OutputStream os = connection.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                osw.write(urlParameters);
                osw.flush();
                osw.close();
                is = connection.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(is));
                String strTemp;
                while ((strTemp = in.readLine()) != null) {
                    responseXML = responseXML + strTemp;
                }
                is.close();
                in.close();
            } else {
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setHostnameVerifier(allHostsValid);
                connection.setSSLSocketFactory(factory);
                connection.setHostnameVerifier(new DummyHostnameVerifier());
                OutputStream os = connection.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                osw.write(urlParameters);
                osw.flush();
                osw.close();
                is = connection.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(is));
                String strTemp;
                while ((strTemp = in.readLine()) != null) {
                    responseXML = responseXML + strTemp;
                }

                is.close();
                in.close();
            }

        } catch (ConnectException localConnectException) {
            logger.info(new Date() + localConnectException.getMessage());
        } catch (Exception e) {
            logger.info(new Date() + e.getMessage());
        }

        return responseXML;
    }

    public static String getResponseFromHttpsBytes(String asaUrl, byte[] xml) {
        SSLContext sslcontext = null;
        String responseXML = "";
        try {
            TrustManager[] trustAllCerts = {new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };
            sslcontext = SSLContext.getInstance("TLS");

            sslcontext.init(new KeyManager[0], trustAllCerts, new SecureRandom());
        } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
        } catch (KeyManagementException localKeyManagementException) {
        }

        try {
            SSLSocketFactory factory = sslcontext.getSocketFactory();

            InputStream is = null;
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            String ip = asaUrl;
            URL url = new URL(ip);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(xml.length));

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setHostnameVerifier(allHostsValid);
            connection.setSSLSocketFactory(factory);

            OutputStream os = connection.getOutputStream();

            os.write(xml);
            os.flush();
            os.close();
            is = connection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            String strTemp;
            while ((strTemp = in.readLine()) != null) {
                responseXML = responseXML + strTemp;
            }

            is.close();
            in.close();
        } catch (ConnectException localConnectException) {
        } catch (Exception localException) {
        }
        return responseXML;
    }

    public static class DummyHostnameVerifier
            implements HostnameVerifier {

        public boolean verify(String urlHostname, String certHostname) {
            return true;
        }

        @Override
        public boolean verify(String arg0, SSLSession arg1) {
            return true;
        }
    }

    public static class DummyTrustManager
            implements X509TrustManager {

        public boolean isClientTrusted(X509Certificate[] cert) {
            return true;
        }

        public boolean isServerTrusted(X509Certificate[] cert) {
            return true;
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {
        }
    }
}
