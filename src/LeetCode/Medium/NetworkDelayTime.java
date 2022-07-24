package LeetCode.Medium;

import java.util.*;

public class NetworkDelayTime {

    private static class Edge {
        public int from;
        public int to;
        public int delay;

        public Edge(int f, int t, int d) {
            from = f;
            to = t;
            delay = d;
        }

        public void addDelay(int val){
            delay += val;
        }
    }

    /*
        Runtime: 65 ms, faster than 13.21% of Java online submissions for Network Delay Time.
        Memory Usage: 65.6 MB, less than 45.86% of Java online submissions for Network Delay Time.
     */

    public static int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<Edge>> graph = new HashMap<>(); // Vertex -> edges
        Map<Integer, Integer> delays = new HashMap<>(); // Node index -> total delay from k to this
        Queue<Edge> edgeHeap = new PriorityQueue<>(10, Comparator.comparingInt(e -> e.delay));

        for (int[] time : times) {
            int from = time[0];
            Edge edge = getEdge(time);
            extendedPut(graph, from, edge);
        }

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> toVisit = new LinkedList<>();
        delays.put(k, 0);
        toVisit.add(k);
        visited.add(k);

        while (!toVisit.isEmpty()) {
            int currNode = toVisit.poll();
            List<Edge> edgesFromThisNode = graph.get(currNode);
            if(edgesFromThisNode != null){
                edgesFromThisNode.forEach(l -> l.addDelay(delays.get(currNode)));
                edgeHeap.addAll(edgesFromThisNode);
            }

            Edge nextSmallest = edgeHeap.poll();
            while (nextSmallest != null && visited.contains(nextSmallest.to)){
                nextSmallest = edgeHeap.poll();
            }

            if(nextSmallest == null){ // Ran out of edges to test
                break;
            }

            delays.put(nextSmallest.to, nextSmallest.delay);
            toVisit.add(nextSmallest.to);
            visited.add(nextSmallest.to);
        }

        if(delays.keySet().size() != n){
            return -1;
        }

        int maxDelay = -1;
        for(int delay: delays.values()){
            maxDelay = Math.max(maxDelay, delay);
        }

        return maxDelay;
    }

    private static Edge getEdge(int[] times) {
        int from = times[0];
        int to = times[1];
        int delay = times[2];

        return new Edge(from, to, delay);
    }

    private static void extendedPut(Map<Integer, List<Edge>> map, int key, Edge edge) {
        if (map.containsKey(key)) {
            map.get(key).add(edge);
            return;
        }

        List<Edge> list = new ArrayList<>();
        list.add(edge);
        map.put(key, list);
    }

    public static void main(String[] args) {
        int n = 5;
        int k = 5;
        int[][] times = {{3,5,78},{2,1,1},{1,3,0},{4,3,59},{5,3,85},{5,2,22},{2,4,23},{1,4,43},{4,5,75},{5,1,15},{1,5,91},{4,1,16},{3,2,98},{3,4,22},{5,4,31},{1,2,0},{2,5,4},{4,2,51},{3,1,36},{2,3,59}};
//        int n = 3;
//        int k = 2;
//        int[][] times = {{2,1,1},{2,3,1},{3,4,1}};

        int result = networkDelayTime(times, n, k);
        System.out.println(result);
    }
}
