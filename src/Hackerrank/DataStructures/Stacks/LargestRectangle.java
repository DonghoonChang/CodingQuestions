package Hackerrank.DataStructures.Stacks;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class LargestRectangle {

    class Result {

        /*
         * Complete the 'largestRectangle' function below.
         *
         * The function is expected to return a LONG_INTEGER.
         * The function accepts INTEGER_ARRAY h as parameter.
         */

        private static class Building {
            public int height;
            public int index;

            public Building(int h, int i){
                height = h;
                index = i;
            }
        }

        public static long largestRectangle(List<Integer> h) {
            List<Integer> debug = new ArrayList<>();
            Stack<Building> stack = new Stack<>();

            int maxArea = h.size(); // 1 * # of buildings
            for(int i = 0; i < h.size(); i++){
                maxArea = Math.max(maxArea, h.get(i)); // single buildings
            }

            for(int a = 0; a < 2; a++){
                int previousHeight = h.get(0);
                for(int i = 0; i < h.size(); i++){
                    Building thisBuilding = new Building(h.get(i), i);

                    if(i == 0){
                        stack.add(thisBuilding);
                        continue;
                    }

                    // increasing
                    if(previousHeight < thisBuilding.height){
                        stack.add(thisBuilding);

                    } else { // decreasing

                        while(!stack.isEmpty() && stack.peek().height >= thisBuilding.height){
                            stack.pop();
                        }

                        if(stack.isEmpty()){ // found the new lowest among the traversed elements
                            int area = thisBuilding.height * (thisBuilding.index + 1);
                            debug.add(area);
                            maxArea = Math.max(maxArea, thisBuilding.height * (thisBuilding.index + 1));
                        } else { // found the next lowest building than the current and the current is not the lowest among the traversed elements
                            int area = thisBuilding.height * (thisBuilding.index - stack.peek().index);
                            debug.add(area);
                            maxArea = Math.max(maxArea, thisBuilding.height * (thisBuilding.index - stack.peek().index));
                        }

                        stack.add(thisBuilding);
                    }

                    previousHeight = thisBuilding.height;
                }

                Collections.reverse(h);
                stack.removeAllElements();
            }

            return maxArea;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> h = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        long result = Result.largestRectangle(h);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}
