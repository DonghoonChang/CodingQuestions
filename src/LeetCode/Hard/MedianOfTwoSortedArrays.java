package LeetCode.Hard;

public class MedianOfTwoSortedArrays {

    /*
        Runtime: 2 ms, faster than 100.00% of Java online submissions for Median of Two Sorted Arrays.
        Memory Usage: 43 MB, less than 95.12% of Java online submissions for Median of Two Sorted Arrays.
     */
    public static double findMedianSortedArrays(int[] a, int[] b) {
        if (a.length > b.length) {
            return findMedianSortedArrays(b, a);
        }

        if (a.length == 0) {
            int middle = b.length / 2;

            if (b.length % 2 == 0) {
                return (double) (b[middle - 1] + b[middle]) / 2.0;
            } else {
                return b[middle];
            }
        }

        int aL = a.length;
        int bL = b.length;

        int half = (aL + bL) / 2;
        int aMax = aL - 1;
        int aMin = 0;
        int aCurr = 0;
        int bCurr = half - (aCurr + 1) - 1;


        while (true) {
            boolean condA = extendedGet(a, aCurr) <= extendedGet(b, bCurr + 1);
            boolean condB = extendedGet(b, bCurr) <= extendedGet(a, aCurr + 1);

            if (condA && condB) {
                break;
            }

            if (!condA) { // reduce A
                aMax = aCurr;
                aCurr = getNewMiddle(aMin, aMax, true);
                bCurr = half - (aCurr + 1) - 1;
                continue;
            }

            aMin = aCurr;
            aCurr = getNewMiddle(aMin, aMax, false);
            bCurr = half - (aCurr + 1) - 1;
            continue;
        }

        if ((aL + bL) % 2 == 0) {
            int medianA = aCurr == -1 ? b[bCurr] : bCurr == -1 ? a[aCurr] : Math.max(a[aCurr], b[bCurr]);
            int medianB = aCurr + 1 == aL ? b[bCurr + 1] : bCurr + 1 == bL ? a[aCurr + 1] : Math.min(a[aCurr + 1], b[bCurr + 1]);

            return ((double) (medianA + medianB) / 2.0);
        }

        return aCurr + 1 == aL ? b[bCurr + 1] : Math.min(a[aCurr + 1], b[bCurr + 1]);
    }

    private static int getNewMiddle(int min, int max, boolean isDecreasing){
        if(max == 0){
            return -1;
        }

        boolean isEven = (min + max) % 2 == 0;
        if(isEven){
            return (min + max) / 2;
        }

        if(isDecreasing){
            return (min + max) / 2; // odd, decreasing
        }

        return (min + max + 1) / 2; // odd, increasing
    }

    private static int extendedGet(int[] arr, int index){
        if (index >= arr.length){
            return Integer.MAX_VALUE;
        }

        if (index < 0){
            return Integer.MIN_VALUE;
        }

        return arr[index];
    }

    public static void main(String[] args) {
        int[] arr = {1};
        int[] arr2 = {2};

        double val = findMedianSortedArrays(arr, arr2);
        System.out.println(val);
    }
}
