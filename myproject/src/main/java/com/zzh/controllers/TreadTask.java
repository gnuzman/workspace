package com.zzh.controllers;

public class TreadTask implements Runnable {
    private String param;
    public TreadTask(String param) {
        this.param = param;
    }

    @Override
    public void run() {
        try {
            System.out.println(param);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
