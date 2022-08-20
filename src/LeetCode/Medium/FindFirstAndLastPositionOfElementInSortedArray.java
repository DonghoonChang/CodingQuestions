package LeetCode.Medium;

/*
Time: 18m
Runtime: 0 ms, faster than 100.00% of Java online submissions for Find First and Last Position of Element in Sorted Array.
Memory Usage: 47.7 MB, less than 15.92% of Java online submissions for Find First and Last Position of Element in Sorted Array.
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int first = find(nums, target, 0, nums.length - 1);

        if(first != -1){
            int left = expandLeft(nums, target, 0, first);
            int right = expandRight(nums, target, first, nums.length - 1);

            return new int[]{left, right};
        }

        return new int[]{-1, -1};
    }

    private int find(int[] nums, int target, int left, int right){
        if(left > right){
            return -1;
        }

        int middle = (left + right) / 2;
        if(nums[middle] == target){
            return middle;
        }

        if(nums[middle] > target){
            return find(nums, target, left, middle - 1);
        } else{
            return find(nums, target, middle + 1, right);
        }
    }

    private int expandRight(int[] nums, int target, int left, int right){
        if(left >= right){
            return left;
        }

        int middle = (left + right + 1) / 2;
        if(nums[middle] == target){
            return expandRight(nums, target, middle, right);
        }

        return expandRight(nums, target, left, middle - 1);
    }

    private int expandLeft(int[] nums, int target, int left, int right){
        if(left >= right){
            return left;
        }

        int middle = (left + right) / 2;
        if(nums[middle] == target){
            return expandLeft(nums, target, left, middle);
        }

        return expandLeft(nums, target, middle + 1, right);
    }

    public static void main(String[] args){
        int[] nums = new int[]{5,7,7,8,8,10};
        int[] result = (new FindFirstAndLastPositionOfElementInSortedArray()).searchRange(nums, 8);

        System.out.println(result);
    }
}

