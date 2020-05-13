package com.whd.interview.preparation.leetcode.multithread.alternateprint;


/**
 * 交替打印
 * <p>
 *     我们提供一个类：
 *
 * class FooBar {
 *   public void foo() {
 *     for (int i = 0; i < n; i++) {
 *       print("foo");
 *     }
 *   }
 *
 *   public void bar() {
 *     for (int i = 0; i < n; i++) {
 *       print("bar");
 *     }
 *   }
 * }
 * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
 *
 * 请设计修改程序，以确保 "foobar" 被输出 n 次。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: n = 1
 * 输出: "foobar"
 * 解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
 * 示例 2:
 *
 * 输入: n = 2
 * 输出: "foobarfoobar"
 * 解释: "foobar" 将被输出两次。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-foobar-alternately
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </p>
* @author whd.java@gmail.com
* @date 2020/5/8 17:33
* @version 0.0.1
* @since 0.0.1
*/
public class FooBar1 {
    private int n;
    private volatile boolean isPrint;
    private final Object lock;
    public FooBar1(int n) {
        this.n = n;
        this.lock = new Object();
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                if(isPrint) {
                    lock.wait();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                isPrint = true;
                lock.notify();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                if(!isPrint) {
                    lock.wait();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                isPrint = false;
                lock.notify();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        FooBar1 printSort = new FooBar1(2);
        Runnable printer1 = () -> System.out.print("foo");
        Runnable printer2 = () -> System.out.println("bar");

        new Thread(() -> {
            try {
                printSort.foo(printer1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread B").start();

        new Thread(() -> {
            try {
                printSort.bar(printer2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread C").start();
    }

}