package datastruct.linkedlist;

import bean.ListNode;

/**
 * @Writer ArtisanLS
 * @Date 2023/2/9
 */
public class UtilsInstance {
    public static ListNode<Integer> create() {
        ListNode<Integer> head = new ListNode<>(1);
        ListNode<Integer> node = head;
        for (int i = 2; i < 8; i++) {
            ListNode<Integer> iNode = new ListNode<>(i);
            node.next = iNode; // 将 iNode 挂到 node 上
            node = node.next; // node 的 指针向下移动一个
        }
        return head;
    }
}
