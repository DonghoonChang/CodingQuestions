package LeetCode.Hard;

import java.util.*;

public class WordLadder {

    /*
        Runtime: 49 ms, faster than 92.91% of Java online submissions for Word Ladder.
        Memory Usage: 50.3 MB, less than 73.99% of Java online submissions for Word Ladder.
     */
    public static int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.stream().anyMatch(l -> l.equals(endWord))){
            return 0;
        }

        Map<String, List<String>> patternToWords = new HashMap<>();
        wordList.add(beginWord);
        for(String word: wordList){
            List<String> patterns = getPatterns(word);

            for(String pattern: patterns){
                extendedPut(patternToWords, pattern, word);
            }
        }

        Set<String> visited = new HashSet<>();
        Queue<String> toVisit = new LinkedList<>();
        toVisit.add(beginWord);
        visited.add(beginWord);

        int steps = 1;
        while(!toVisit.isEmpty()){
            int size = toVisit.size();
            for(int i = 0; i < size; i++){
                String word = toVisit.poll();
                if(word.equals(endWord)){
                    return steps;
                }

                for(String pattern: getPatterns(word)){
                    for(String neighbour: patternToWords.get(pattern)){
                        if(visited.contains(neighbour)){
                            continue;
                        }

                        toVisit.add(neighbour);
                        visited.add(neighbour);
                    }
                }
            }

            steps++;
        }

        return 0;
    }

    private static void extendedPut(Map<String, List<String>> map, String key, String value){
        if(map.containsKey(key)){
            map.get(key).add(value);
            return;
        }

        List<String> list = new ArrayList<>(){{
            add(value);
        }};

        map.put(key, list);
    }

    private static List<String> getPatterns(String original){
        List<String> list = new ArrayList<>();
        for(int i = 0; i < original.length(); i++){
            list.add(original.substring(0, i) + "*" + original.substring(i + 1));
        }

        return list;
    }


    // Bellman-ford
    // Inefficient, but without ways get neighbours in O(1), this is the best choice
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
        List<String> list = new ArrayList<>();
        list.add("ted");
        list.add("tex");
        list.add("red");
        list.add("tax");
        list.add("tad");
        list.add("den");
        list.add("rex");
        list.add("pee");

        int result = ladderLength2("red", "tax", list);
        System.out.println(result);
    }
}
