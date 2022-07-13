package ProblemSolving.Algorithms.Greedy;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.apache.commons.lang.time.StopWatch;

import static java.util.stream.Collectors.toList;

public class CloudyDay {

    class Result {

        /*
         * Complete the 'maximumPeople' function below.
         *
         * The function is expected to return a LONG_INTEGER.
         * The function accepts following parameters:
         *  1. LONG_INTEGER_ARRAY p : population
         *  2. LONG_INTEGER_ARRAY x : locaiton of these populations
         *  3. LONG_INTEGER_ARRAY y : location of the clouds
         *  4. LONG_INTEGER_ARRAY r : range of each cloud
         */

        public static long maximumPeople(List<Long> p, List<Long> x, List<Long> y, List<Long> r) {
            List<List<Long>> clouds = new ArrayList<>();
            Map<Long, Long> map = new HashMap<>();
            Map<Integer, Long> popUnderCloud = new HashMap<>();

            for(int i = 0; i < x.size(); i++){
                long pop = p.get(i);
                long loc = x.get(i);

                if (map.containsKey(loc)){
                    map.put(loc, map.get(loc) + pop);
                } else {
                    map.put(loc, pop);
                }
            }

            for(int i = 0; i < y.size(); i++){
                long loc = y.get(i);
                long range = r.get(i);

                List<Long> cloud = new ArrayList<>();
                cloud.add(loc - range);
                cloud.add(loc + range);
                clouds.add(cloud);
            }

            long alreadySunny = 0;

            for(Map.Entry<Long, Long> city: map.entrySet()){
                long pop = city.getValue();
                long loc = city.getKey();

                int count = 0;
                int cloudIndex = -1;

                for(int i = 0; i < clouds.size(); i++){
                    List<Long> cloud = clouds.get(i);
                    long left = cloud.get(0);
                    long right = cloud.get(1);

                    if(left <= loc && loc <= right){
                        count++;

                        if(count >= 2){
                            break;
                        }

                        cloudIndex = i;
                    }
                }

                if(count == 0){
                    alreadySunny += pop;
                } else if (count == 1){
                    if(popUnderCloud.containsKey(cloudIndex)){
                        long existing = popUnderCloud.get(cloudIndex);
                        popUnderCloud.put(cloudIndex, existing + pop);
                    } else {
                        popUnderCloud.put(cloudIndex, pop);
                    }
                }
            }

            long maxSunnifiable = -1;
            for(Map.Entry<Integer, Long> entry: popUnderCloud.entrySet()){
                maxSunnifiable = Math.max(maxSunnifiable, entry.getValue());
            }

            return alreadySunny + (maxSunnifiable == -1 ? 0 : maxSunnifiable);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(
                CloudyDay.class.getProtectionDomain().getCodeSource().getLocation().getPath() +
                        CloudyDay.class.getPackageName().replace(".", "/") +
                        "\\Input.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Long> p = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        List<Long> x = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        int m = Integer.parseInt(bufferedReader.readLine().trim());

        List<Long> y = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        List<Long> r = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        long result = Result.maximumPeople(p, x, y, r);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
