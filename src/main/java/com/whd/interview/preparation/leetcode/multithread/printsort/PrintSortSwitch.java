package com.whd.interview.preparation.leetcode.multithread.printsort;

import java.util.concurrent.CountDownLatch;

/**
 *
 * 1114. 按序打印
 * <p>PrintSort<p/>
 * <p>
 * public class PrintSort {
 *   public void one() { print("one"); }
 *   public void two() { print("two"); }
 *   public void three() { print("three"); }
 * }
 * 三个不同的线程将会共用一个 PrintSort 实例。
 *
 * 线程 A 将会调用 one() 方法
 * 线程 B 将会调用 two() 方法
 * 线程 C 将会调用 three() 方法
 * 请设计修改程序，以确保 two() 方法在 one() 方法之后被执行，three() 方法在 two() 方法之后被执行。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-in-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </p>
 * @author whd.java@gmail.com
 * @date 2020/5/8 17:33
 * @version 0.0.1
 * @since 0.0.1
 */
public class PrintSortSwitch {

    private CountDownLatch latch2;
    private CountDownLatch latch3;

    public PrintSortSwitch() {
        latch2 = new CountDownLatch(1);
        latch3 = new CountDownLatch(1);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        latch2.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        latch2.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        latch3.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        latch3.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

   
    public static void main(String[] args) throws InterruptedException {

        PrintSortLock printSort = new PrintSortLock();
        Runnable printer1 = () -> System.out.println("one");
        Runnable printer2 = () -> System.out.println("two");
        Runnable printer3 = () -> System.out.println("three");

        new Thread(() -> {
            try {
                printSort.second(printer2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread B").start();

        new Thread(() -> {
            try {
                printSort.third(printer3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread C").start();

        new Thread(() -> {
            try {
                printSort.first(printer1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread A").start();
    }
}
