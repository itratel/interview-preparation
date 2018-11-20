package com.whd.interview.preparation.callback;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/20 22:43
 * @apiNote 回调接口
 **/
public interface CallBackInterface {

    /***
     * 学生思考完毕告诉老师答案的回调函数
     * @param answer 答案
     * @return String
     */
    String tellAnswer(String answer);
}
