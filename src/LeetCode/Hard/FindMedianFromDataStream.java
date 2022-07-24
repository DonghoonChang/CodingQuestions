package LeetCode.Hard;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class FindMedianFromDataStream {
    /*
        Runtime: 145 ms, faster than 84.66% of Java online submissions for Find Median from Data Stream.
        Memory Usage: 59.7 MB, less than 99.89% of Java online submissions for Find Median from Data Stream.

        Runtime: 190 ms, faster than 58.91% of Java online submissions for Find Median from Data Stream.
        Memory Usage: 128.1 MB, less than 6.71% of Java online submissions for Find Median from Data Stream.

        Runtime: 236 ms, faster than 32.63% of Java online submissions for Find Median from Data Stream.
        Memory Usage: 140 MB, less than 5.00% of Java online submissions for Find Median from Data Stream.

        Runtime: 328 ms, faster than 9.82% of Java online submissions for Find Median from Data Stream.
        Memory Usage: 139.7 MB, less than 5.00% of Java online submissions for Find Median from Data Stream.
     */
    public static double solve(int[] stream){
        Queue<Integer> small = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<Integer> large = new PriorityQueue<>();

        for(int num: stream){
            int sizeS = small.size();
            int sizeL = large.size();

            if(sizeS == sizeL + 1){
                large.add(num);
            } else {
                small.add(num);
            }

            if(!areHeapsInOrder(small, large)){
                orderHeaps(small, large);
            }
        }

        return getMedian(small, large);
    }

    private static boolean areHeapsInOrder(Queue<Integer> small, Queue<Integer> large){
        if(small.size() == 0){
            return true;
        }

        int smallPeek = small.peek();
        int largePeek = large.size() == 0 ? Integer.MAX_VALUE : large.peek();

        return smallPeek < largePeek;
    }

    private static void orderHeaps(Queue<Integer> small, Queue<Integer> large){
        int maxOfSmall = small.poll();
        int minOfLarge = large.poll();

        small.add(minOfLarge);
        large.add(maxOfSmall);
    }

    private static double getMedian(Queue<Integer> small, Queue<Integer> large){
        int sizeS = small.size();
        int sizeL = large.size();

        if(sizeS == sizeL){
            return (double) (small.peek() + large.peek()) / 2.0;
        }

        if(sizeS == sizeL + 1){
            return small.peek();
        }

        return Integer.MIN_VALUE;
    }

    public static void main(String[] args){
        FindMedianFromDataStream.solve(new int[]{1, 2, 3});
    }
}
