package com.whd.interview.preparation.callback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/22 22:55
 * @apiNote Describe the function of this class in one sentence
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class Answer {
    /***
     * 答案编号
     */
    private String id;
    /***
     * 问题编号
     */
    private String qId;
    /**
     * 答案结果
     */
    private String result;
}
