package Hackerrank.Algorithms.Greedy;

import Utils.Stopwatch;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class CloudyDay {

    class Result {

        /*
         * Complete the 'maximumPeople' function below.
         *
         * The function is expected to return a LONG_INTEGER.
         * The function accepts following parameters:
         *  1. LONG_INTEGER_ARRAY p : population
         *  2. LONG_INTEGER_ARRAY x : location of these populations
         *  3. LONG_INTEGER_ARRAY y : location of the clouds
         *  4. LONG_INTEGER_ARRAY r : range of each cloud
         */

        public static long maximumPeople(List<Long> p, List<Long> x, List<Long> y, List<Long> r) {
            Set<Long> positionsSet = new HashSet<>(x);
            Map<Long, Integer> positionToIndex = new HashMap<>();
            Map<Long, Long> map = new HashMap<>();

            Stopwatch sw = new Stopwatch();
            sw.start();

            // O(n)
            for(int i = 0; i < x.size(); i++){
                long pop = p.get(i);
                long loc = x.get(i);

                if (map.containsKey(loc)){
                    map.put(loc, map.get(loc) + pop);
                } else {
                    map.put(loc, pop);
                }
            }

            System.out.println(String.format("first Loop: %d", sw.getElapsedSinceLastRecord()));
            sw.record();

            p = new ArrayList<>();
            x = new ArrayList<>();
            for(Map.Entry<Long, Long> entry: map.entrySet()){
                long loc = entry.getKey();
                long pop = entry.getValue();

                x.add(loc);
                p.add(pop);
            }

            System.out.println(String.format("second Loop: %d", sw.getElapsedSinceLastRecord()));
            sw.record();

            // get distinct positions of cities and clouds(left, right)
            for(int i = 0; i < y.size(); i++){
                long loc = y.get(i);
                long range = r.get(i);
                long left = loc - range;
                long right = loc + range;

                positionsSet.add(left);
                positionsSet.add(right + 1);
            }

            System.out.println(String.format("third Loop: %d", sw.getElapsedSinceLastRecord()));
            sw.record();

            List<Long> positionsList = Arrays.asList(positionsSet.toArray(Long[]::new));
            Collections.sort(positionsList);
            int totalPositions = positionsSet.size();
            for(int i = 0; i < totalPositions; i++){
                long pos = positionsList.get(i);
                positionToIndex.put(pos, i);
            }

            System.out.println(String.format("4th Loop: %d", sw.getElapsedSinceLastRecord()));
            sw.record();

            long[] cityPopulations = new long[totalPositions];
            for(int i = 0; i < x.size(); i++){
                long cityPos = x.get(i);
                long cityPop = p.get(i);
                cityPopulations[positionToIndex.get(cityPos)] = cityPop;
            }

            System.out.println(String.format("5th Loop: %d", sw.getElapsedSinceLastRecord()));
            sw.record();

            long[] clouded = new long[totalPositions];
            for(int i = 0; i < y.size(); i++){
                long loc = y.get(i);
                long range = r.get(i);
                long left = loc - range;
                long right = loc + range;
                int leftIndex = positionToIndex.get(left);
                int rightIndex = positionToIndex.get(right + 1);

                clouded[leftIndex]++;
                clouded[rightIndex]--;
            }

            System.out.println(String.format("6th Loop: %d", sw.getElapsedSinceLastRecord()));
            sw.record();

            int cloudCount = 0;
            long sunnyCount = 0;
            for(int i = 0; i < totalPositions; i++){
                cloudCount += clouded[i];

                if(cloudCount == 0 && cityPopulations[i] != 0){
                    sunnyCount += cityPopulations[i];
                }

                clouded[i] = cloudCount == 1 ? cityPopulations[i] : 0;
            }

            System.out.println(String.format("7th Loop: %d", sw.getElapsedSinceLastRecord()));
            sw.record();

            long maxPerCloud = 0;
            for(int i = 0; i < y.size(); i++){
                long loc = y.get(i);
                long range = r.get(i);
                long left = loc - range;
                long right = loc + range;
                int leftIndex = positionToIndex.get(left);
                int rightIndex = positionToIndex.get(right + 1);
                long maxThisCloud = 0;

                for(int j = leftIndex; j < rightIndex; j++){
                    maxThisCloud += clouded[j];
                }

                if(maxPerCloud < maxThisCloud){
                    maxPerCloud = maxThisCloud;
                }
            }

            System.out.println(String.format("8th Loop: %d", sw.getElapsedSinceLastRecord()));
            sw.record();

            return sunnyCount + maxPerCloud;
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
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
