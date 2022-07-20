package DataStructures.Stacks;

import java.io.*;
import java.util.*;
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
            Stack<Building> stack = new Stack<>();
            Map<Integer, Integer> map = new HashMap<>();

            int maxArea = 0;
            int previousHeight = h.get(0);
            h.add(0); // adding a building of no height at the end
            for(int i = 0; i < h.size(); i++){
                Building thisBuilding = new Building(h.get(i), i);
                addOrPut(map, i, thisBuilding.height);

                if(i == 0){
                    stack.add(thisBuilding);
                    continue;
                }

                // increasing
                if(previousHeight < thisBuilding.height){
                    stack.add(thisBuilding);
                    previousHeight = thisBuilding.height;
                    continue;
                }  // decreasing

                while(!stack.isEmpty() && stack.peek().height >= thisBuilding.height){
                    Building prev = stack.pop();
                    int area = prev.height * (i - prev.index - 1);
                    addOrPut(map, prev.index, area);
                }

                if(stack.isEmpty()){ // found the new lowest among the traversed elements
                    int area = thisBuilding.height * (thisBuilding.index);
                    addOrPut(map, i, area);
                } else { // found the next lowest building than the current and the current is not the lowest among the traversed elements
                    int area = thisBuilding.height * (thisBuilding.index - stack.peek().index - 1);
                    addOrPut(map, i, area);
                }

                stack.add(thisBuilding);
                previousHeight = thisBuilding.height;
            }

            for(Integer area : map.values()){
                maxArea = Math.max(maxArea, area);
            }

            return maxArea;
        }

        private static void addOrPut(Map<Integer, Integer> map, int index, int area) {
            if (map.containsKey(index)) {
                int existing = map.get(index);
                map.put(index, existing + area);
            } else {
                map.put(index, area);
            }
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
