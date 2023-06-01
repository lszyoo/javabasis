package algorithm.sword;

/**
 * 替换空格：
 *      请实现一个函数，将一个字符串中的空格替换成 %20
 *      例如：当字符串为 We Are Happy，则经过替换之后的字符串为 We%20Are%20Happy
 *
 * @Writer ArtisanLS
 * @Date 2023/1/4
 */
public class Offer_2_ReplaceBlank {
    public static void main(String[] args) {
        String str = replaceBlank("Hello World ");
        System.out.println(str); // Hello%20World%20
    }

    public static String replaceBlank(String str) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char si = str.charAt(i);
            if (si == ' ') {
                buffer.append("%20");
            } else {
                buffer.append(si);
            }
        }
        return buffer.toString();
    }
}
