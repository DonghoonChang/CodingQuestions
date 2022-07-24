package LeetCode.Easy;

public class BinarySearch {
    /*
        Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Search.
        Memory Usage: 43.1 MB, less than 86.24% of Java online submissions for Binary Search.
     */
    public int search(int[] nums, int target) {
        int min = 0;
        int max = nums.length - 1;
        int index;

        while(true){
            index = (min + max) / 2;
            int num = nums[index];
            if (num == target){
                return index;
            }

            if(min >= max){
                return -1;
            }

            if(num > target){
                max = index - 1;
                continue;
            }

            if(num < target){
                min = index + 1;
                continue;
            }
        }
    }

    public static void main(String[] args){
        BinarySearch bs = new BinarySearch();
        int result = bs.search(new int[]{2, 5}, 0);

        System.out.println(result);
    }
}
