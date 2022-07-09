package DataStructures.Trees;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'swapNodes' function below.
     *
     * The function is expected to return a 2D_INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. 2D_INTEGER_ARRAY indexes
     *  2. INTEGER_ARRAY queries
     */

    static class Node{
        int index;
        Node left, right;
        int depth;

        public Node(int i, int d){
            index = i;
            depth = d;
        }
    }

    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {
        // Space O(n)
        Map<Integer, Node> indexMap = new HashMap<>();
        Map<Integer, List<Node>> depthMap = new HashMap<>();
        Queue<Integer> indexQueue = new LinkedList<>();

        // init data
        int rootIndex = 1;
        int rootDepth = 1;
        int maxDepth = 1;
        Node root = new Node(rootIndex, rootDepth);

        // init prep
        indexMap.put(rootIndex, root);
        addNodeToDepthMap(depthMap, rootDepth, root);
        indexQueue.add(1);

        for(int i = 0; i < indexes.size(); i++){
            int nextIndex = indexQueue.remove();

            List<Integer> children = indexes.get(i);
            int leftIndex = children.get(0);
            int rightIndex = children.get(1);

            Node next = indexMap.get(nextIndex);
            Node left =  null;
            Node right =  null;

            if(leftIndex != -1){
                left = new Node(leftIndex, next.depth + 1);

                indexQueue.add(leftIndex);
                addNodeToDepthMap(depthMap, next.depth + 1, left);
                indexMap.put(leftIndex, left);
                maxDepth = Math.max(maxDepth, next.depth + 1);
            }

            if(rightIndex != -1){
                right = new Node(rightIndex, next.depth + 1);

                indexQueue.add(rightIndex);
                addNodeToDepthMap(depthMap, next.depth + 1, right);
                indexMap.put(rightIndex, right);
                maxDepth = Math.max(maxDepth, next.depth + 1);
            }

            next.left = left;
            next.right = right;
        }

        List<List<Integer>> results = new ArrayList<>();
        for(Integer depth: queries){
            for(int i = depth; i <= maxDepth; i += depth){
                List<Node> nodes = depthMap.get(i);

                for(Node node: nodes){
                    swapLeftRight(node);
                }
            }

            List<Integer> result = traverseFromRoot(root);
            results.add(result);
        }

        return results;
    }

    private static void addNodeToDepthMap(Map<Integer, List<Node>> map, int depth, Node node){
        if (map.containsKey(depth)){
            map.get(depth).add(node);
            return;
        }

        List<Node> nodes = new ArrayList<>();
        nodes.add(node);
        map.put(depth, nodes);
    }

    private static void swapLeftRight(Node node){
        Node temp = node.left;
        node.left = node.right;
        node.right = temp;
    }

    private static List<Integer> traverseFromRoot(Node root){
        List<Integer> rtn = new ArrayList<>();

        traverseFromRootInner(root, rtn);

        return rtn;
    }

    private static void traverseFromRootInner(Node node, List<Integer> order){
        if (node.left != null){
            traverseFromRootInner(node.left, order);
        }

        order.add(node.index);

        if (node.right != null){
            traverseFromRootInner(node.right, order);
        }
    }
}

public class SwapNodes {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> indexes = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                indexes.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> queries = IntStream.range(0, queriesCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<List<Integer>> result = Result.swapNodes(indexes, queries);

        result.stream()
                .map(
                        r -> r.stream()
                                .map(Object::toString)
                                .collect(joining(" "))
                )
                .map(r -> r + "\n")
                .collect(toList())
                .forEach(e -> {
                    try {
                        bufferedWriter.write(e);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
