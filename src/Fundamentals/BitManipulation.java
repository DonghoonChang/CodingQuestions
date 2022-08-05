package Fundamentals;

public class BitManipulation {
    public static int isolateFirstSetBit(int number){
        System.out.println(String.format("%64s", Integer.toBinaryString(number)));
        System.out.println(String.format("%64s", Integer.toBinaryString(~(number - 1))));
        System.out.println(String.format("%64s", Integer.toBinaryString(number & ~(number - 1))));

        return number & ~(number - 1);
    }

    public static int invertTrailingZeros(int number){
        System.out.println(String.format("%64s", Integer.toBinaryString(number)));
        System.out.println(String.format("%64s", Integer.toBinaryString((number - 1))));
        System.out.println(String.format("%64s", Integer.toBinaryString(number | (number - 1))));

        return number | (number - 1);
    }

    public static int moduloPowerOfTwo(int number, int power){
        int pow = (int) Math.pow(2, power);
        System.out.println(String.format("%64s", Integer.toBinaryString(number)));
        System.out.println(String.format("%64s", Integer.toBinaryString((pow))));
        System.out.println(String.format("%64s", Integer.toBinaryString(number & (pow - 1))));

        return number & (pow - 1);
    }

    public static void main(String[] args){
//        isolateFirstSetBit(23);
//        invertTrailingZeros(23);
        moduloPowerOfTwo(1234, 3); // 23 % 8
    }
}
