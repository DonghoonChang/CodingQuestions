package LeetCode.Medium;


/*
Runtime: 0 ms, faster than 100.00% of Java online submissions for Search in Rotated Sorted Array.
Memory Usage: 41.7 MB, less than 94.26% of Java online submissions for Search in Rotated Sorted Array.
Time taken; 1hr
 */

public class SearchinRotatedSortedArray {
    public int search(int[] nums, int target) {
        boolean shifted = nums[0] > nums[nums.length - 1];
        if(!shifted){
            return bs(nums, 0, nums.length - 1, target);
        }

        int pivot = findPivot(nums, 0, nums.length - 1);
        int[] newNums = new int[nums.length];

        for(int i = 0; i < nums.length; i++){
            newNums[i] = nums[(i + pivot) % nums.length];
        }

        int result = bs(newNums, 0, nums.length - 1, target);

        return result == -1 ? result : (result + pivot) % nums.length;
    }

    private int findPivot(int[] nums, int start, int end){
        if(start + 1 == end){
            return end; // returns the index of the smallest number
        }
        int middle = (start + end) / 2;
        int startValue = nums[start];
        int middleValue = nums[middle];

        if(startValue > middleValue){
            return findPivot(nums, start, middle);
        }

        return findPivot(nums, middle, end);
    }

    private int bs(int[] nums, int start, int end, int target){
        if(start == end && nums[start] != target){
            return -1;
        }

        if(start > end){
            return -1;
        }

        int m = (start + end) / 2;
        int middle = nums[m];

        if(target == middle){
            return m;
        }

        if(target < middle){
            return bs(nums, start, m - 1, target);
        }

        return bs(nums, m + 1, end, target);
    }

    public static void main(String[] args){
        int[] sequence = new int[]{1, 3};
        int result = (new SearchinRotatedSortedArray()).search(sequence, 0);

        System.out.println(result);
    }
}
