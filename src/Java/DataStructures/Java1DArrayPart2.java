package Java.DataStructures;

import java.util.Scanner;

public class Java1DArrayPart2 {


    static boolean[] visitMemo;

    public static boolean canWin(int leap, int[] game) {
        init(game);

        return canWin(leap, 0, game);
    }

    private static boolean canWin(int leap, int index, int[] game) {

        if (index > game.length - 1) {
            return true;
        }

        if (index < 0) {
            return false;
        }

        if (visitMemo[index]) {
            return false;
        }

        if (game[index] != 0) {
            return false;
        }

        visitMemo[index] = true;
        boolean found = canWin(leap, index - 1, game) || canWin(leap, index + 1, game) || canWin(leap, index + leap, game);

        if (!found) {
            visitMemo[index] = true;
        }

        return found;
    }

    private static void init(int[] game) {
        int gameSize = game.length;
        visitMemo = new boolean[gameSize];
        for (int i = 0; i < gameSize; i++) {
            int cell = game[i];

            if (cell != 0) {
                visitMemo[i] = true;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int q = scan.nextInt();
        while (q-- > 0) {
            int n = scan.nextInt();
            int leap = scan.nextInt();

            int[] game = new int[n];
            for (int i = 0; i < n; i++) {
                game[i] = scan.nextInt();
            }

            System.out.println((canWin(leap, game)) ? "YES" : "NO");
        }
        scan.close();
    }
}