
package com.example.aims.subsystem.vnpay;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.*;

class ConfigVNPay {

    public static String vnp_PayUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    public static String vnp_ReturnUrl = "http://aismtest.com/vnpay_jsp/vnpay_return.jsp";
    public static String vnp_TmnCode = "ZS6CKLLB";
    public static String vnp_HashSecret = "NPZY5P726YTDLND5OVVSLQNIA1SV8HBD"; // hoặc QL51HVQVBD2JI24ZZDK7TUDRDRAD1781 hoặc ODAT4XHI366QZFDV5O16KJYBFRHCNDZX
    public static String vnp_Version = "2.1.0";
    public static String vnp_Command_pay = "pay";
    public static String vnp_BankCode = "NCB";

    public static String hmacSHA512(final String key, final String data) {
        try {

            if (key == null || data == null) {
                throw new NullPointerException();
            }
            final Mac hmac512 = Mac.getInstance("HmacSHA512");
            byte[] hmacKeyBytes = key.getBytes();
            final SecretKeySpec secretKey = new SecretKeySpec(hmacKeyBytes, "HmacSHA512");
            hmac512.init(secretKey);
            byte[] dataBytes = data.getBytes();
            byte[] result = hmac512.doFinal(dataBytes);
            StringBuilder hexString = new StringBuilder();
            for (byte b : result) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (Exception ex) {
            return "";
        }
    }

    public static String getIpAddress() {
        return "127.0.0.1";
    }

    public static String getRandomNumber(int len) {
        Random rnd = new Random();
        String chars = "0123456789";
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
