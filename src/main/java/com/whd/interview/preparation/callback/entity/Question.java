package com.whd.interview.preparation.callback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/22 22:44
 * @apiNote Describe the function of this class in one sentence
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class Question {
    /***
     * 问题编号
     */
    private String id;
    /***
     * 问题标题
     */
    private String title;
    /***
     * 问题内容
     */
    private String content;
}
