package LeetCode.Medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Runtime: 12 ms, faster than 6.48% of Java online submissions for Minimum Cost For Tickets.
Memory Usage: 42.3 MB, less than 40.22% of Java online submissions for Minimum Cost For Tickets.
 */
public class MinimumCostForTickets {
    static int min;
    static int[] _days;
    static int[] _costs;
    static int[] costMap;

    public int mincostTickets(int[] days, int[] costs) {
        _days = days;
        _costs = costs;
        costMap = new int[days.length];
        Arrays.fill(costMap, Integer.MAX_VALUE);
        min = Integer.MAX_VALUE;

        inner(0, 0);

        return min;
    }

    public void inner(int cost, int dayIndex) {
        if (dayIndex >= _days.length) {
            min = Math.min(min, cost);
            return;
        }

        if (costMap[dayIndex] <= cost) {
            return;
        } else {
            costMap[dayIndex] = cost;
        }

        int currDay = _days[dayIndex];
        int steps = 1;
        int stepsTill7DayTicketExpire = 1;
        int stepsTill30DayTicketExpire = 1;
        while (dayIndex + steps < _days.length) {
            int nextDay = _days[dayIndex + steps];

            if (nextDay - currDay < 7) {
                stepsTill7DayTicketExpire = steps + 1;
            }

            if (nextDay - currDay < 30) {
                stepsTill30DayTicketExpire = steps + 1;
            } else {
                break;
            }

            steps++;
        }


        inner(cost + _costs[2], dayIndex + stepsTill30DayTicketExpire);
        inner(cost + _costs[1], dayIndex + stepsTill7DayTicketExpire);
        inner(cost + _costs[0], dayIndex + 1);
    }

    public static void main(String[] args) {
//        int[] days = {1,4,6,7,8,20};
//        int[] days = {1,2,3,4,5,6,7,8,9,10,30,31};
//        int[] days = {1,4,6,9, 10,11,12,13,14,15,16, 17,18,20,21,22,23, 27,28};
//        int[] costs = {3, 13, 45};

//        int[] days = {1, 5, 7, 10};
        int[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31};
        int[] costs = {2, 7, 15};

        int result = (new MinimumCostForTickets()).mincostTickets(days, costs);
        System.out.println(result);
    }
}
