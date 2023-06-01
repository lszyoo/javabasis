package bean;

/**
 * 链表节点
 *
 * @Writer ArtisanLS
 * @Date 2023/1/4
 */
public class ListNode<T> {
    public T data;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(T data) {
        this.data = data;
    }

    public static void print(ListNode<Integer> node) {
        if (node == null) {
            System.out.println("List is null!!!");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (node != null) {
            buffer.append(node.data);
            buffer.append("->");
            node = node.next;
        }
        buffer.append("null");
        System.out.println(buffer.toString());
    }
}
