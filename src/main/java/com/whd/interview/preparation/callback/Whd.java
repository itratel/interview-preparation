package com.whd.interview.preparation.callback;

import lombok.extern.slf4j.Slf4j;

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
}
