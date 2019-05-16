package com.whd.interview.preparation.concurrency.threadpool;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author whd.java@gmail.com
 * @date 2019/5/16 13:47
 * @apiNote Describe the function of this class in one sentence
 */
@Slf4j
public class ThreadPoolDemo {

    private static final int THREAD_SIZE = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ThreadFactory namedFactory = new ThreadFactoryBuilder().setNameFormat("task-pool-%d").build();
        ExecutorService executorService = new MonitorThreadPool(THREAD_SIZE, THREAD_SIZE * 4, 0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                namedFactory,
                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 20; i++) {
            Future<String> future = executorService.submit(MyTask.of(i));
            if (future.isDone()){
                String result = future.get();
                log.info("result: {}", result);
            }
        }
    }

}
