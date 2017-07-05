package com.zzh.common;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by house on 2017/7/5.
 */
public class AES {

    private static String key = "52125ad5baf8b04567702ac9842479fa";

    public static String GEN(int keysize) {
        KeyGenerator kgen;
        try {
            kgen = KeyGenerator.getInstance("AES");
            kgen.init(keysize);
            SecretKey skey = kgen.generateKey();
            byte[] raw = skey.getEncoded();
            return asHex(raw);
        } catch (Exception ex) {
        }
        return null;
    }

    private static String asHex(byte buf[]) {
        StringBuilder strbuf = new StringBuilder(buf.length * 2);
        int i;

        for (i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10) {
                strbuf.append("0");
            }
            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }

        return strbuf.toString();
    }

    private static byte[] hexToBytes(String s) {
        return hexToBytes(s.toCharArray());
    }
    private static final char[] kDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
            'b', 'c', 'd', 'e', 'f'};

    private static byte[] hexToBytes(char[] hex) {
        int length = hex.length / 2;
        byte[] raw = new byte[length];
        for (int i = 0; i < length; i++) {
            int high = Character.digit(hex[i * 2], 16);
            int low = Character.digit(hex[i * 2 + 1], 16);
            int value = (high << 4) | low;
            if (value > 127) {
                value -= 256;
            }
            raw[i] = (byte) value;
        }
        return raw;
    }

    public static String aesEncode(String src) throws Exception {
        byte[] raw = hexToBytes(key);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(src.getBytes());
        String enc = (asHex(encrypted));
        return enc;
    }

    public static String aesDecode(String enc) throws Exception {
        byte[] raw = hexToBytes(key);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

        skeySpec = new SecretKeySpec(hexToBytes(key), "AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] original = cipher.doFinal(hexToBytes(enc));

        String dec = new String(original);
        return dec;
    }

    public static void main(String[] args) throws Exception {
        String encoder = aesEncode("nice");
        System.out.println(encoder);
        String decoder = aesDecode(encoder);
        System.out.println(decoder);
    }
}