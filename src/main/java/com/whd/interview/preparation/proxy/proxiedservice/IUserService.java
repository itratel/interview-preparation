package com.whd.interview.preparation.proxy.proxiedservice;

/**
 * @author whd.java@gmail.com
 * @date 2018/10/29 14:16
 * @apiNote Describe the function of this class in one sentence
 */
public interface IUserService {

    /***
     * get user's name
     * @return name
     */
    String getUserName();

    /***
     * get user's information by realName
     * @param realName user realName
     * @return user's information
     */
    String getUserInfo(String realName);

}
