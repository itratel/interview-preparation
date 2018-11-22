package com.whd.interview.preparation.callback;

import com.whd.interview.preparation.callback.async.TeacherB;
import com.whd.interview.preparation.callback.sync.TeacherA;
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
        String answer = teacherB.askQuestion("你是什么人？");
        log.info("answer: {}", answer);
        log.info("teacherB问答结束了");
        log.info("teacherB现在下课了");

        log.info("------------------------------------------------");
        Student student = new Whd();
        TeacherA teacherA = new TeacherA(student);
        String result = teacherA.askQuestion("你是哪里人？");
        log.info("result: {}", result);
        log.info("teacherA问答结束了");
        log.info("teacherA现在下课了");

    }
}
