package LeetCode.Medium;

import java.util.*;

/*
Time: 1h 2m
Runtime: 5 ms, faster than 21.50% of Java online submissions for Generate Parentheses.
Memory Usage: 43.8 MB, less than 51.13% of Java online submissions for Generate Parentheses.
 */
public class GenerateParenthesis {
    public List<String> generateParenthesis(int n){
        List<boolean[]> answers = new ArrayList<>();
        getVariations(answers, new boolean[n * 2], 0, 0);
        Set<String> distinct = new HashSet<>();
        for(boolean[] answer: answers){
            distinct.add(stringify(answer));
        }

        return distinct.stream().toList();
    }

    private void getVariations(List<boolean[]> answers, boolean[] prev, int open, int close){
        int index = open + close;
        if(index == prev.length){
            answers.add(prev);
        }

        if(open == close){
            prev[index] = true;
            getVariations(answers, prev, open + 1, close);
            return;
        }

        if(open == prev.length / 2){
            answers.add(prev);
            return;
        }

        boolean[] cloneA = Arrays.copyOf(prev, prev.length);
        boolean[] cloneB = Arrays.copyOf(prev, prev.length);

        cloneA[index] = true;
        cloneB[index] = false;

        getVariations(answers, cloneA, open + 1, close);
        getVariations(answers, cloneB, open, close + 1);
    }

    private String stringify(boolean[] answer){
        StringBuilder sb = new StringBuilder();
        for(boolean open: answer){
            sb.append(open ? '(' : ')' );
        }

        return sb.toString();
    }

    public static void main(String[] args){
        List<String> answers = (new GenerateParenthesis()).generateParenthesis(3);

        for(String answer: answers){
            System.out.println(answer);
        }
    }

}
