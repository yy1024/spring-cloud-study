package com.wanda.yangyong67.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class Md5Utils {
    /**
     * log日志
     */
    private static final Logger logger = Logger.getLogger(Md5Utils.class);
    /**
     * MD5加密
     */
    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.reset();

            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
        } catch (UnsupportedEncodingException e) {
            logger.error(e);
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }

        return md5StrBuff.toString();
    }


    /**
     * md5加密产生，产生128位（bit）的mac
     * 将128bit Mac转换成16进制代码
     * @param strSrc
     * @param key
     * @return
     */
    public static String MD5Encode(String strSrc, String key) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(strSrc.getBytes("UTF8"));

            String result = "";
            byte[] temp;
            temp = md5.digest(key.getBytes("UTF8"));


            for (int i = 0; i < temp.length; i++) {
                result += Integer.toHexString(
                        (0x000000ff & temp[i]) | 0xffffff00).substring(6);
            }
            return result;

        } catch (NoSuchAlgorithmException e) {
            logger.error(e);

        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }



    public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
