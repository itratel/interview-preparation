package com.whd.interview.preparation.java8.classlibapi.encode;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author whd.java@gmail.com
 * @date 2018/11/2 14:13
 * @apiNote TestBase64
 * @see Base64
 */
public class TestBase64 {

    public static void main(String[] args) {

        final String str = "Base64 finally in java 8";
        final String encode = Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
        System.out.println("encode = " + encode);

        final String decode = new String(Base64.getDecoder().decode(encode), StandardCharsets.UTF_8);
        System.out.println("decode = "+decode);
    }
}
