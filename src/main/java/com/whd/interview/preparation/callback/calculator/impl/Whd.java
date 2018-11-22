package com.whd.interview.preparation.callback.calculator.impl;

import com.whd.interview.preparation.callback.calculator.Student;
import com.whd.interview.preparation.callback.entity.Answer;
import com.whd.interview.preparation.callback.entity.Question;
import com.whd.interview.preparation.callback.interfaces.CallBackInterface;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/22 22:23
 * @apiNote Describe the function of this class in one sentence
 **/
@Slf4j
public class Whd implements Student {

    /***
     * 学生思考问题
     * @param backInterface 回调接口
     * @return String
     */
    @Override
    public String solveQuestion(String question, CallBackInterface backInterface) {
        try {
            log.info("{}正在解决问题 {}", this.getClass().getSimpleName(), question);
            //模拟解决问题的消耗操作
            Thread.sleep(4000);
            log.info("{} 解决完了，准备调用回调函数告诉老师答案", question);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //回掉函数，告诉老师正确答案
        return backInterface.tellAnswer("我是地球人");
    }

    /***
     * 学生重新解决问题
     * @param question 问题
     * @param backInterface 回调接口
     * @return {@link Answer}
     */
    @Override
    public Answer resolveQuestion(Question question, CallBackInterface backInterface) {
        Answer answer = null;
        try {
            log.info("{}正在解决问题 {}", this.getClass().getSimpleName(), question.getId());
            //模拟解决问题的消耗操作
            Thread.sleep(8000);
            answer = Answer.of("uuid" + new Random().nextInt(100), question.getId(), "地球人");
            log.info("{} 已经解决，准备调用回调函数告诉老师答案", question.getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return backInterface.tellAnswer0(answer);
    }
}
