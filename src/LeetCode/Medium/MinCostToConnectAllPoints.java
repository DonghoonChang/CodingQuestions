package LeetCode.Medium;

import java.util.*;

public class MinCostToConnectAllPoints {
    public class Edge {
        public int[] from;
        public int[] to;
        public int d;

        public Edge(int[] from, int[] to, int d){
            this.from = from;
            this.to = to;
            this.d = d;
        }
    }

    /*
        Runtime: 296 ms, faster than 54.44% of Java online submissions for Min Cost to Connect All Points.
        Memory Usage: 92.4 MB, less than 68.95% of Java online submissions for Min Cost to Connect All Points.
     */
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        Map<int[], List<Edge>> edges = new HashMap<>();
        Queue<Edge> edgePool = new PriorityQueue<>(n, Comparator.comparingInt(a -> a.d));
        Set<int[]> map = new HashSet<>();

        int span = 0;
        for(int[] from: points){
            edges.put(from, new ArrayList<>());
            for(int[] to: points){
                if(from == to){
                    continue;
                }

                edges.get(from).add(new Edge(from, to, getManhattanDistance(from, to)));
            }
        }

        int[] nextPoint = points[0];
        map.add(nextPoint);
        while(map.size() != n){
            edgePool.addAll(edges.get(nextPoint));

            Edge edge;
            while(true){
               edge = edgePool.poll();

               if(!map.contains(edge.to)){
                   span += edge.d;
                   nextPoint = edge.to;
                   map.add(nextPoint);
                   break;
               }
            }
        }

        return span;
    }

    private int getManhattanDistance(int[] a, int[] b){
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    public static void main(String[] agrs){
//        int[][] arg = new int[][]{{2,-3},{-17,-8},{13,8},{-17,-15}};
        int[][] arg = new int[][]{{0,0},{2,2},{3,10},{5,2},{7,0}};
        int result = (new MinCostToConnectAllPoints()).minCostConnectPoints(arg);

        System.out.println(result);
    }
}
