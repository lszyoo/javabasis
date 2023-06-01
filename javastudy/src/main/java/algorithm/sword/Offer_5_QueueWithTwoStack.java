package algorithm.sword;

import java.util.Stack;

/**
 * 用两个栈实现队列：
 *    用两个栈来实现一个队列，完成队列的 Push 和 Pop 操作。
 * <p>
 *     栈 stack1 用来实现 push 操作，stack2 空的前提下才能进行入栈，否则影响后续进出队列的顺序。
 *     栈 stack2 用来实现 pop 操作，将 push 进去的 stack1 内的元素存入 stack2 中，再进行出栈，就是队列的出列顺序。
 * </p>
 *
 * @Writer ArtisanLS
 * @Date 2023/1/5
 */
public class Offer_5_QueueWithTwoStack {
    public static void main(String[] args) {
        QueueWithTwoStack<Integer> queue = new QueueWithTwoStack<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.pop()); // 1
        queue.push(4);
        System.out.println(queue.pop()); // 2
        System.out.println(queue.pop()); // 3
    }

    private static class QueueWithTwoStack<E> {
        Stack<E> stack1 = new Stack<>();
        Stack<E> stack2 = new Stack<>();

        // 进队列
        public void push(E data) {
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
            stack1.push(data);
        }

        // 出队列
        public E pop() {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }
    }
}
