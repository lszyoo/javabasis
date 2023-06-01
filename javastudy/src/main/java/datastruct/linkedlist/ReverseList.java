package datastruct.linkedlist;

import bean.ListNode;

import java.util.Stack;

/**
 * 反转链表
 *
 * @Writer ArtisanLS
 * @Date 2023/2/9
 */
public class ReverseList {
    public static void main(String[] args) {
        ListNode<Integer> head1 = UtilsInstance.create();
        ListNode<Integer> head2 = UtilsInstance.create();
        ListNode<Integer> head3 = UtilsInstance.create();

        head1.print(reverseList1(head1)); // 7->6->5->4->3->2->1->null
        head2.print(reverseList2(head2)); // 7->6->5->4->3->2->1->null
        head3.print(reverseList3(head3)); // 7->6->5->4->3->2->1->null
    }

    /**
     * 方法一：利用栈
     *
     * @param head
     * @return
     */
    public static ListNode<Integer> reverseList1(ListNode<Integer> head) {
        if (head == null) return null;
        Stack<ListNode<Integer>> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        ListNode<Integer> node = stack.pop();
        ListNode<Integer> nodeTemp = node;
        while (!stack.isEmpty()) {
            node.next = stack.pop();
            node = node.next;
        }
        node.next = null;

        return nodeTemp;
    }

    /**
     * 方法二：遍历
     *        1->2->3->4->null
     *
     * @param head
     * @return
     */
    public static ListNode<Integer> reverseList2(ListNode<Integer> head) {
        ListNode<Integer> reverseNode = null;
        ListNode<Integer> tempNode;
        while (head != null) {
            tempNode = head.next;
            head.next = reverseNode;
            reverseNode = head;
            head = tempNode;
        }
        return reverseNode;
    }

    /**
     * 方法三：递归
     *
     * @param head
     * @return
     */
    public static ListNode<Integer> reverseList3(ListNode<Integer> head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode<Integer> node = head.next;
        // 从当前节点的下一个结点开始递归调用
        ListNode<Integer> reverseNode = reverseList3(node);
        // reverse 是反转之后的链表，因为函数 reverseList3 表示的是对链表的反转，所以反转完之后 next 肯定是链表 reverse 的尾结点，
        // 然后我们再把当前节点head挂到next节点的后面就完成了链表的反转。
        node.next = head;
        // 这里head相当于变成了尾结点，尾结点都是为空的，否则会发生闭环
        head.next = null;
        return reverseNode;
    }
}
