package com.whd.interview.preparation.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * @author whd.java@gmail.com
 * @date 2020/05/07 21:49
 * @apiNote Describe the function of this class in one sentence
 * <p>
 * 当其他线程通过调用当前线程的 interrupt 方法，表示向当
 * 前线程打个招呼，告诉他可以中断线程的执行了，至于什
 * 么时候中断，取决于当前线程自己。
 * 线程通过检查自身是否被中断来进行响应，可以通过
 * isInterrupted()来判断是否被中断。
 * </p>
 */
public class InterruptDemo {

    private static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                //默认情况下 isInterrupted 返回 false、通过 thread.interrupt 变成了 true
                i++;
            }
            System.out.println("Num:" + i);
        }, "interruptDemo");
        thread.start();
        TimeUnit.MILLISECONDS.sleep(1);
        thread.interrupt();
    }
}
