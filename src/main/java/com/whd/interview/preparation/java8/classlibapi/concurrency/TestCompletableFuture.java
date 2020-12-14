package com.whd.interview.preparation.java8.classlibapi.concurrency;

import cn.hutool.core.util.RandomUtil;
import com.whd.interview.preparation.entity.User;
import com.whd.interview.preparation.utils.DateUtil;
import lombok.SneakyThrows;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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

    /***
     * 测试thenAccept
     */
    @Test
    public void testThenAccept() {
        CompletableFuture<Void> future2 = CompletableFuture.supplyAsync(() -> {
            return ThreadLocalRandom.current().nextInt(100);
        }).thenAccept(n -> {
            System.out.println("n = " + n);
        });
    }


    /***
     * 测试testThenRun
     */
    @Test
    public void testThenRun() {
        CompletableFuture<Void> future2 = CompletableFuture.supplyAsync(() -> {
            return ThreadLocalRandom.current().nextInt(100);
        }).thenRun(() -> {
            System.out.println("完成收直接做这件事情-->");
        });
    }


    /***
     * 测试testThenRun
     */
    @Test
    public void testApplyToEither() {
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "通过方式A获取商品a";
        });
        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "通过方式B获取商品a";
        });
        CompletableFuture<String> futureC = futureA.applyToEither(futureB, product -> "结果:" + product);
        CompletableFuture<Void> futureD = futureA.acceptEither(futureB, System.out::println);
        CompletableFuture<Void> futureE = futureA.runAfterEither(futureB, () -> System.out.println("最早的任务完成过后要做的事情"));
        //结果:通过方式A获取商品a
        System.out.println(futureC.join());
        System.out.println(futureD.join());
        System.out.println(futureE.join());

    }
    /***
     * 哪怕supplyAsync抛出了异常，whenComplete也会执行，意思就是，只要supplyAsync执行结束，它就会执行，不管是不是正常执行完。
     * exceptionally只有在异常的时候才会执行
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testWhenComplete() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            int a = 10 / 0;
            return 1;
        }).whenComplete((res, exception) -> {
            //whenComplete和whenCompleteAsync方法的区别在于：前者是由上面的线程继续执行，而后者是将whenCompleteAsync的任务继续交给线程池去做决定。
            System.out.println("res = " + res);
            System.out.println("whenComplete exception = " + exception);
        }).exceptionally(exception -> {
            System.out.println("exceptionally exception = " + exception);
            return 2;
        });
        Integer integer1 = future1.get();
        System.out.println("integer1 = " + integer1);


        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            return ThreadLocalRandom.current().nextInt(100);
        }).whenComplete((res, exception) -> {
            //whenComplete和whenCompleteAsync方法的区别在于：前者是由上面的线程继续执行，而后者是将whenCompleteAsync的任务继续交给线程池去做决定。
            System.out.println("res = " + res);
            System.out.println("whenComplete exception = " + exception);
        }).exceptionally(exception -> {
            System.out.println("exceptionally exception = " + exception);
            return 2;
        });
        Integer integer2 = future2.get();
        System.out.println("integer2 = " + integer2);
    }


    @SneakyThrows
    @Test
    public void testHandle() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            int a = 10 / 0;
            return 1;
        })
        .thenApply(n -> n + 20)
        .handle((res, exception) -> {
            System.out.println("res = " + res);
            System.out.println("handle exception = " + exception);
            if (exception != null) {
                //handle exception here
                return 100;
            } else {
                return res;
            }
        })
        //因为handle方法是带返回值的，所以不管你handle方法之前的方法是否发生异常，handle方法都会处理并返回值，所以handle方法后的exceptionally方法是永远不会执行的
        .exceptionally(exception -> {
            System.out.println("exceptionally exception = " + exception);
            return 0;
        });
        //此时地future.get()方法仍然会阻塞主线程执行
        Integer s1 = future1.get();
        System.out.println("-------------------------------->" + s1);

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            return ThreadLocalRandom.current().nextInt(100);
        })
        .thenApply(n -> n + 20)
        .handle((res, exception) -> {
            System.out.println("res = " + res);
            System.out.println("handle exception = " + exception);
            if (exception != null) {
                //handle exception here
                return 100;
            } else {
                return res;
            }
        })
        .exceptionally(exception -> {
            System.out.println("exceptionally exception = " + exception);
            return 0;
        });
        //此时地future.get()方法仍然会阻塞主线程执行
        Integer s2 = future2.get();
        System.out.println("-------------------------------->" + s2);
    }

    /***
     * 获取用户信息
     * @param userId 用户id
     * @return {@link CompletableFuture<User>}
     */
    private CompletableFuture<User> getUsersDetail(String userId) {
        return CompletableFuture.supplyAsync(() -> User.of(userId, "男", ThreadLocalRandom.current().nextLong(50) + "", 12));
    }

    /***
     * 获取信用分
     * @param user 用户信息
     * @return {@link CompletableFuture<Double>}
     */
    private CompletableFuture<Double> getCreditRating(User user) {
        return CompletableFuture.supplyAsync(() -> Double.parseDouble(user.getAge()) + ThreadLocalRandom.current().nextDouble());
    }

    /***
     * test dependency
     */
    @Test
    public void testThenCompose() throws ExecutionException, InterruptedException {
        //如果使用thenApply()达到预期的结果会发生什么,不符合题意
        CompletableFuture<CompletableFuture<Double>> result = getUsersDetail("userId").thenApply(this::getCreditRating);
        //使用thenCompose
        CompletableFuture<Double> future = getUsersDetail("userId").thenCompose(this::getCreditRating);
        Double aDouble = future.get();
        System.out.println("aDouble = " + aDouble);
    }

    /***
     * 获取体重
     * @return {@link CompletableFuture<Double>}
     */
    private CompletableFuture<Double> weightInKgFuture() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 65.0;
        });
    }

    /***
     * 获取身高
     * @return {@link CompletableFuture<Double>}
     */
    private CompletableFuture<Double> heightInCmFuture() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 177.8;
        });
    }


    /***
     * test dependency
     */
    @Test
    public void testThenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<Double> combinedFuture = weightInKgFuture().thenCombine(heightInCmFuture(), (weightInKg, heightInCm) -> {
                    Double heightInMeter = heightInCm / 100;
                    return weightInKg / (heightInMeter * heightInMeter);
                });
        Double aDouble = combinedFuture.get();
        System.out.println("aDouble = " + aDouble);
        CompletableFuture<Void> futureD = weightInKgFuture().thenAcceptBoth(heightInCmFuture(), (a, b) -> System.out.println("a + b = " + (a + b)));
        CompletableFuture<Void> futureE = weightInKgFuture().runAfterBoth(heightInCmFuture(), () -> System.out.println("两件事情完成收做的事情"));
        System.out.println(futureD.join());
        System.out.println(futureE.join());
    }

    /***
     * 获取数据
     * @param pageLink 页面链接
     * @return {@link String}
     */
    private String getStr(String pageLink) {
        String demo = "CompletableFuture";
        StringBuilder str = new StringBuilder();
        int a = Math.abs(pageLink.hashCode() % 10);
        for (int i = 0; i < a; i++) {
            str.append(RandomUtil.randomString(3)).append(demo).append(RandomUtil.randomString(3));
        }
        return str.toString();
    }


    /***
     * 下载文件的异步方法
     * @param pageLink 链接地址
     * @return {@link CompletableFuture<String>}
     */
    public CompletableFuture<String> downloadWebPage(String pageLink) {
        return CompletableFuture.supplyAsync(() -> {
            // 下载并返回网页内容的代码
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return getStr(pageLink);
        });
    }


    @SneakyThrows
    @Test
    public void testAllOf() {
        String baseDir = "http://120.78.190.213/images/";
        List<String> webPageLinks = Arrays.asList(baseDir + "learn-java8",
                baseDir + "egg_floor.jpg",
                baseDir + "k8s.png",
                baseDir + "tomcat.jpg",
                baseDir + "运行时数据区.png");
        //异步下载所有网页的内容
        List<CompletableFuture<String>> pageContentFutures = webPageLinks.stream()
                .map(this::downloadWebPage).collect(Collectors.toList());
        // 使用allOf()方法创建一个合并的Future
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(pageContentFutures.toArray(new CompletableFuture[0]));
        CompletableFuture<List<String>> allPageContentsFuture = allFutures.thenApply(v -> pageContentFutures.stream().map(CompletableFuture::join).collect(Collectors.toList()));

        // 计算具有“CompletableFuture”关键字的网页的数量
        CompletableFuture<Long> countFuture = allPageContentsFuture.thenApply(pageContents -> pageContents.stream()
                .filter(pageContent -> pageContent.contains("CompletableFuture"))
                .count());
        System.out.println("countFuture = " + countFuture.get());
    }


}
