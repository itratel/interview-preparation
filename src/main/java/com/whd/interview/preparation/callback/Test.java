package com.whd.interview.preparation.callback;

import com.whd.interview.preparation.callback.entity.Answer;
import com.whd.interview.preparation.callback.entity.Question;
import com.whd.interview.preparation.callback.provider.TeacherB;
import com.whd.interview.preparation.callback.calculator.impl.Mtt;
import com.whd.interview.preparation.callback.calculator.Student;
import com.whd.interview.preparation.callback.calculator.impl.Whd;
import com.whd.interview.preparation.callback.provider.TeacherA;
import com.whd.interview.preparation.callback.provider.TeacherC;
import lombok.extern.slf4j.Slf4j;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/21 0:25
 * @apiNote Describe the function of this class in one sentence
 **/
@Slf4j
public class Test {

    public static void main(String[] args) {
        Student stu = new Mtt();
        TeacherB teacherB = new TeacherB(stu);
        Answer answer = teacherB.askQuestion(Question.of("wt123", "问题1", "你是什么人？"));
        log.info("answer: {}", answer);
        log.info("teacherB问答结束了");
        log.info("teacherB现在下课了");

//        log.info("------------------------------------------------");
//        Student student = new Whd();
//        TeacherA teacherA = new TeacherA(student);
//        Answer result = teacherA.askQuestion(Question.of("wt123", "问题1", "你是哪里人"));
//        log.info("result: {}", result);
//        log.info("teacherA问答结束了");
//        log.info("teacherA现在下课了");

//        log.info("------------------------------------------------");
//        Student whd = new Whd();
//        TeacherC teacherC = new TeacherC(whd);
//        Answer result = teacherC.askQuestion(Question.of("123", "问题1", "你是哪里人"));
//        log.info("result: {}", result);
//        log.info("teacherC问答结束了");
//        log.info("teacherC现在下课了");

    }
}
