package ProblemSolving.Algorithms.Search;

import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

public class GridlandMetro {

    class Result {

        public static BigInteger gridlandMetro(int n, int m, int k, List<List<Integer>> tracks) {
            // Space O(n)
            // Time O(n)
            long cells_with_track = 0;
            Map<Integer, List<List<Long>>> tracks_by_row = new HashMap<>();
            for(List<Integer> track: tracks){
                int row = track.get(0);
                long c1 = track.get(1);
                long c2 = track.get(2);
                long start = Math.min(c1, c2);
                long end = Math.max(c1, c2);

                List<List<Long>> row_tracks = tracks_by_row.containsKey(row) ? tracks_by_row.get(row) : new ArrayList<>();
                List<Long> track_without_row_number = new ArrayList<>();
                track_without_row_number.add(start);
                track_without_row_number.add(end);

                row_tracks.add(track_without_row_number);
                tracks_by_row.put(row, row_tracks);
            }

            for(List<List<Long>> tracks_in_row: tracks_by_row.values()){
                cells_with_track += getOccupiedCellsInARow(tracks_in_row);
            }

            return BigInteger.valueOf(n).multiply(BigInteger.valueOf(m)).subtract(BigInteger.valueOf(cells_with_track));
        }

        private static long getOccupiedCellsInARow(List<List<Long>> tracks){
            List<List<Long>> merged = new ArrayList<>();
            int count = 0;

            for(int i = 0; i < tracks.size(); i++){
                List<Long> first = tracks.get(i);
                long c1 = first.get(0);
                long c2 = first.get(1);
                long start_first = Math.min(c1, c2);
                long end_first = Math.max(c1, c2);
                boolean canBeMerged = false;

                for(int j = i + 1; j < tracks.size(); j++){
                    long cA = tracks.get(j).get(0);
                    long cB = tracks.get(j).get(1);
                    long start_second = Math.min(cA, cB);
                    long end_second = Math.max(cA, cB);

                    // first segment comes first
                    boolean isConnectable_first = start_first <= start_second && start_second <= end_first;
                    // second segment comes first
                    boolean isConnectable_second = start_second <= start_first && start_first <= end_second;
                    boolean isConnectable = isConnectable_first || isConnectable_second;

                    if(isConnectable){
                        long start_combined = Math.min(start_first, start_second);
                        long end_combined = Math.max(end_first, end_second);

                        List<Long> combined = new ArrayList<>();
                        combined.add(start_combined);
                        combined.add(end_combined);

                        tracks.set(j, combined);
                        canBeMerged = true;

                        break;
                    }
                }

                if(!canBeMerged){
                    merged.add(first);
                }
            }

            for(List<Long> track: merged){
                count += (track.get(1) - track.get(0) + 1);
            }

            return count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(
                GridlandMetro.class.getProtectionDomain().getCodeSource().getLocation().getPath() +
                        GridlandMetro.class.getPackageName().replace(".", "/") +
                        "\\Input.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        int k = Integer.parseInt(firstMultipleInput[2]);

        List<List<Integer>> track = new ArrayList<>();

        IntStream.range(0, k).forEach(i -> {
            try {
                track.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        BigInteger result = Result.gridlandMetro(n, m, k, track);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
