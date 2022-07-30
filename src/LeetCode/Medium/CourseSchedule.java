package LeetCode.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Runtime: 4 ms, faster than 93.78% of Java online submissions for Course Schedule.
Memory Usage: 42.8 MB, less than 90.79% of Java online submissions for Course Schedule.
 */
public class CourseSchedule {
    // prerequisite[i] = [target, prerequisite]
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] dp = new int[numCourses]; // 0-> not visited 1: possible 2:impossible

        for (int[] prerequisite : prerequisites) {
            int from = prerequisite[0];
            int to = prerequisite[1];

            if (map.containsKey(from)) {
                map.get(from).add(to);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(to);
                map.put(from, list);
            }
        }

        for (int i = 0; i < numCourses; i++) {
            if (!dfs(map, dp, i, 0, numCourses)) {
                return false;
            }
            ;
        }

        return true;
    }

    public boolean dfs(Map<Integer, List<Integer>> map, int[] dp, int course, int count, int numCourses) {
        if (count >= numCourses) {
            return false;
        }

        if (dp[course] != 0) {
            return dp[course] == 1;
        }

        List<Integer> prerequisites = map.get(course);
        if (prerequisites == null) {
            return true;
        }

        boolean possible = true;
        for (int prerequisite : prerequisites) {
            if (!dfs(map, dp, prerequisite, count + 1, numCourses)) {
                possible = false;
                break;
            }
        }

        dp[course] = possible ? 1 : -1;
        return possible;
    }

    public static void main(String[] args) {
        int numCourses = 2;
//        int[][] prerequisites = {{1,0}};
        int[][] prerequisites = {{1, 0}, {0, 1}};
        boolean result = (new CourseSchedule()).canFinish(numCourses, prerequisites);

        System.out.println(result);
    }
}
