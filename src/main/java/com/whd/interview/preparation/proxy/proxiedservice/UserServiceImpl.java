package com.whd.interview.preparation.proxy.proxiedservice;

/**
 * @author whd.java@gmail.com
 * @date 2018/10/29 14:22
 * @apiNote Describe the function of this class in one sentence
 */
public class UserServiceImpl implements IUserService {
    /***
     * get user's name
     * @return name
     */
    @Override
    public String getUserName() {
        return "username";
    }

    /***
     * get user's information by realName
     * @param realName user realName
     * @return user's information
     */
    @Override
    public String getUserInfo(String realName) {
        System.out.println(realName + " 18岁 大学生");
        return realName + " 18岁 大学生";
    }
}
