package com.whd.interview.preparation.callback.calculator;

import com.whd.interview.preparation.callback.entity.Answer;
import com.whd.interview.preparation.callback.entity.Question;
import com.whd.interview.preparation.callback.interfaces.CallBackInterface;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/20 23:52
 * @apiNote Describe the function of this class in one sentence
 **/
public interface Student {

    /***
     * 学生思考问题
     * @param question 问题
     * @param backInterface 回调接口
     * @return String
     */
    String solveQuestion(String question, CallBackInterface backInterface);

    /***
     * 学生重新解决问题
     * @param question 问题
     * @param backInterface 回调接口
     * @return {@link Answer}
     */
    Answer resolveQuestion(Question question, CallBackInterface backInterface);

}
