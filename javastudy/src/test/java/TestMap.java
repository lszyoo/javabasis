import java.util.*;

/**
 * @Writer ArtisanLS
 * @Date 2023/5/24
 */
public class TestMap {
    public static void main(String[] args) {
        System.out.println(test("1,A;1,B;1,A;2,B;2,A;2,C;2,D"));
    }

    public static List<Map.Entry<String, Integer>> test(String str) {
        Map<String, Integer> map = new HashMap<>();

        // 分隔去重字符串
        Set<String> set = new HashSet<>();
        String[] strings = str.split(";");
        for (String string : strings) {
            set.add(string);
        }

        for (String s : set) {
            String[] strs = s.split(",");
            String user = strs[0];
            if (map.containsKey(user)) {
                map.put(user, map.get(user) + 1);
            } else {
                map.put(user, 1);
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        Collections.reverse(list);
        return list;
    }
}
