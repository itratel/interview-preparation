package com.whd.interview.preparation.leetcode.algorithm.integerpalindrome;
//判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
//
// 示例 1: 
//
// 输入: 121
//输出: true
// 
//
// 示例 2: 
//
// 输入: -121
//输出: false
//解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
// 
//
// 示例 3: 
//
// 输入: 10
//输出: false
//解释: 从右向左读, 为 01 。因此它不是一个回文数。
// 
//
// 进阶: 
//
// 你能不将整数转为字符串来解决这个问题吗？ 
// Related Topics 数学


import org.springframework.util.Assert;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public boolean isPalindrome(int x) {
        //思考：这里大家可以思考一下，为什么末尾为 0 就可以直接返回 false
        if (x < 0 || (x % 10 == 0 && x != 0)) {return false;}
        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        return x == revertedNumber || x == revertedNumber / 10;
    }

    public boolean isPalindrome1(int x) {
        //边界判断
        if (x < 0) {return false;}
        int div = 1;
        //
        while (x / div >= 10)  {div *= 10;}
        while (x > 0) {
            int left = x / div;
            int right = x % 10;
            if (left != right) {return false;}
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }

    public boolean isPalindrome2(int x) {
        String reversedStr = (new StringBuilder(x + "")).reverse().toString();
        return (x + "").equals(reversedStr);
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.isTrue(solution.isPalindrome(1321), "该整数不是回文数");
//        Assert.isTrue(solution.isPalindrome1(1321), "该整数不是回文数");
//        Assert.isTrue(solution.isPalindrome2(1321), "该整数不是回文数");
    }
}
//leetcode submit region end(Prohibit modification and deletion)
