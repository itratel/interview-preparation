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
        TeacherB teacher = new TeacherB(stu);
        String answer = teacher.askQuestion("你是什么人？");
        log.info("问答结束了");
    }
}
