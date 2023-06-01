package datastruct.linkedlist;

import bean.ListNode;

/**
 * 合并两个有序的链表
 *
 * @Writer ArtisanLS
 * @Date 2023/2/19
 */
public class MergeOrderList {
    public static void main(String[] args) {
        ListNode<Integer> node1 = new ListNode<>(1);
        node1.next = new ListNode<>(3);
        node1.next.next = new ListNode<>(5);

        ListNode<Integer> node2 = new ListNode<>(2);
        node2.next = new ListNode<>(4);
        node2.next.next = new ListNode<>(6);

        ListNode.print(mergeOrderList1(node1, node2)); // 1->2->3->4->5->6->null
        ListNode.print(mergeOrderList2(node1, node2)); // 1->2->3->4->5->6->null
    }

    // 优解：循环
    public static ListNode<Integer> mergeOrderList1(ListNode<Integer> node1, ListNode<Integer> node2) {
        ListNode<Integer> resNode = new ListNode<>(); // 存储返回 node
        ListNode tempNode = resNode;
        while (node1 != null && node2 != null) {
            if (node1.data < node2.data) {
                tempNode.next = node1;
                node1 = node1.next; // node1 向后移动
            } else {
                tempNode.next = node2;
                node2 = node2.next; // node2 向后移动
            }
            tempNode = tempNode.next; // tempNode 向后移动
        }
        if (node1 == null) tempNode.next = node2;
        if (node2 == null) tempNode.next = node1;

        return resNode.next; // resNode 起始值是 null
    }

    // 递归
    public static ListNode<Integer> mergeOrderList2(ListNode<Integer> node1, ListNode<Integer> node2) {
        if (node1 == null) return node2;
        if (node2 == null) return node1;
        ListNode<Integer> head;
        if (node1.data < node2.data) {
            head = node1;
            head.next = mergeOrderList2(node1.next, node2);
        } else {
            head = node2;
            head.next = mergeOrderList2(node1, node2.next);
        }
        return head;
    }
}
