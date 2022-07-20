package Hackerrank.Algorithms.Greedy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class GreedyFlorist {

    // Complete the getMinimumCost function below.
    static int getMinimumCost(int numberOfFriends, int[] costs) {
        int totalFlowers = costs.length;
        Arrays.sort(costs);

        for (int i = 0, j = totalFlowers - 1; i < j; i++, j--) {
            int temp = costs[i];
            costs[i] = costs[j];
            costs[j] = temp;
        }

        int cost = 0;
        int multiplier = 0;

        for (int i = 0; i < totalFlowers; i++) {
            if (i % numberOfFriends == 0) {
                multiplier++;
            }

            int currCost = costs[i];
            cost += multiplier * currCost;
        }

        return cost;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] c = new int[n];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int minimumCost = getMinimumCost(k, c);

        bufferedWriter.write(String.valueOf(minimumCost));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}