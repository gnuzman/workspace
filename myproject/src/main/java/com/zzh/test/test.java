package com.zzh.test;


import java.net.InetAddress;

public class test {
    public static void main(String[] args) throws Exception {
        InetAddress address = InetAddress.getByName("sina.com");
        String ip = address.getHostAddress();

        int i = 0;
    }
}
