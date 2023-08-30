package algorithm.other;

import java.util.HashMap;
import java.util.Map;

/**
 * 子串：
 *    给定序列中零个或多个连续的元素(如字符)组成的子序列。
 *    例如：给定序列【A,B,C,D,E,F,G,H】
 *    子串：C,D,E,F
 *
 * @Writer ArtisanLS
 * @Date 2023/2/21
 */
public class LongestSubstring {
    public static void main(String[] args) {
        System.out.println(longestCommonSubstring("hello world", "ll o sky")); // 2
        System.out.println(longestPalindrome1("abcdcbm")); // bcdcb
        System.out.println(longestPalindrome2("abcdcbm")); // bcdcb
        System.out.println(longestNoRepetition("abcdcbm")); // 4
    }

    /**
     * 最长公共子串
     * 引申：判断字符串 A 包含字符串 B，res = strB.length()，则包含
     * 参考链接：https://blog.csdn.net/qq_58668057/article/details/123774788
     */
    public static int longestCommonSubstring(String strA, String strB) {
        int strALen = strA.length();
        int strBLen = strB.length();
        int res = 0;
        int[][] dp = new int[strALen + 1][strBLen + 1];

        for (int i = 0; i < strALen; i++) {
            for (int j = 0; j < strBLen; j++) {
                if (strA.charAt(i) == strB.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    res = Math.max(res, dp[i + 1][j + 1]);
                } else {
                    dp[i + 1][j + 1] = 0; // 类比最长公共子序列，这里相当于重置
                }
            }
        }
        return res;
    }

    /**
     * 最长回文子串
     * 参考链接：https://blog.csdn.net/tiankong_12345/article/details/102018257
     */
    public static String longestPalindrome1(String str) {
        int strLen = str.length();
        // 存储是否是回文子串
        boolean[][] dp = new boolean[strLen][strLen];
        String res = "";
        for (int len = 1; len <= strLen; len++) { // 遍历所有长度，l 为字符串长度
            for (int start = 0; start + len - 1 < strLen; start++) { // start 是回文子串起点
                int end = start + len - 1; // 回文子串截止点
                dp[start][end] = (len == 1 || len == 2 || dp[start + 1][end - 1]) && str.charAt(start) == str.charAt(end);
                if (dp[start][end] && len > res.length()) { // 字符串长度大于子串长度
                    res = str.substring(start, end + 1);
                }
            }
        }
        return res;
    }

    // https://blog.csdn.net/a1111116/article/details/115176747
    public static String longestPalindrome2(String str) {
        int strLen = str.length();
        // 存储是否是回文子串
        boolean[][] dp = new boolean[strLen][strLen];
        // 单个字符一定是回文
//        for (int i = 0; i < strLen; i++) {
//            dp[i][i] = true;
//        }
        // 最大长度
        int maxLen = 0;
        int start = 0;
        // dp[i][j] 表示字符串第 i 个字符到第 j 个字符是否是回文串
        for (int j = 1; j < strLen; j++) {
            for (int i = 0; i < j; i++) {
                dp[i][j] = (j - i < 2 || dp[i + 1][j - 1]) && str.charAt(i) == str.charAt(j);
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    start = i;
                }
            }
        }
        return str.substring(start, start + maxLen);
    }

    /**
     * 最长无重复字符子串
     * 滑动窗口
     * 参考链接：https://blog.csdn.net/hwruirui/article/details/123622937
     */
    public static int longestNoRepetition(String str) {
        int strLen = str.length();
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int start = 0, end = 0; end < strLen; end++) {
            char c = str.charAt(end);
            if (map.containsKey(c)) {
                start = Math.max(map.get(c), start);
            }
            res = Math.max(res, end - start + 1);
            map.put(str.charAt(end), end + 1);
        }
        return res;
    }
}
