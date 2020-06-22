package com.whd.interview.preparation.concurrency.threadpool;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.*;

/**
 * @author whd.java@gmail.com
 * @date 2019/05/25 22:44
 * @apiNote Describe the function of this class in one sentence
 */
public class PoolDemo {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        //使用谷歌带有监听器的执行器
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(service);
        ListenableFuture<String> future = executorService.submit(new Callable<String>() {
            /**
             * Computes a result, or throws an exception if unable to do so.
             *
             * @return computed result
             * @throws Exception if unable to compute a result
             */
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "123456";
            }
        });

        //添加监听方法
        Futures.addCallback(future, new FutureCallback<String>() {

            /***
             * Invoked with the result of the {@code Future} computation when it is successful.
             * @param result return result
             */
            @Override
            public void onSuccess(@Nullable String result) {
                //it is declared in unstable class 'com.google.common.util.concurrent.Futures' marked with @Beta
                System.out.println("result = " + result);

                //关闭线程池
                executorService.shutdown();
            }

            /**
             * Invoked when a {@code Future} computation fails or is canceled.
             *
             * <p>If the future's {@link Future#get() get} method throws an {@link ExecutionException}, then
             * the cause is passed to this method. Any other thrown object is passed unaltered.
             */
            @Override
            public void onFailure(Throwable t) {
                System.out.println("t.getMessage() = " + t.getMessage());
            }
        }, executorService);

    }
}
