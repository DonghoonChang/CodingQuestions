package DataStructures.Stacks;

import Utils.Stopwatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;


public class SimpleTextEditor {

    private static Stopwatch sw = new Stopwatch();

    public static void text(StringBuilder sb, List<String> inputs){
        sw.start();
        Stack<String> undo = new Stack<>();

        for(String input: inputs){
            process(sb, input, undo, false);
        }
    }

    private static void process(StringBuilder sb, String input, Stack<String> undo, boolean isUndo){
        if(input.length() == 0){
            return;
        }

        String[] split = input.split("\\s");
        String type = split[0];

        switch(type){
            case "1":
                sw.record();
                if(!isUndo){
                    undo.push(getReverseInput(sb, input));
                }

                String data = split[1];
                sb.append(data);
                sw.addCumulativeElapsedTimeMs("Case 1", sw.getElapsedSinceLastRecord());
                break;
            case "2":
                sw.record();
                if(!isUndo){
                    undo.push(getReverseInput(sb, input));
                }

                String chars = split[1];
                String sub = sb.substring(0, sb.length() - Integer.parseInt(chars));
                sb.setLength(0);
                sb.append(sub);
                sw.addCumulativeElapsedTimeMs("Case 2", sw.getElapsedSinceLastRecord());
                break;
            case "3":
                sw.record();
                int index  = Integer.parseInt(split[1]);
                System.out.println(sb.charAt(index - 1));
                sw.addCumulativeElapsedTimeMs("Case 3", sw.getElapsedSinceLastRecord());
                break;
            case "4":
                sw.record();
                String prevInput = undo.isEmpty() ? "" : undo.pop();
                process(sb, prevInput, undo, true);
                sw.addCumulativeElapsedTimeMs("Case 4", sw.getElapsedSinceLastRecord());
                break;
            default:
                break;
        }
    }

    private static String getReverseInput(StringBuilder sb, String input){
        String[] split = input.split("\\s");
        String type = split[0];

        if(!type.equals("1") && !type.equals("2")){
            return "";
        }

        if(type.equals("1")){
            int l = split[1].length();
            return String.format("2 %d", l);
        }

        int l = Integer.parseInt(split[1]);
        String deleted = sb.substring(sb.length() - l);
        return "1 " + deleted;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        sc.nextLine();
        List<String> inputs = new ArrayList<>();
        for(int i = 0; i < q; i++){
            String input = sc.nextLine();
            inputs.add(input);
        }

        text(new StringBuilder(), inputs);
    }
}
