package datastruct.linkedlist;

import bean.ListNode;

/**
 * 在 O(1) 的时间删除链表结点：
 *     若顺序遍历链表的话，时间复杂度为 O(n)；而删除结点，可以找到结点的下个结点，完成指针转移后再将下个结点删除。
 *     注意最后一个结点，以及头结点等。
 *     时间复杂度为 [(n-1)O(1) + O(n)] / n, 即 O(1)
 *
 * @Writer ArtisanLS
 * @Date 2023/2/8
 */
public class DeleteNodeInO1 {
    public static void main(String[] args) {
        // 链表为空
        ListNode<Integer> head = null;
        ListNode.print(deleteNodeInO1(head, head)); // List is null!!!

        // 只有一个结点
        head = new ListNode<>(0);
        ListNode<Integer> node = head;
        ListNode.print(deleteNodeInO1(head, head)); // List is null!!!
        ListNode.print(head); // 0->null  对象是否放到两个地方：栈、堆，放到栈上快速销毁？
        // https://www.zhihu.com/question/583527575/answer/2890127282?utm_id=0
        // https://baijiahao.baidu.com/s?id=1678511797907822583&wfr=spider&for=pc

        // 多个结点
        for (int i = 1; i < 5; i++) {
            ListNode<Integer> iNode = new ListNode<>(i);
            node.next = iNode;
            node = node.next;
        }
        ListNode<Integer> tail = new ListNode<>(5);
        node.next = tail;

        ListNode.print(head); // 0->1->2->3->4->5->null
        node.print(node); // 4 5
        // 多个结点，删除尾结点
        head.print(deleteNodeInO1(head, tail)); // 0 1 2 3 4
        // 多个结点，删除的不是尾结点
        head.print(deleteNodeInO1(head, head.next)); // 0 2 3 4
    }

    /**
     * 思路：把下一个结点的内容赋值给需要删除的结点
     *
     * @param headNode 链表头结点
     * @param deleteNode 需要删除的结点
     */
    public static ListNode<Integer> deleteNodeInO1(ListNode<Integer> headNode, ListNode<Integer> deleteNode) {
        // 如果链表为空，或者要删除的节点为空，返回 null
        if (headNode == null | deleteNode == null) return null;

        if (deleteNode.next != null) { // 删除的不是尾结点
            ListNode<Integer> nextNode = deleteNode.next; // 缓存下一个结点
            deleteNode.data = nextNode.data; // 将要删除的结点值指向下一个结点
            deleteNode.next = nextNode.next; // 将要删除的结点指针指向下一个结点
            nextNode = null; // 将缓存清空
        } else { // 删除尾结点
            if (headNode == deleteNode) { // 只有一个结点，尾结点也是头结点
                // 加上这句，返回的 22行 head变量 才是 null，问题：自己本身是成员属性初始化方式
                // headNode.data = null;
                headNode = null;
            } else { // 多个结点
                ListNode<Integer> node = headNode;
                while (node.next != deleteNode) {
                    node = node.next; // headNode 指向原来的地址，node 指向新地址
                }
                node.next = null;
            }
        }
        return headNode;
    }
}
