package com.whd.interview.preparation.callback.async;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.whd.interview.preparation.callback.CallBackInterface;
import com.whd.interview.preparation.callback.Student;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/21 0:35
 * @apiNote Describe the function of this class in one sentence
 **/
@Slf4j
public class TeacherB implements CallBackInterface {


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
        log.info("very good，your answer is: {}", answer);
        return "very good，your answer is: " + answer;
    }

    /***
     * 老师向学生提问题
     */
    public String askQuestion(String question) {
        new Thread(() -> {
            //调用学生的解决问题的方法
            student.solveQuestion(question, this);

        }).start();
        return null;
    }
}
