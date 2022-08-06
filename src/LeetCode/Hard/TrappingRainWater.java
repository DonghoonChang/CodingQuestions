package LeetCode.Hard;

import java.util.Stack;

/*
    start time: 5:26
    end time: 6:09
    Runtime: 23 ms, faster than 5.58% of Java online submissions for Trapping Rain Water.
    Memory Usage: 51.4 MB, less than 5.28% of Java online submissions for Trapping Rain Water.
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        int l = height.length;
        Stack<int[]> stack = new Stack<>();
        int[] waterLevels = new int[l];
        int water = 0;

        for(int i = 0; i < l; i++){
            if(stack.isEmpty()){
                stack.add(new int[]{i, height[i]});
                continue;
            }

            if(stack.peek()[1] <= height[i]){
                int[] last = null;
                int lowerWall;
                while(!stack.isEmpty() && stack.peek()[1] <= height[i]){
                    last = stack.pop();
                }

                int fillStart = -1;
                if(stack.isEmpty()){
                    lowerWall = last[1];
                    fillStart = last[0] + 1;
                } else{
                    lowerWall = height[i];
                    fillStart = stack.peek()[0] + 1;
                }

                for(int j = fillStart; j < i; j++){
                    waterLevels[j] = lowerWall;
                }
            }

            stack.add(new int[]{i, height[i]});
        }

        for(int i = 0; i < l; i++){
            waterLevels[i] = waterLevels[i] > 0 ? waterLevels[i] - height[i] : 0;
        }

        for(int waterLevel: waterLevels){
            water += waterLevel;
        }

        return water;
    }

    public static void main(String[] args){
        int[] height = new int[]{4,2,3};
        int result = (new TrappingRainWater()).trap(height);

        System.out.println(result);
    }
}
