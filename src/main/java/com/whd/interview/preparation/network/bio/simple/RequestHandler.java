package com.whd.interview.preparation.network.bio.simple;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/24 1:40
 * @apiNote Describe the function of this class in one sentence
 **/
public class RequestHandler {

    /***
     * 响应的逻辑
     * @param request
     * @return
     */
    public String handler(String request){
        return "From BIOServer Hello " + request + "\n";
    }
}
