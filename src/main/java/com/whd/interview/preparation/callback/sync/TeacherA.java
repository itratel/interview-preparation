package com.whd.interview.preparation.callback.sync;

import com.whd.interview.preparation.callback.CallBackInterface;
import com.whd.interview.preparation.callback.Student;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/20 23:41
 * @apiNote Describe the function of this class in one sentence
 **/
public class TeacherA implements CallBackInterface {

    private Student student;

    public TeacherA(Student student){
        this.student = student;
    }

    /***
     * 学生思考完毕告诉老师答案
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
        return student.solveQuestion(question, this);
    }

}
