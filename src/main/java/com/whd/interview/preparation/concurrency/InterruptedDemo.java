package com.whd.interview.preparation.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * @author whd.java@gmail.com
 * @date 2020/05/07 22:01
 * @apiNote Describe the function of this class in one sentence
 */
public class InterruptedDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                if (!Thread.currentThread().isInterrupted()) {
                    System.out.println("before:" + Thread.currentThread().isInterrupted());
                    Thread.interrupted(); //对线程进行复位，由 true 变成 false
                    System.out.println("after:" + Thread
                            .currentThread().isInterrupted());
                } else {
                    System.out.println("当前线程还未被中断");
                }
            }
        }, "interruptDemo");
        thread.start();
        TimeUnit.MILLISECONDS.sleep(1);
        thread.interrupt();
    }
}
