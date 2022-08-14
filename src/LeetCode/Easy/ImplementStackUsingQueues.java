package LeetCode.Easy;

import java.util.LinkedList;
import java.util.Queue;

/*
Time: 13m
Runtime: 1 ms, faster than 32.03% of Java online submissions for Implement Stack using Queues.
Memory Usage: 41.8 MB, less than 46.96% of Java online submissions for Implement Stack using Queues.
 */
public class ImplementStackUsingQueues {
        Queue<Integer> q = new LinkedList<>();
        public ImplementStackUsingQueues() {
        }

        public void push(int x) {
            q.add(x);

            for(int i = 0; i < q.size()-1; i++){
                q.add(q.poll());
            }
        }

        public int pop() {
            return q.poll();
        }

        public int top() {
            return q.peek();
        }

        public boolean empty() {
            return q.size() == 0;
        }
}
