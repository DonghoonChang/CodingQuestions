package LeetCode.Hard;

import java.util.*;

public class WordLadder {

    //Bel
    /*
        Runtime: 935 ms, faster than 15.67% of Java online submissions for Word Ladder.
        Memory Usage: 77.9 MB, less than 49.58% of Java online submissions for Word Ladder.
    */
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        boolean found = false;
        for(String word: wordList){
            if (word.equals(endWord)){
                found = true;
            }
        }

        if(!found){
            return 0;
        }

        List<String> extended = new ArrayList<>();
        extended.add(beginWord);
        extended.addAll(wordList.stream().filter(l -> !l.equals(endWord) && !l.equals(beginWord)).toList());
        extended.add(endWord);

        int l = extended.size();
        boolean[][] graph = new boolean[l][l]; // from -> to
        for (int i = 0; i < l; i++) {
            String a = extended.get(i);
            for (int j = i + 1; j < l; j++) {
                String b = extended.get(j);

                if (canGo(a, b)) {
                    graph[i][j] = true;
                    graph[j][i] = true;
                }
            }
        }

        int[] distances = new int[l];
        distances[0] = 1;
        for(int i = 1; i < l; i++){ // i = 0: startWord
            distances[i] = Integer.MAX_VALUE;
        }

        Queue<Integer> updated = new LinkedList<>();
        updated.add(0);
        while(!updated.isEmpty()) {
            int from = updated.poll();
            int newDistance = distances[from] + 1;
            int currMin = distances[l - 1];

            if(newDistance >= currMin){
                continue;
            }

            for(int to = 0; to < l; to ++){
                if(from == to){
                    continue;
                }

                if(graph[from][to]){
                    int existingDistance = distances[to];

                    if(existingDistance > newDistance){
                        updated.add(to);
                        distances[to] = newDistance;
                    }
                }
            }
        }


        return distances[l - 1] == Integer.MAX_VALUE ? 0 : distances[l - 1];
    }

    private static boolean canGo(String w1, String w2) {
        int diffCount = 0;
        for (int i = 0; i < w1.length(); i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                diffCount++;
            }
        }

        return diffCount <= 1;
    }
    public static void main(String[] args) {
        int result = ladderLength("leet", "code", Arrays.stream(new String[]{"lest","leet","lose","code","lode","robe","lost"}).toList());
        System.out.println(result);
    }
}
