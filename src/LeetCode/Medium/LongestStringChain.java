package LeetCode.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// start time: 11: 15
// end time: 12: 03

/*
    Runtime: 506 ms, faster than 5.01% of Java online submissions for Longest String Chain.
    Memory Usage: 62.5 MB, less than 51.36% of Java online submissions for Longest String Chain.

    Needs to be improved
 */
public class LongestStringChain {

    static int[][] memo;

    public int longestStrChain(String[] words) {
        int l = words.length;
        memo = new int[l][l];

        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int i = 0; i < l; i++){
            for(int j = 0; j < l; j++){
                if(isSuccessor(words, i, j)){
                    if(map.containsKey(i)){
                        map.get(i).add(j);
                    } else{
                        List<Integer> list = new ArrayList<>();
                        list.add(j);
                        map.put(i, list);
                    }
                }
            }
        }

        int[] counts = new int[l];
        for(int i = 0; i < l; i++) {
            countSuccessors(map, counts, i, i, 1);
        }

        int max = 1;
        for(int count: counts){
            max = Math.max(max, count);
        }

        return max;
    }

    private void countSuccessors(Map<Integer, List<Integer>> map, int[] counts, int originalIndex, int currentIndex, int count){
        if(!map.containsKey(currentIndex)){
            counts[originalIndex] = Math.max(counts[originalIndex], count);
            return;
        }

        if(counts[currentIndex] != 0){
            counts[originalIndex] = Math.max(counts[originalIndex], count + counts[currentIndex] - 1);
            return;
        }

        List<Integer> nexts = map.get(currentIndex);
        for(int next: nexts){
            countSuccessors(map, counts, originalIndex, next, count + 1);
        }
    }

    // B has to be longer
    private boolean isSuccessor(String[] words, int a, int b){
        if(memo[a][b] != 0){
            return memo[a][b] == 1;
        }

        String strA = words[a];
        String strB = words[b];


        if(strA.length() >= strB.length()){
            return false;
        }

        if(strA.length() + 1 < strB.length()){
            return false;
        }

        int diff = 0;
        for(int i = 0, j = 0; i < strA.length() && j < strB.length();){
            if(strA.charAt(i) != strB.charAt(j)){
                diff++;
                j++;
            } else {
                i++;
                j++;
            }

            if(diff >= 2){
                memo[a][b] = -1;
                return false;
            }
        }

        memo[a][b] = 1;
        return true;
    }

    public static void main(String[] args){
        String[] words = {"a","abc"};
//        String[] words = {"a","b","ba","bca","bda","bdca"};
//        String[] words = {"xbc","pcxbcf","xb","cxbc","pcxbc"};
//        String[] words = {"abcd","dbqca"};
//        String[] words = {"bqtmbnugq","bjqtmbnuwsgq","m","btmnugq","czlhk","ihkgszexnh","wiszechhcxldcrow","kgszn","lhk","zlzfgmjurcntwvn","jjvodlrsmdmie","tm","ihqkgpszexnh","wnwpxg","zmun","hkgszenh","zmucnwn","kgzn","yjmk","wiszechcxldcrow","fnnh","yjm","jjvodlrmdmie","bqtmnugq","hkgszen","wehcxdrow","nhed","zmucn","wisehcxdrow","fnnlh","wehcxdro","zlzgmjurcntwvn","wnwg","jjvodlmde","wisechcxldcrow","wiehcxdrow","nhxyeedlcqxw","wehcxr","zzgmjurcnwvn","btmgq","nwdhslknbwpxg","ihqkgszexnh","jjvodlrsmdmhie","jjvodlmd","wdhslknbwpxg","nhedxw","wisehcxldcrow","btmugq","mfnnlfh","bqtmbnuwgq","nhedcxw","bqtmbnuwsgq","nhe","zzgmjurcntwvn","jjvodlrmdie","whslknwpxg","wdhslknwpxg","wsnwpxg","jjvodlm","hkgszexnh","zzgmjucnwvn","nhxyeedlcxw","nhxedcxw","zzmucnwn","hkgszn","zmucnw","whsnwpxg","czlmhk","whsknwpxg","wehcxro","wnwpg","nhxeedcxw","nwdhslknbjwpxg","nhedw","tmg","zlhk","zlzfgmjurcntwvnr","jjvodlmdie","zzmjucnwvn","mfnnlh","zzmjucnwn","wisehcxldrow","tmgq","nhxyeedcxw"};
        int result = (new LongestStringChain()).longestStrChain(words);

        System.out.println(result);
    }
}
