package com.whd.interview.preparation.callback.provider;

import com.whd.interview.preparation.callback.abs.AbstractCallBack;
import com.whd.interview.preparation.callback.calculator.Student;
import com.whd.interview.preparation.callback.entity.Answer;
import com.whd.interview.preparation.callback.entity.Question;

import java.util.List;
import java.util.Queue;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/20 23:41
 * @apiNote Describe the function of this class in one sentence
 **/
public class TeacherA extends AbstractCallBack {


    public TeacherA(Student student) {
        super(student);
    }

    /***
     * 老师向学生提问题
     */
    public String askQuestion(String question) {
        return student.solveQuestion(question, this);
    }

    /***
     * 老师向学生提问题
     * @param question
     * @return
     */
    @Override
    public Answer askQuestion(Question question) {
        return student.resolveQuestion(question, this);
    }

    /***
     * 老师像学生提出多个问题，具体实现由子类实现
     * @param queue 问题队列
     * @return {@link List<Answer>}
     */
    @Override
    protected List<Answer> askQuestions(Queue<Question> queue) {
        return null;
    }
}
