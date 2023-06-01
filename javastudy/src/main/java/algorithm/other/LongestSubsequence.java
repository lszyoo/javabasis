package algorithm.other;

import java.util.Arrays;

/**
 * 子序列：
 *    将给定序列中零个或多个元素(如字符)去掉后所得结果。
 *    例如：给定序列【A,B,C,D,E,F,G,H】
 *    子序列：A,C,E,F
 *    同理，【A,H】,【C,D,E】等都是子序列
 *
 * @Writer ArtisanLS
 * @Date 2023/2/21
 */
public class LongestSubsequence {
    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("hello world", "h words")); // 6
        System.out.println(longestRiseSubsequence(new int[]{1, 8, 2, 9, 3, 6})); // 4
    }

    /**
     * 最长公共子序列
     * 参考链接：https://blog.csdn.net/qq_58668057/article/details/123774788
     */
    public static int longestCommonSubsequence(String strA, String strB) {
        int strALen = strA.length();
        int strBLen = strB.length();
        int[][] dp = new int[strALen + 1][strBLen + 1];
        // StringBuffer sb = new StringBuffer();

        for (int i = 0; i < strALen; i++) {
            for (int j = 0; j < strBLen; j++) {
                if (strA.charAt(i) == strB.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    // sb.append(strA.charAt(i));
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        // System.out.println(sb);
        return dp[strALen][strBLen];
    }

    /**
     * 最长上升子序列
     * 参考链接：https://zhuanlan.zhihu.com/p/92604300
     */
    public static int longestRiseSubsequence(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        int[] dp = new int[arr.length];
        int res = 0;
        Arrays.fill(dp, 1);

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }

        return res;
    }
}
