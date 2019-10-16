package com.whd.interview.preparation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author whd.java@gmail.com
 * @date 2019/10/03 20:14
 * @apiNote Describe the function of this class in one sentence
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class User {

    private String username;

    private String gender;

    private String age;

    private Integer ranking;
}
