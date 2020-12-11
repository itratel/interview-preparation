package com.whd.interview.preparation.java8.classlibapi.concurrency;

import com.whd.interview.preparation.utils.DateUtil;
import lombok.SneakyThrows;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static com.whd.interview.preparation.utils.DateUtil.now;

/**
 * <p>TestCompleteService<p/>
 *
 * @author whd.java@gmail.com
 * @version 0.0.1
 * @date 2020/4/26 13:48
 * @since 0.0.1
 */
public class TestCompletableFuture {

    @Test
    @SneakyThrows
    public void testDemo() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        System.out.println("现在开始获取数据!");
        //手动完成时间的
        completableFuture.complete("Future's Result");
        completableFuture.get();
    }


    @Test
    @SneakyThrows
    public void testRunAsync() {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            // Simulate a long-running Job
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("时间: " + now() + " I'll run in a separate thread than the main thread.");
        });

        System.out.println("时间: " + now() + " future================================ ");

        // Block and wait for the future to complete
        future.get();
        System.out.println("时间: " + now() + " future================================end ");
    }

    @Test
    @SneakyThrows
    public void testSupplyAsync() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // Simulate a long-running Job
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("时间: " + now() + " I'll run in a separate thread than the main thread.");
            return "Result of the asynchronous computation";
        });

        System.out.println("时间: " + now() + " future================================ ");

        // Block and wait for the future to complete
        String result = future.get();
        System.out.println("时间: " + now() + " future================================ " + result);
    }

    @SneakyThrows
    @Test
    public void test()  {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "12a3d4b5R6")
                .thenApplyAsync(String::toUpperCase)
                .thenApplyAsync(e -> {
                    try {
                        TimeUnit.SECONDS.sleep(4);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    return e;
                })
                .handleAsync((res, exception) -> {
                    if (exception != null) {
                        //handle exception here
                        return null;
                    } else {
                        return res;
                    }
                });
        //此时地future.get()方法仍然会阻塞主线程执行
        String s = future.get();
        System.out.println(s);
    }

    /***
     * 哪怕supplyAsync抛出了异常，whenComplete也会执行，意思就是，只要supplyAsync执行结束，它就会执行，
     * 不管是不是正常执行完。exceptionally只有在异常的时候才会执行
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void tests() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int a = 10 / 0;
            return 1;
        }).whenComplete((res, exception) -> {
            //whenComplete和whenCompleteAsync方法的区别在于：前者是由上面的线程继续执行，而后者是将whenCompleteAsync的任务继续交给线程池去做决定。
            System.out.println("res = " + res);
            System.out.println("exception = " + exception);
        }).exceptionally(exception -> {
            System.out.println(exception);
            return 2;
        });
        Integer integer = future.get();
        System.out.println("integer = " + integer);
    }


    /***
     * test dependency
     */
    @Test
    public void testss() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 5)
                .thenApply((r) -> {
                    r = r + 10;
                    return r;
                }).whenComplete((res, exception) -> {
                    if (exception == null) {
                        System.out.println("res = " + res);
                    }
                });
        Integer integer1 = future.get();
        System.out.println("integer1 = " + integer1);
        //出现了异常，handle方法可以拿到异常 e
        CompletableFuture<Integer> handle = CompletableFuture.supplyAsync(() -> {
            int i = 10 / 0;
            return 5;
        }).handle((res, e) -> {
            if (res == null) {
                return 10;
            }
            res = res + 1;
            return res;
        });
        Integer integer = handle.get();
        System.out.println("integer = " + integer);
    }
}
