package com.zzh.test;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class test {
    public static void main(String[] args) throws Exception {
//        InetAddress address = InetAddress.getByName("sina.com");
//        String ip = address.getHostAddress();
//
//        int i = 0;



        Socket socket = new Socket("192.168.42.84", 10050);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        OutputStream output = socket.getOutputStream();

        String key = "[system.swap.size[,free]";
        byte[] bytes = (key + "\n").getBytes();
        output.write(bytes);
        try {
            while (true) {
                String inputLine = input.readLine();
                System.out.println(inputLine);
                Thread.sleep(1000);
            }
        } catch (Exception e) {

        } finally {
            socket.close();
        }



    }
}
