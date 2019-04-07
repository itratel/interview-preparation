package com.whd.interview.preparation.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * @author whd.java@gmail.com
 * @date 2019/04/07 11:40
 * @apiNote Describe the function of this class in one sentence
 */
public class CountdownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        long start = System.currentTimeMillis();
        try {
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    latch.countDown();
                    System.out.println(Thread.currentThread().getName() + "执行完毕，执行时间为: "
                            + (System.currentTimeMillis() - start) / 1000 +" 秒，latch = " + latch.getCount());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                    latch.countDown();
                    System.out.println(Thread.currentThread().getName() + "执行完毕，执行时间为: "
                            + (System.currentTimeMillis() - start) / 1000 +" 秒，latch = " + latch.getCount());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(() -> {
                try {
                    Thread.sleep(5000);
                    latch.countDown();
                    System.out.println(Thread.currentThread().getName() + "执行完毕，执行时间为: "
                            + (System.currentTimeMillis() - start) / 1000 +" 秒，latch = " + latch.getCount());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }catch (Exception e){
            e.printStackTrace();
        }
        //阻塞当前线程
        latch.await();
        System.out.println("所有线程执行完毕, 花费时间为:" + (System.currentTimeMillis() - start) / 1000 + "秒");
    }
}
