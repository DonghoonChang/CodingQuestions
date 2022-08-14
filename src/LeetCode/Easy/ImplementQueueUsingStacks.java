package LeetCode.Easy;

import java.util.Stack;

/*
Time: 4m
Runtime: 1 ms, faster than 71.15% of Java online submissions for Implement Queue using Stacks.
Memory Usage: 41.4 MB, less than 77.55% of Java online submissions for Implement Queue using Stacks.
 */
public class ImplementQueueUsingStacks {
    private Stack<Integer> stackIn = new Stack<>();
    private Stack<Integer> stackOut = new Stack<>();

    public ImplementQueueUsingStacks() {

    }

    public void push(int x) {
        stackIn.push(x);
    }

    public int pop() {
        if(!stackOut.isEmpty()){
            return stackOut.pop();
        }

        while(!stackIn.isEmpty()){
            stackOut.push(stackIn.pop());
        }

        return stackOut.pop();
    }

    public int peek() {
        if(!stackOut.isEmpty()){
            return stackOut.peek();
        }

        while(!stackIn.isEmpty()){
            stackOut.push(stackIn.pop());
        }

        return stackOut.peek();
    }

    public boolean empty() {
        return stackIn.empty() && stackOut.empty();
    }
}
