package com.zzh.controllers;

import java.util.concurrent.CountDownLatch;

public class TestThread extends Thread
{
    private CountDownLatch countDownLatch;
    private Integer param1;
    private String param2;

    public TestThread(CountDownLatch countDownLatch, Integer param1, String param2)
    {
        this.countDownLatch = countDownLatch;
        this.param1 = param1;
        this.param2 = param2;
    }

    public void run()
    {
        System.out.println(this.getName() + "子线程开始");
        try
        {
            // 子线程休眠五秒
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println(this.getName() + "子线程结束 " + "params: " + param1 + " " + param2);

        // 倒数器减1
        countDownLatch.countDown();
    }
}