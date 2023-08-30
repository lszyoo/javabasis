package algorithm.other;

import java.util.*;

/**
 * 区间合并：
 * 例子：
 *    输入：[{"start": 1, "end": 4}, {"start": 2, "end": 5}, {"start": 7, "end": 8}, {"start": 9, "end": 12}, {"start": 10, "end": 13}, {"start": 11, "end": 14}]
 *    输出：[{"start": 1, "end": 5}, {"start": 7, "end": 8}, {"start": 9, "end": 14}]
 *
 * @Writer ArtisanLS
 * @Date 2023/6/14
 */
public class IntervalMerge {
    public static void main(String[] args) {
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("start", 1);
        map1.put("end", 4);
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("start", 2);
        map2.put("end", 5);
        Map<String, Integer> map3 = new HashMap<>();
        map3.put("start", 7);
        map3.put("end", 8);
        Map<String, Integer> map4 = new HashMap<>();
        map4.put("start", 9);
        map4.put("end", 12);
        Map<String, Integer> map5 = new HashMap<>();
        map5.put("start", 10);
        map5.put("end", 13);
        Map<String, Integer> map6 = new HashMap<>();
        map6.put("start", 11);
        map6.put("end", 14);
        List<Map<String, Integer>> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);
        list.add(map4);
        list.add(map6);
        list.add(map3);
        list.add(map5);
        System.out.println(list); // [{start=1, end=4}, {start=2, end=5}, {start=9, end=12}, {start=11, end=14}, {start=7, end=8}, {start=10, end=13}]
        System.out.println(intervalMerge(list)); // [{start=1, end=5}, {start=7, end=8}, {start=9, end=14}]
    }

    public static List<Map<String, Integer>> intervalMerge(List<Map<String, Integer>> list) {
        Collections.sort(list, new Comparator<Map<String, Integer>>() {
            @Override
            public int compare(Map<String, Integer> o1, Map<String, Integer> o2) {
                return o1.get("start").compareTo(o2.get("start"));
            }
        });
        // System.out.println(list); // [{start=1, end=4}, {start=2, end=5}, {start=7, end=8}, {start=9, end=12}, {start=10, end=13}, {start=11, end=14}]

        List<Map<String, Integer>> resList = new ArrayList<>();
        for (Map<String, Integer> mapE : list) { // 遍历所有元素
            Map<String, Integer> resMap = new HashMap<>();
            int start = mapE.get("start");
            int end = mapE.get("end");
            if (resList.size() == 0 || start > resList.get(resList.size() - 1).get("end")) { // 遍历的原 list 元素比 resList 最右边元素比对
                resMap.put("start", start);
                resMap.put("end", end);
                resList.add(resMap);
            } else { // 重新赋值
                resList.get(resList.size() - 1).put("end", Math.max(end, resList.get(resList.size() - 1).get("end")));
            }
        }

        return resList;
    }
}
