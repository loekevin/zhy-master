package com.finest.zhy.comm;

import java.util.concurrent.Semaphore;

/**
 * Created by kezy on 2020/5/7.
 */
public class SemaphoreTest {

    private Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {
        SemaphoreTest semaphoreTest = new SemaphoreTest();
        semaphoreTest.test();
    }

    public void test() {
        try {
            semaphore.acquire();
            System.out.println("使用前--------->"+semaphore.availablePermits());
            //业务代码开始
            int sleepValue = ((int) (Math.random() *10000));
            Thread.sleep(sleepValue);
            //业务代码结束
            semaphore.release();
            System.out.println("使用后--------->"+semaphore.availablePermits());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
