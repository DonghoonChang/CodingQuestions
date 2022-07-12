import java.io.*;
import java.math.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Test {

    class Result {

        /*
         * Complete the 'authEvents' function below.
         *
         * The function is expected to return an INTEGER_ARRAY.
         * The function accepts 2D_STRING_ARRAY events as parameter.
         */

        static final String setEvent = "setPassword";
        static final String authEvent = "authorize";

        static int charSetSize = 62;
        static List<Character> charSet = new ArrayList<>();
        static int[] powersOfPModM = new int[10];
        static Map<Character, List<Integer>> charPowers = new HashMap<>();
        static Set<Integer> hashes = new HashSet<Integer>();

        // charset a-z A-Z 0-9 26 + 26 + 10 = 62

        // notes: 1 <= s <= 9
        public static List<Integer> authEvents(List<List<String>> events) {
            int p = 103;
            int mod = 1000000007;

            initCharSet();
            List<Integer> results = new ArrayList<>();

            // O(1): Pre-calculate powers of p(103)
            for(int power = 0; power <= 9; power++){
                if (power == 0){
                    powersOfPModM[power] = 1;
                    continue;
                }

                int prevPower = powersOfPModM[power - 1];
                BigInteger prevPowerBig = BigInteger.valueOf(prevPower);
                BigInteger pBig = BigInteger.valueOf(p);
                BigInteger modBig = BigInteger.valueOf(mod);

                powersOfPModM[power] =  prevPowerBig.multiply(pBig).mod(modBig).intValue();
            }

            // O(1): pre-calculate powers of p * (int) characer
            for(char ch: charSet){
                List<Integer> multiplications = new ArrayList<>();

                for(int i = 0; i <= 9; i++){
                    BigInteger powerBig = BigInteger.valueOf(powersOfPModM[i]);
                    BigInteger charBig = BigInteger.valueOf((int) ch);
                    BigInteger modBig = BigInteger.valueOf(mod);

                    int value = powerBig.multiply(charBig).mod(modBig).intValue();
                    multiplications.add(value);
                }

                charPowers.put(ch, multiplications);
            }

            for(List<String> event: events){
                String eventType = event.get(0);

                if(isSetEvent(eventType)){
                    String password = event.get(1);
                    int hash = getHash(password);
                    hashes.add(hash);

                    for(char ch: charSet){
                        String appended = password + ch;
                        hash = getHash(appended);
                        hashes.add(hash);
                    }
                } else {
                    int hash = Integer.parseInt(event.get(1));

                    if(hashes.contains(hash)){
                        results.add(1);
                    } else{
                        results.add(0);
                    }
                }
            }

            return results;

        }

        private static void initCharSet(){
            charSet.add('0');
            charSet.add('1');
            charSet.add('2');
            charSet.add('3');
            charSet.add('4');
            charSet.add('5');
            charSet.add('6');
            charSet.add('7');
            charSet.add('8');
            charSet.add('9');

            charSet.add('a');
            charSet.add('b');
            charSet.add('c');
            charSet.add('d');
            charSet.add('e');
            charSet.add('f');
            charSet.add('g');
            charSet.add('h');
            charSet.add('i');
            charSet.add('j');
            charSet.add('k');
            charSet.add('l');
            charSet.add('m');
            charSet.add('n');
            charSet.add('o');
            charSet.add('p');
            charSet.add('q');
            charSet.add('r');
            charSet.add('s');
            charSet.add('t');
            charSet.add('u');
            charSet.add('v');
            charSet.add('w');
            charSet.add('x');
            charSet.add('y');
            charSet.add('z');

            charSet.add('A');
            charSet.add('B');
            charSet.add('C');
            charSet.add('D');
            charSet.add('E');
            charSet.add('F');
            charSet.add('G');
            charSet.add('H');
            charSet.add('I');
            charSet.add('J');
            charSet.add('K');
            charSet.add('L');
            charSet.add('M');
            charSet.add('N');
            charSet.add('O');
            charSet.add('P');
            charSet.add('Q');
            charSet.add('R');
            charSet.add('S');
            charSet.add('T');
            charSet.add('U');
            charSet.add('V');
            charSet.add('W');
            charSet.add('X');
            charSet.add('Y');
            charSet.add('Z');

        }

        public static boolean isSetEvent(String event){
            return event.equals(setEvent);
        }

        public static int getHash(String password){
            int l = password.length();
            BigInteger hash = BigInteger.ZERO;
            BigInteger c = BigInteger.valueOf(1000000007);

            int power = l - 1;
            for(char ch: password.toCharArray()){
                BigInteger a = BigInteger.valueOf(powersOfPModM[power]);
                BigInteger b = BigInteger.valueOf((int) ch);
                hash = hash.add(a.multiply(b)).mod(c);
                power--;
            }

            return hash.mod(c).intValue();
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int eventsRows = Integer.parseInt(bufferedReader.readLine().trim());
        int eventsColumns = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> events = new ArrayList<>();

        IntStream.range(0, eventsRows).forEach(i -> {
            try {
                events.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> result = Result.authEvents(events);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
