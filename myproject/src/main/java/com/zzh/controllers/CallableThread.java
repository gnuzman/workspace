package com.zzh.controllers;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
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
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("hostname", "hostname");
        mapData.put("cmd", cmd);
        Gson gson = new Gson();
        String str = gson.toJson(mapData);
        return str;
    }

}
