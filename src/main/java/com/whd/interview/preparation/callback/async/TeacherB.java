package com.whd.interview.preparation.callback.async;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.whd.interview.preparation.callback.CallBackInterface;
import com.whd.interview.preparation.callback.Student;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/21 0:35
 * @apiNote Describe the function of this class in one sentence
 **/
@Slf4j
public class TeacherB implements CallBackInterface {

    private static final int THREAD_SIZE = Runtime.getRuntime().availableProcessors() * 2;

    private Student student;

    public TeacherB(Student student){
        this.student = student;
    }
    /***
     * 学生思考完毕告诉老师答案的回调函数
     * @param answer 答案
     * @return String
     */
    @Override
    public String tellAnswer(String answer) {
        return "very good，your answer is: " + answer;
    }

    /***
     * 老师向学生提问题
     */
    public String askQuestion(String question) {
        ThreadFactory namedFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ExecutorService executorService = new ThreadPoolExecutor(THREAD_SIZE, THREAD_SIZE, 0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                namedFactory,
                new ThreadPoolExecutor.AbortPolicy());
        executorService.submit(() -> student.solveQuestion(question, TeacherB.this));
        executorService.shutdown();
        return "请稍等，结果正在计算喔！！！";
    }
}
