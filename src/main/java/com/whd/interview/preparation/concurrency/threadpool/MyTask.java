package com.whd.interview.preparation.concurrency.threadpool;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author whd.java@gmail.com
 * @date 2019/5/16 13:59
 * @apiNote Describe the function of this class in one sentence
 */
@Slf4j
@AllArgsConstructor(staticName = "of")
public class MyTask implements Callable<String> {

    private Integer index;

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public String call() throws Exception {
        Random random = new Random();
        int nextInt = random.nextInt(10);
        TimeUnit.SECONDS.sleep(nextInt);
        log.info("线程{}执行任务{}的执行时间为: {}秒", Thread.currentThread().getName(), index, nextInt);
        return "异步返回->线程" + Thread.currentThread().getName() + "的执行时间为: " + nextInt;
    }
}
