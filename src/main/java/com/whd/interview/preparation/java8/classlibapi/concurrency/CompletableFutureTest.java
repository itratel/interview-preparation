package com.whd.interview.preparation.java8.classlibapi.concurrency;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * <p>CompletableFutureTest<p/>
 * CompletableFutureTest <br>
 *
 * @author whd.java@gmail.com
 * @date 2020/12/12 1:24
 * @since 1.0.0
 */
public class CompletableFutureTest {

    public static void main(String[] args) {
        testAccept();
    }

    @SneakyThrows
    @Test
    public static void testAccept() {
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> "Hello")
                .thenApply(s -> s + " World")
                .thenApply(String::toUpperCase)
//                .thenAccept(s -> System.out.println("s = " + s));
//                .thenRun(() -> System.out.println("我是来呵呵的"))
                .handle((s, ex) -> s + "dssda")
                .whenComplete((a, ex) -> {
                    if (ex == null) {
                        System.out.println("a = " + a);;
                    }
                })
                .exceptionally(e ->{
                    System.out.println("exceptionally exception:"+e.getMessage());
                    return "hello ijiangtao";
                });
        System.out.println("future.get() = " + future.get());
    }

}
