import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SyntaxCheck {

    public static void main(String[] args){

        for(int i = 1; i <= 100; i++){
            System.out.println(String.format("%d:%d", i, countPrimeFactors(i)));
        }

    }

    private static int countPrimeFactors(int n){
        int next = n;
        int prime = 2;
        int count = 0;
        while (next != 1 && prime <= next){
            if(next % prime == 0){
                do {
                    count++;
                    next = next / prime;
                } while (next % prime == 0);
            }

            prime++;
        }

        return count;
    }
}
