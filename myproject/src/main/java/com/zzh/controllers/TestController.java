package com.zzh.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zzh.common.AES;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;


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

    @GetMapping("test/write-file-new")
    public void writeFileNew() {
        String fileName = "d:\\kuka.txt";
        try {
            //使用这个构造函数时，如果存在kuka.txt文件，
            //则直接往kuka.txt中追加字符串
            FileWriter writer = new FileWriter(fileName);
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
        for (int i = 0; i < 5; i++) {
            Thread thread = new TestThread(countDownLatch, i, "str");
            thread.start();
        }

        try {
            // 阻塞当前线程，直到倒数计数器倒数到0
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("子线程执行时长：" + (end - start));
    }

    // 线程用法，带返回值
    @GetMapping("test/thread-return")
    public String testThreadReturn() throws Exception {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        List<Future> futureList = new ArrayList<Future>();

        for (Integer i = 0; i < 10; ++i) {
            CallableThread callableThread = new CallableThread(i.toString());
            Future<String> future = cachedThreadPool.submit(callableThread);
            futureList.add(future);
        }

//        cachedThreadPool.shutdown();
//        while (!cachedThreadPool.awaitTermination(1, TimeUnit.SECONDS));
//
//        try {
//            for (Future future : futureList) {
//                Gson gson = new Gson();
//                Map<String, Object> mapObj = gson.fromJson(future.get().toString(), new TypeToken<Map<String, String>>() {
//                }.getType());
//
//                System.out.println(future.get().toString());
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return "end";
    }

    @GetMapping("test/returnArray")
    public List<String> testReturnArray(@RequestParam(value = "testList") List<String> lstParam) {

        List<String> lst = new ArrayList<>();
        lst.add("111");
        lst.add("abc");

        return lst;
    }

    @GetMapping("test/threadBase")
    public String testThreadBase() {

        new Thread() {
            public void run() {
                try {
                    Thread.sleep(5000);
                    System.out.println("wait 5000");
                } catch (Exception e) {

                }

            }
        }.start();
        return "end";
    }

    public enum STATUS {
        AAA(-1), BBB(2), CCC(3);
        private Integer value = 0;

        STATUS(Integer value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    @GetMapping("test/enum")
    public Integer testEnum() {

        return STATUS.AAA.value();

    }

    @GetMapping("test/resttemplate")
    public void testRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // 填写登录用户名密码
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("username", "passwd"));

//    public static void main(String[] args) {
//        RestTemplate restTemplate = new RestTemplate();

//get方式***********************************************************************************************************

//	//参数直接放在URL中
//	String message = restTemplate.getForObject("http://localhost:8080/yongbarservice/appstore/appgoods/restTemplate?name=zhaoshijie&id=80", String.class );
//
//
//	//参数使用MAP传递
//	Map<String ,Object> urlVariables = new HashMap<String ,Object>();-
//	urlVariables.put("name", "zhaoshijie");
//	urlVariables.put("id", 80);
//	String message2 = restTemplate.getForObject("http://localhost:8080/yongbarservice/appstore/appgoods/restTemplate", String.class, urlVariables);



//delete方式***********************************************************************************************************

//delete方法（注意：delete方法没有返回值，说明，id=0这个参数在服务器端可以不定义该参数，直接使用request获取）
//	restTemplate.delete("http://localhost:8080/yongbarservice/appstore/appgoods/deleteranking?id=0");




//post方式***********************************************************************************************************
//使用MAP传递参数
//	Map<String ,Object> urlVariables = new HashMap<String ,Object>();
//	urlVariables.put("name", "zhaoshijie");
//	urlVariables.put("id", 80);
//	String message3 = restTemplate.postForObject("http://localhost:8080/yongbarservice/appstore/appgoods/restTemplate",null, String.class, urlVariables);

//直接使用URL传递参数
//	String message = restTemplate.postForObject("http://localhost:8080/yongbarservice/appstore/appgoods/restTemplate?name=zhaoshijie&id=80",null, String.class );



//put方式***********************************************************************************************************
//注意：delete方法没有返回值，说明，id=0这个参数在服务器端可以不定义该参数，直接使用request获取
//        restTemplate.put("http://localhost:8080/yongbarservice/appstore/appgoods/restTemplate?name=zhaoshijie&id=80" ,null);



//	System.out.println(message);
//	System.out.println(message2);
//	System.out.println(message3);
    }

}
