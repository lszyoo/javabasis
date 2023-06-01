package datastruct.linkedlist;

import bean.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 从尾打印到头(链表的知识，重点)：
 *      输入一个链表，从尾到头打印链表每个节点的值。
 *      用到栈的知识点。后进先出。
 *
 * @Writer ArtisanLS
 * @Date 2023/1/4
 */
public class PrintFromTailToHead {
    public static void main(String[] args) {
        ListNode<Integer> node1 = new ListNode<>(1);
        ListNode<Integer> node2 = new ListNode<>(5);
        ListNode<Integer> node3 = new ListNode<>(2);
        ListNode<Integer> node4 = new ListNode<>(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        List<Integer> list = printFromTailToHead(node1);
        System.out.println(list); // [6, 2, 5, 1]
    }

    public static List<Integer> printFromTailToHead(ListNode<Integer> node) {
        Stack<Integer> stack = new Stack<>();
        while (node != null) {
            stack.push(node.data);
            node = node.next;
        }
        List<Integer> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        return list;
    }
}

