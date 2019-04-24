package com.whd.interview.preparation.concurrency.threadlocal;

/**
 * @author whd.java@gmail.com
 * @date 2019/4/24 16:44
 * @apiNote Describe the function of this class in one sentence
 */
public class ThreadLocalDemo {

    ThreadLocal<Long> longLocal = ThreadLocal.withInitial(() -> Thread.currentThread().getId());

    ThreadLocal<String> stringLocal = ThreadLocal.withInitial(() -> Thread.currentThread().getName());


    private void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    private long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final ThreadLocalDemo test = new ThreadLocalDemo();


//        test.set(); 如果初始状态没有设值就会报空
        System.out.println(Thread.currentThread().getName() + "------ test.getLong() " + test.getLong());
        System.out.println(Thread.currentThread().getName() + "------ test.getString() " + test.getString());

        Thread thread1 = new Thread(() -> {
                test.set();
                System.out.println(Thread.currentThread().getName() + "------ test.getLong() " + test.getLong());
                System.out.println(Thread.currentThread().getName() + "------ test.getString() " + test.getString());
        });
        thread1.start();
        thread1.join();

        System.out.println(Thread.currentThread().getName() + "------ test.getLong() " + test.getLong());
        System.out.println(Thread.currentThread().getName() + "------ test.getString() " + test.getString());
    }
}
