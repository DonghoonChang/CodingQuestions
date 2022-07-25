package LeetCode.Medium;

import java.util.*;

public class CheapestFlightsWithinKStops {
    /*
        Runtime: 7 ms, faster than 70.41% of Java online submissions for Cheapest Flights Within K Stops.
        Memory Usage: 47.3 MB, less than 42.63% of Java online submissions for Cheapest Flights Within K Stops.
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        Queue<Integer> toVisit = new LinkedList<>();
        toVisit.add(src);

        for(int[] flight: flights){
            int s = flight[0];
            if(graph.containsKey(s)){
                graph.get(s).add(flight);
            } else {
                graph.put(flight[0], new ArrayList<>(){{
                 add(flight);
                }});
            }
        }

        int[] costs = new int[n];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[src] = 0;
        for(int i = 0; i < k + 1 ; i++){
            int toVisitThisIteration = toVisit.size();
            int[] thisIterationCosts = Arrays.copyOf(costs, costs.length);
            for(int j = 0; j < toVisitThisIteration; j++){
                int next = toVisit.poll();
                int priceToNext = costs[next];
                List<int[]> routes = graph.get(next);
                if(routes == null){
                    continue;
                }

                for(int[] route: routes){
                    int nei = route[1];
                    int costFromNextToNei = route[2];
                    int totalCostToNei = priceToNext + costFromNextToNei;
                    if(totalCostToNei < thisIterationCosts[nei]){
                        thisIterationCosts[nei] = totalCostToNei;
                        toVisit.add(nei);
                    }
                }
            }

            costs = thisIterationCosts;
        }

        return costs[dst] == Integer.MAX_VALUE ? -1 : costs[dst];
    }

    public static void main(String[] args){
        int[][] flights = {{0,1,5},{1,2,5},{0,3,2},{3,1,2},{1,4,1},{4,2,1}};
        int result = (new CheapestFlightsWithinKStops()).findCheapestPrice(5, flights, 0, 2, 2);
        System.out.println(result);
    }
}
