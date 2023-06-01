package datastruct.linkedlist;

import bean.ListNode;

/**
 * 链表中倒数第 k 个结点
 *    输入一个链表，输出该链表中倒数第k个结点。
 * <p>
 *    方法一：
 *         技巧性解法，利用双指针法。
 *         设置快指针 forward 和慢指针 backward，让其都指向 head 头指针。
 *         先让 forward 往前跳 k 步，如果 forward == null,说明 链表长度不够 k 个，返回 null。
 *         然后前指针、后指针同步走，当 forward 走到 null 时，返回慢指针即倒数第 k 个结点。
 *         总长：x = k + (x - k)，forward 走 k 步，forward 和 backward 一起走 x - k，forward 到链表尾部，backward 到链表倒数第 k 个结点
 *    方法二：
 *         一般方式求解，求出结点个数，再根据k的值确定向后跳x步
 * </p>
 *
 * @Writer ArtisanLS
 * @Date 2023/2/17
 */
public class FindKthToTail {
    public static void main(String[] args) {
        ListNode<Integer> head1 = UtilsInstance.create();
        ListNode<Integer> head2 = UtilsInstance.create();
        head1.print(findKthToTail1(head1, 3)); // 5->6->7->null
        head2.print(findKthToTail2(head2, 3)); // 5->6->7->null
    }

    public static ListNode<Integer> findKthToTail1(ListNode<Integer> head, int k) {
        if (k < 1) return null;

        ListNode<Integer> forward = head;
        for (int i = 0; i < k; i++) { // 先让 forward 往前走 k 步
            if (forward == null) return null; // 链表长度不够 k 步，返回 null
            forward = forward.next;
        }
        ListNode<Integer> backward = head;
        while (forward != null) {
            forward = forward.next;
            backward = backward.next;
        }
        return backward;
    }

    public static ListNode<Integer> findKthToTail2(ListNode<Integer> head, int k) {
        int size = 0;
        // 求出链表结点个数
        for (ListNode<Integer> cur = head; cur != null; cur = cur.next) size++;
        int step = size - k; // 求出从头结点向后跳 step 步
        if (step > 0) {
            for (int i = 0; i < step; i++) head = head.next;
        } else {
            head = null;
        }
        return head;
    }
}