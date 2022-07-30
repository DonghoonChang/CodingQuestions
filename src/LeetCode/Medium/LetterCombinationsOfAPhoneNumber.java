package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

/*
Runtime: 6 ms, faster than 42.33% of Java online submissions for Letter Combinations of a Phone Number.
Memory Usage: 41 MB, less than 94.09% of Java online submissions for Letter Combinations of a Phone Number.
 */
public class LetterCombinationsOfAPhoneNumber {

    private static final List<char[]> chars = new ArrayList<>();


    public List<String> letterCombinations(String digits) {
        init();
        int l = digits.length();

        if (l == 0) {
            return new ArrayList<>();
        }

        List<String> cb = new ArrayList<>();
        cb.add("");

        for (int i = 0; i < l; i++) {
            int digit = Integer.parseInt("" + digits.charAt(i));
            char[] letters = chars.get(digit);

            List<String> cbNew = new ArrayList<>();

            for (String str : cb) {
                for (char letter : letters) {
                    cbNew.add(str + letter);
                }
            }

            cb = cbNew;
        }

        return cb;
    }

    private void init() {
        char[] _2 = new char[]{'a', 'b', 'c'};
        char[] _3 = new char[]{'d', 'e', 'f'};
        char[] _4 = new char[]{'g', 'h', 'i'};
        char[] _5 = new char[]{'j', 'k', 'l'};
        char[] _6 = new char[]{'m', 'n', 'o'};
        char[] _7 = new char[]{'p', 'q', 'r', 's'};
        char[] _8 = new char[]{'t', 'u', 'v'};
        char[] _9 = new char[]{'w', 'x', 'y', 'z'};

        chars.add(new char[0]); // 0
        chars.add(new char[0]); // 2
        chars.add(_2);
        chars.add(_3);
        chars.add(_4);
        chars.add(_5);
        chars.add(_6);
        chars.add(_7);
        chars.add(_8);
        chars.add(_9);
    }

    private String getTemplate(int length) {
        String rtn = "";

        for (int i = 0; i < length; i++) {
            rtn += "*";
        }

        return rtn;
    }
}
