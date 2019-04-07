package com.whd.interview.preparation.concurrency;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/2 0:12
 * @apiNote MyCountTask
 * @see java.util.concurrent.ForkJoinPool
 **/
public class ForkJoinDemo extends RecursiveTask<Integer> {
    /***
     * 任务阈值
     */
    private static final int THRESHOLD = 100;

    private int start;

    private int end;

    public ForkJoinDemo(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */
    @Override
    protected Integer compute() {
        int sum = 0;
        //队列任务小就计算任务
        boolean isCanCompute = (end - start) <= THRESHOLD;
        if (isCanCompute){
            for (int i = start; i < end; i++) {
                sum += i;
            }
        } else {
            // 如果任务大于任务阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            ForkJoinDemo leftTask = new ForkJoinDemo(start, middle);
            ForkJoinDemo rightTask = new ForkJoinDemo(middle, end);
            //执行子任务
            leftTask.fork();
            rightTask.fork();
            //等待子任务执行完，并得到其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            //合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }


    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //生成一个计算任务，负责计算1到1000
        ForkJoinDemo countTask = new ForkJoinDemo(1, 200);
        // 执行一个任务
        Future<Integer> result = forkJoinPool.submit(countTask);
        try {
            System.out.println(result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
