package algorithm.other;

/**
 * 阿里飞猪：
 *    判断字符串 a 是否包含子字符串 b
 *
 * @Writer ArtisanLS
 * @Date 2023/1/5
 */
public class StrContains {
    public static void main(String[] args) {
        System.out.println(strContains("Hello, Jenny", "lo"));
    }

    public static boolean strContains(String a, String b) {
        if (a == null || b == null) return false;

        if (a.equals("") || b.equals("")) return false;

        int len = 0; // 记录 b 在 a 中相同连续字符数
        for (int i = 0; i < a.length(); i++) {
            while (a.charAt(i) == b.charAt(len)) {
                len++;
                if (len == b.length()) return true;
                i++;
            }
            i = i - len; // i 重置到遍历点
            len = 0;
        }
        return false;
    }
}
