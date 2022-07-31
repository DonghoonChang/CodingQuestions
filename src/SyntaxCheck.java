public class SyntaxCheck {

    public static void main(String[] args){
        boolean[] values = {false, true};

        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++){
                boolean a = values[i];
                boolean b = values[j];

                System.out.println(String.format("%B ^ %B = %B", a, b, a ^ b));
            }
        }
    }
}
