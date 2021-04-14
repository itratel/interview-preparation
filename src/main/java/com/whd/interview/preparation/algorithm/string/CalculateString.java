package com.whd.interview.preparation.algorithm.string;

/***
 * <p>
 *    DemoClass
 * </p>
 * @author whd.java@gmail.com
 * @date 2021/4/14 21:29
 * @since 1.0.0
 */
public class CalculateString {

    public static void main(String[] args) {
        String s2 = getCompressStr("hhdhhhhssadddasdss");
        System.out.println("s2 = " + s2);
    }


    public static String getCompressStr(String str) {
        if(str == null || "".equals(str)){
            return null;
        }
        if(str.length() > 100) {
            return "字符串长度不能超过100";
        }
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            boolean flag = (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
            if (!flag) {
                return "字符串中包含有非英文字母的字符";
            }
        }
        int length = str.length();
        String[] array = str.split("");
        StringBuilder s2 = new StringBuilder();
        int count = 1;
        for (int i = 0; i < length; i++) {
            String first = array[i];
            String next = (i == length - 1) ? "": array[i + 1];
            if (first.equals(next)) {
                count++;
            } else {
                s2.append(first).append(count);
                count = 1;
            }
        }
        return s2.toString().replaceAll("1","");
    }


}
