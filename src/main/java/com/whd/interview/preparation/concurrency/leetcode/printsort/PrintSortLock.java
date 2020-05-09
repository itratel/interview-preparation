package com.whd.interview.preparation.concurrency.leetcode.printsort;

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
public class PrintSortLock {

    private boolean firstFinished;
    private boolean secondFinished;
    private final Object lock = new Object();

    public PrintSortLock() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (lock) {
            String name = Thread.currentThread().getName();
            System.out.println(name + " 获得锁成功！");
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            firstFinished = true;
            lock.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock) {
            String name = Thread.currentThread().getName();
            System.out.println(name + " 获得锁成功！");
            while (!firstFinished) {
                System.out.println(Thread.currentThread().getName() + " 释放了锁。");
                lock.wait();
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            secondFinished = true;
            lock.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {

        synchronized (lock) {
            String name = Thread.currentThread().getName();
            System.out.println(name + " 获得锁成功！");
            while (!secondFinished) {
                System.out.println(name + " 释放了锁。");
                lock.wait();
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }

   
    public static void main(String[] args) {
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
