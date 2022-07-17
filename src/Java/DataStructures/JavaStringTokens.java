package Java.DataStructures;

import java.util.*;
import java.util.stream.Collectors;

public class JavaStringTokens {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();

        String delims = "[ !,?._'@]+";
        List<String> tokens = Arrays.asList(s.trim().split(delims));
        tokens = tokens.stream().filter(l -> l != "").collect(java.util.stream.Collectors.toList());

        System.out.println(tokens.size());
        for(String token: tokens){
            System.out.println(token);
        }
        scan.close();
    }
}

