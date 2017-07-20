package com.zzh.controllers;

import java.util.concurrent.Callable;

class CallableThread implements Callable<String> {

    private String cmd;

    public CallableThread(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        System.out.println(this.cmd + " 子线程结束");
        return this.cmd;
    }

}
