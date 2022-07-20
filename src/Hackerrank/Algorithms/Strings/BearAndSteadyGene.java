//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Hackerrank.Algorithms.Strings;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class BearAndSteadyGene {
    public BearAndSteadyGene() {
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bufferedReader.readLine().trim());
        String gene = bufferedReader.readLine();
        int result = BearAndSteadyGene.Result.steadyGene(gene);
        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();
        bufferedReader.close();
        bufferedWriter.close();
    }

    class Result {
        Result(BearAndSteadyGene this$0) {
        }

        public static int steadyGene(String gene) {
            int length = gene.length();
            int stable = length / 4;

            HashMap<Character, Integer> count = new HashMap<>();
            count.put('A', 0);
            count.put('C', 0);
            count.put('G', 0);
            count.put('T', 0);

            // O(n)
            for(int i = 0; i < length; i++){
                count.put(gene.charAt(i), count.get(gene.charAt(i)) + 1);
            }

            if(isStable(count, stable)){
                return 0;
            }

            // O(n)
            int windowL = 0;
            int windowR = 0;
            int min = length;

            while(windowR < length && windowL < length){
                for(;windowR < length;){
                    if(!isStable(count, stable)){
                        count.put(gene.charAt(windowR), count.get(gene.charAt(windowR)) - 1);
                        windowR++;
                        continue;
                    }

                    break;
                }

                // decrease window size
                for(; windowL < windowR;){
                    if(isStable(count, stable)){
                        min = Math.min(min, windowR - windowL);
                        count.put(gene.charAt(windowL), count.get(gene.charAt(windowL)) + 1);
                        windowL++;
                        continue;
                    }

                    break;
                }
            }

            return min;
        }

        private static boolean isStable(HashMap<Character, Integer> map, int threshold) {
            for(int count : map.values()){
                if(count > threshold){
                    return false;
                }
            }

            return true;
        }
    }
}
