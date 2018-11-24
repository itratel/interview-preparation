package com.whd.interview.preparation.callback.abs;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.whd.interview.preparation.callback.calculator.Student;
import com.whd.interview.preparation.callback.entity.Answer;
import com.whd.interview.preparation.callback.entity.Question;
import com.whd.interview.preparation.callback.interfaces.CallBackInterface;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/22 23:20
 * @apiNote Describe the function of this class in one sentence
 **/
@Slf4j
public abstract class AbstractCallBack implements CallBackInterface {

    private static final int THREAD_SIZE = Runtime.getRuntime().availableProcessors() * 2;

    protected Student student;

    public AbstractCallBack(Student student) {
        this.student = student;
    }

    /***
     * 学生思考完毕告诉老师答案的回调函数
     * @param answer 答案
     * @return String
     */
    @Override
    public String tellAnswer(String answer) {
        return answer;
    }

    /***
     * 学生解决完毕告诉老师答案的回调函数
     * @param answer
     * @return
     */
    @Override
    public Answer tellAnswer0(Answer answer) {
        log.info("answer: {}" , answer);
        return answer;
    }

    /***
     * 老师向学生提问题,默认情况下 采用异步回调的方式实现
     * @param question 问题
     * @return {@link Answer}
     */
    protected Answer askQuestion(Question question) {
        ThreadFactory namedFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ExecutorService executorService = new ThreadPoolExecutor(THREAD_SIZE, THREAD_SIZE, 0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                namedFactory,
                new ThreadPoolExecutor.AbortPolicy());
        executorService.submit(() -> student.resolveQuestion(question, AbstractCallBack.this));
        executorService.shutdown();
        return Answer.of("dsa", question.getId(), "请稍等，结果正在计算喔！！！");
    }

    /***
     * 老师像学生提出多个问题，具体实现由子类实现
     * @param queue 问题队列
     * @return {@link List<Answer>}
     */
    protected abstract List<Answer> askQuestions(Queue<Question> queue);
}
