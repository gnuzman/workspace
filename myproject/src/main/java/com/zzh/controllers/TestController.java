package com.zzh.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zzh.common.AES;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;



/**
 * Created by house on 2017/6/28.
 */
@RestController
@CrossOrigin
@Api(tags = {"test"}, description = "test api")
public class TestController {

    @Value("${com.zzh.key}")
    private String key;

    public static void callShell(String shellString) {
        try {
            Process process = Runtime.getRuntime().exec(shellString);
            int exitValue = process.waitFor();
            if (0 != exitValue) {
//                log.error("call shell failed. error code is :" + exitValue);
            }
        } catch (Throwable e) {
//            log.error("call shell failed. " + e);
        }
    }

    // 四种参数传入
    // 1.@RequestParam;
    // 2.@PathVariable;
    // 3.@RequestHeader;
    // 4.@RequestBody; <- 必需为post
    @GetMapping("/RequestParam")
    public String RequestParam(@RequestParam(required = false) String str) throws Exception {

        String encoder = AES.aesEncode("nice");
        System.out.println(encoder);
        String decoder = AES.aesDecode(encoder);
        System.out.println(decoder);

        return str + key;
    }

    @GetMapping("/PathVariable/{id}")
    public String PathVariable(@PathVariable String id) {
        return id;
    }

    @GetMapping("/RequestHeader")
    public String RequestHeader(@RequestHeader(value = "params", required = false) String params) {
        return params;
    }

    // GET：无副作用，幂等，不可带 Request Body
    // PUT：副作用，幂等，可以带 Request Body
    // POST：副作用，非幂等，可以带 Request Body
    // DELETE：副作用，幂等，不可带 Request Body
    @PostMapping("/RequestBody")
    public String RequestBody(@RequestBody String id) {
        return id;
    }

    @GetMapping("test/write-file")
    public void writeFile() {
        String fileName = "d:\\kuka.txt";
        try {
            //使用这个构造函数时，如果存在kuka.txt文件，
            //则直接往kuka.txt中追加字符串
            FileWriter writer = new FileWriter(fileName, true);
            SimpleDateFormat format = new SimpleDateFormat();
            String time = format.format(new Date());
            writer.write("\n\t" + time);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("test/shell")
    public String shell(@RequestParam(value = "cmd") String cmd) throws Exception {
        return executeShell(cmd);
    }

    // 执行shell脚本
    private String executeShell(String cmd) throws Exception {
        Process process = Runtime.getRuntime().exec(cmd);
        InputStreamReader ir = new InputStreamReader(process.getInputStream());
        LineNumberReader input = new LineNumberReader(ir);
        String line;
        String rtn = null;
        while ((line = input.readLine()) != null)
            rtn += line;
        input.close();
        ir.close();
        return rtn;
    }

    // Gson用法
    @GetMapping("test/gson")
    public String testGson() throws Exception {

        List<Map<String, String>> lstData = new ArrayList<>();
        Map<String, String> mapData1 = new HashMap<>();
        mapData1.put("username", "lmh");
        mapData1.put("passwd", "123");
        lstData.add(mapData1);
        mapData1.clear();
        mapData1.put("username", "zzh");
        mapData1.put("passwd", "234");
        lstData.add(mapData1);

        Gson gson = new Gson();
        String strData = gson.toJson(lstData);

        List<Map<String, String>> retList = gson.fromJson(strData, new TypeToken<List<Map<String, String>>>() {
        }.getType());

        return "";
    }

    // stream用法
    @GetMapping("test/stream")
    public void testStream() throws Exception {
        List<Map<String, String>> lstData = new ArrayList<>();
        Map<String, String> mapData1 = new HashMap<>();
        mapData1.put("username", "lmh");
        mapData1.put("passwd", "123");
        lstData.add(mapData1);
        mapData1.clear();
        mapData1.put("username", "zzh");
        mapData1.put("passwd", "234");
        lstData.add(mapData1);

        List<Map<String, String>> xxx = lstData.stream().filter(obj -> obj.get("username") == "zzh")
                .collect(Collectors.toList());

        String strPasswd = xxx.get(0).get("passwd");


        int i = 0;
    }

    // 线程用法
    @GetMapping("test/thread")
    public void testThread() throws Exception {
        long start = System.currentTimeMillis();

        // 创建一个初始值为5的倒数计数器
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for(int i = 0; i < 5; i++)
        {
            Thread thread = new TestThread(countDownLatch, i, "str");
            thread.start();
        }

        try
        {
            // 阻塞当前线程，直到倒数计数器倒数到0
            countDownLatch.await();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("子线程执行时长：" + (end - start));
    }

}
