//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ProblemSolving.Algorithms.Strings;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;

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
            int min = Integer.MAX_VALUE;
            int length = gene.length();
            int stable = length / 4;
            HashMap<Character, Integer> count = new HashMap();
            count.put('A', 0);
            count.put('C', 0);
            count.put('G', 0);
            count.put('T', 0);

            int windowL;
            for(windowL = 0; windowL < length; ++windowL) {
                count.put(gene.charAt(windowL), count.get(gene.charAt(windowL)) + 1);
            }

            if (isStable(count, stable)) {
                return 0;
            } else {
                windowL = 0;
                int windowR = 0;

                while(windowL < length && windowR < length) {
                    if (!isStable(count, stable)) {
                        count.put(gene.charAt(windowR), count.get(gene.charAt(windowR)) - 1);
                        ++windowR;
                    } else {
                        min = Math.min(min, windowR - windowL);
                        count.put(gene.charAt(windowL), count.get(gene.charAt(windowL)) + 1);
                        ++windowL;
                    }
                }

                return min;
            }
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
