package com.finest.zhy.comm;


import com.finest.zhy.comm.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * 描述：并发测试
 * Created by kezy on 2020/1/9.
 */
public final class ConcurrentTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConcurrentTest.class);

    public static void main(String[] args) throws InterruptedException {
        Runnable taskTemp = new Runnable() {

            private int iCounter;

            @Override
            public void run() {
                for(int i = 0; i < 10; i++) {
                    // 发起请求
                    String result = HttpClientUtil.doGet("http://localhost:9999/resources-interface/resource/userList?token=2e631090e80748aa98f75bb886b5e474");
                    LOGGER.info(i+":"+result);
                    iCounter++;
                    System.out.println(System.nanoTime() + " [" + Thread.currentThread().getName() + "] iCounter = " + iCounter);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        ConcurrentTest latchTest = new ConcurrentTest();
        latchTest.startTaskAllInOnce(2, taskTemp);
    }

    public long startTaskAllInOnce(int threadNums, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(threadNums);
        for(int i = 0; i < threadNums; i++) {
            Thread t = new Thread() {
                public void run() {
                    try {
                        // 使线程在此等待，当开始门打开时，一起涌入门中
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            // 将结束门减1，减到0时，就可以开启结束门了
                            endGate.countDown();
                        }
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            };
            t.start();
        }
        long startTime = System.nanoTime();
        System.out.println(startTime + " [" + Thread.currentThread() + "] All thread is ready, concurrent going...");
        // 因开启门只需一个开关，所以立马就开启开始门
        startGate.countDown();
        // 等等结束门开启
        endGate.await();
        long endTime = System.nanoTime();
        System.out.println(endTime + " [" + Thread.currentThread() + "] All thread is completed.");
        return endTime - startTime;
    }
}