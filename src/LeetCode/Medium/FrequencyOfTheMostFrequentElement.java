package LeetCode.Medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

    /*
        Runtime: 68 ms, faster than 15.65% of Java online submissions for Frequency of the Most Frequent Element.
        Memory Usage: 117.8 MB, less than 5.02% of Java online submissions for Frequency of the Most Frequent Element.
    */
public class FrequencyOfTheMostFrequentElement {
    private static int max = 0;

    public int maxFrequency(int[] nums, int k) {
        max = 0;
        Integer[] sorted = Arrays.stream(nums).sorted().boxed().toArray(Integer[]::new);

        int l = 0, r = 0;
        long total = 0;
        while (r < sorted.length) {

            total += sorted[r];

            if (total + k > (r - l + 1) * sorted[r]) {
                r++;
                max = Math.max(max, r - l);
                continue;
            }

            total -= sorted[r];
            total -= sorted[l++];
        }

        return max;
    }


    /*
    Runtime: 2519 ms, faster than 5.03% of Java online submissions for Frequency of the Most Frequent Element.
    Memory Usage: 302.7 MB, less than 5.02% of Java online submissions for Frequency of the Most Frequent Element.
     */
    private static int globalMax;

    public int maxFrequency2(int[] nums, int k) {
        globalMax = 0;
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            if (freqMap.containsKey(num)) {
                freqMap.put(num, freqMap.get(num) + 1);
            } else {
                freqMap.put(num, 1);
            }
        }

        Integer[] descending = freqMap.keySet().stream().sorted(Comparator.reverseOrder()).toArray(Integer[]::new);
        for (int i = 0; i < descending.length; i++) {
            int count = k;
            int initialNum = descending[i];
            int freq = freqMap.get(initialNum);
            int nextIndex = 1;

            while (count >= 0 && i + nextIndex < descending.length) {
                int nextNum = descending[i + nextIndex];
                int nextFreq = freqMap.get(nextNum);
                int gap = initialNum - nextNum;
                int max = gap * nextFreq;

                nextIndex++;

                if (count >= max) {
                    count -= max;
                    freq += nextFreq;
                } else if (count >= gap) {
                    int added = count / gap;
                    count -= added * gap;
                    freq += added;
                } else {
                    break;
                }
            }

            globalMax = Math.max(globalMax, freq);
        }

        return globalMax;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 2};
        int result = (new FrequencyOfTheMostFrequentElement()).maxFrequency(arr, 4);

        System.out.println(result);
    }
}
