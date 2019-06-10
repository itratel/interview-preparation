package com.whd.interview.preparation.concurrency.threadpool;

import java.util.concurrent.*;

/**
 * @author whd.java@gmail.com
 * @date 2019/05/25 22:44
 * @apiNote Describe the function of this class in one sentence
 */
public class PoolDemo {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        Future future = service.submit(new Callable<String>() {
            /**
             * Computes a result, or throws an exception if unable to do so.
             *
             * @return computed result
             * @throws Exception if unable to compute a result
             */
            @Override
            public String call() throws Exception {
                Thread.sleep(6000);
                return "123456";
            }
        });
        try {
            System.out.println(future.get());
            System.out.println("------------------------future---------------------");
            service.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
