package datastruct.linkedlist;

import bean.ListNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 环形链表：
 *    判断链表是否有环
 *
 * @Writer ArtisanLS
 * @Date 2023/2/8
 */
public class DetectCycleList {
    public static void main(String[] args) {
        ListNode<Integer> head = new ListNode<>(1);
        ListNode<Integer> lastNode = new ListNode<>(5);
        head.next = new ListNode<>(2);
        head.next.next = new ListNode<>(3);
        head.next.next.next = new ListNode<>(4);
        head.next.next.next.next = lastNode;
        lastNode.next = head.next;

        System.out.println(detectCycleList1(head).data); // 2
        System.out.println(detectCycleList2(head)); // 4
        System.out.println(detectCycleList3(head)); // true
        System.out.println(detectCycleList4(head).data); // 2
        System.out.println(detectCycleList5(head)); // 4
    }

    /**
     * 集合法
     */
    // 是否有环、入环点
    public static ListNode<Integer> detectCycleList1(ListNode<Integer> head) {
        Set<ListNode<Integer>> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)) {
                return head;
            }
            head = head.next;
        }
        return null;
    }

    // 求环的长度
    public static int detectCycleList2(ListNode<Integer> head) {
        int count = 0;
        Map<ListNode<Integer>, Integer> map = new HashMap<>();
        while (head != null) {
            if (!map.containsKey(head)) {
                map.put(head, 1);
            } else {
                map.put(head, map.get(head) + 1);
            }

            if (map.get(head) == 2) count++;
            if (map.get(head) == 3) break;

            head = head.next;
        }

        return count;
    }

    /**
     * 快慢指针:
     * 类似于数学中的追及问题，设置一个快指针，步长为2，设置一个慢指针，步长为1。
     * 如果不存在环，则快慢指针不会相遇。如果存在环，则快慢指针会相遇。
     * 时间复杂度为O(n)，
     * 空间复杂度为O(1)。
     * 此为最优方法
     */
    // 是否有环
    public static boolean detectCycleList3(ListNode<Integer> head) {
        ListNode<Integer> fast = head;
        ListNode<Integer> slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    // 入环点：a + n(b + c) + b = 2(a + b) => a = c + (n - 1)(b + c)
    // https://leetcode.cn/problems/linked-list-cycle-ii/solution/huan-xing-lian-biao-ii-by-leetcode-solution/
    public static ListNode<Integer> detectCycleList4(ListNode<Integer> head) {
        ListNode<Integer> fast = head;
        ListNode<Integer> slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                while (head != slow) {
                    head = head.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }

    // 环的长度
    public static int detectCycleList5(ListNode<Integer> head) {
        ListNode<Integer> fast = head;
        ListNode<Integer> slow = head;
        boolean flag = false; // 指针是否相遇
        int count = 0; // 环的长度

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (flag) count++;

            if (fast == slow) {
                if (flag) { // 第二次相遇
                    return count;
                } else { // 第一次相遇
                    flag = true;
                }
            }
        }

        return count;
    }
}