package LeetCode.Medium;

public class MinimumNumberofFlipstoMaketheBinaryStringAlternating {
    public int minFlips(String s) {
        char[] chars = s.toCharArray();

        for(int i = 0; i < s.length(); i += 2){
            char first = chars[i];
            char second = chars[i + 1];
            char third = chars[i + 2];

            // 101 010
            if(first != second && second != third){
                continue;
            }

            // 111 000
            if(first == second && second == third){
                if (second == '0'){
                    chars[i + 1] = '1';
                } else {
                    chars[i + 1] = '0';
                }
                continue;
            }

            //
            if(first == second){

            }
        }
    }
}
