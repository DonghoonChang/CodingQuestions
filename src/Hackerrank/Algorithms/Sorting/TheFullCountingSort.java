package Hackerrank.Algorithms.Sorting;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

//20
//0 ab
//6 cd
//0 ef
//6 gh
//4 ij
//0 ab
//6 cd
//0 ef
//6 gh
//0 ij
//4 that
//3 be
//0 to
//1 be
//5 question
//1 or
//2 not
//4 is
//2 to
//4 the

public class TheFullCountingSort {

    class Result {

        public static void countSort(List<List<String>> arr) {
            int l = arr.size();
            int[] count = new int[100];
            String[] sorted = new String[l];

            for(int i = 0; i < l / 2; i++){
                arr.get(i).set(1, "-");
            }

            for(List<String> pair: arr){
                int num = Integer.parseInt(pair.get(0));
                count[num]++;
            }

            for(int i = 1; i < 100; i++){
                count[i] = count[i] + count[i - 1];
            }

            StringBuilder sb = new StringBuilder();
            for(int i = l - 1; i >= 0; i--){
                int num = Integer.parseInt(arr.get(i).get(0));
                String str = arr.get(i).get(1);

                sorted[--count[num]] = str;
            }

            for(String str: sorted){
                sb.append(str);
                sb.append(" ");
            }

            System.out.println(sb);
        }

        public static void notCountSort(List<List<String>> arr) {
            int l = arr.size();
            Map<Integer, List<String>> map = new HashMap();

            for(int i = 0; i < l / 2; i++){
                arr.get(i).set(1, "-");
            }

            for(List<String> pair: arr){
                int num = Integer.parseInt(pair.get(0));
                String str = pair.get(1);

                if (map.containsKey(num)){
                    map.get(num).add(str);

                } else {
                    List<String> list = new ArrayList();
                    list.add(pair.get(1));
                    map.put(num, list);
                }
            }

            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < l; i++){
                if(!map.containsKey(i)){
                    continue;
                }

                for(String str: map.get(i)){
                    sb.append(str);
                    sb.append(" ");
                }
            }

            System.out.println(sb);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> arr = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                arr.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Result.countSort(arr);

        bufferedReader.close();
    }
}
