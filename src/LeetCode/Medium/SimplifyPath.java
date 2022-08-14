package LeetCode.Medium;

import java.util.Deque;
import java.util.LinkedList;

/*
Time: 11m
Runtime: 6 ms, faster than 83.21% of Java online submissions for Simplify Path.
Memory Usage: 44 MB, less than 56.04% of Java online submissions for Simplify Path.
 */
public class SimplifyPath {
    public String simplifyPath(String path) {
        String[] tokens = path.split("/");
        Deque<String> dq = new LinkedList<>();

        for (String token : tokens) {
            if (token.isEmpty()) {
                continue;
            }

            switch (token) {
                case ".":
                    continue;
                case "..":
                    if (!dq.isEmpty()) {
                        dq.pollLast();
                    }
                    continue;
                default:
                    dq.offerLast(token);
            }
        }

        if (dq.isEmpty()) {
            return "/";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("/");

        while (!dq.isEmpty()) {
            sb.append(dq.pollFirst());
            sb.append("/");
        }

        String rtn = sb.toString();
        return rtn.substring(0, rtn.length() - 1);
    }

    public static void main(String[] args) {
        String path = "/home/myhome/nomore/../changed";
        String simplifiedPath = (new SimplifyPath()).simplifyPath(path);

        System.out.println(simplifiedPath);
    }
}
