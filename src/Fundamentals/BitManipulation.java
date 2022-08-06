package Fundamentals;

public class BitManipulation {
    public static long isolateFirstSetBit(int number){
        return number & ~(number - 1);
    }

    public static long invertTrailingZeros(int number){
        return number | (number - 1);
    }

    public static long moduloPowerOfTwo(int number, int power){
        int pow = (int) Math.pow(2, power);

        return number & (pow - 1);
    }

    public static long multiply(long a, long b){
        long sum = 0;
        while(a != 0){
            if((a & 1) != 0){
                sum = add(sum, b);
            }

            a >>>= 1;
            b <<= 1;
        }

        return sum;
    }

    public static long add(long a, long b){
        long sum = 0, carryIn = 0, k = 1, tempA = a, tempB = b;
        while(tempA != 0 && tempB != 0){
            long ak = a & k, bk = b & k;
            long carryout = (ak & bk) | (ak & carryIn) | (bk & carryIn);

            sum |= ak ^ bk ^ carryIn;
            carryIn = carryout << k;
            k <<= 1;
            tempA >>>= 1;
            tempB >>>= 1;
        }

        return sum | carryIn;
    }

    public static long divide(long a, long b){
        long quotient = 0;
        long power = 32;
        long bPower = b << power;
        while(a >= b){
            while(bPower > a){
                bPower >>>= 1;
                power--;
            }

            quotient += 1L << power;
            a -= bPower;
        }

        return quotient;
    }

    public static void main(String[] args){
//        isolateFirstSetBit(23);
//        invertTrailingZeros(23);
        moduloPowerOfTwo(1234, 3); // 23 % 8
    }
}
