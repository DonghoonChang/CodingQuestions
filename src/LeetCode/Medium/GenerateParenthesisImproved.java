package LeetCode.Medium;

import java.util.*;

/*
Runtime: 1 ms, faster than 97.59% of Java online submissions for Generate Parentheses.
Memory Usage: 42 MB, less than 97.96% of Java online submissions for Generate Parentheses.
 */
public class GenerateParenthesisImproved {
    public List<String> generateParenthesis(int n){
        List<String> answers = new ArrayList<>();
        getVariations(answers, new StringBuilder(), 0, 0, n);

        return answers;
    }

    private void getVariations(List<String> answers, StringBuilder sb, int open, int close, int n){
        if(close == n){
            answers.add(sb.toString());
            return;
        }

        if(open < n){
            StringBuilder sb2 = new StringBuilder(sb);
            sb2.append("(");
            getVariations(answers, sb2, open + 1, close, n);
        }

        if(open > close){
            StringBuilder sb2 = new StringBuilder(sb);
            sb2.append(")");
            getVariations(answers, sb2, open, close + 1, n);
        }
    }
}
