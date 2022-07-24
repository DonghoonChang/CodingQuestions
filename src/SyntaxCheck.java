import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SyntaxCheck {

    public static void main(String[] args){
        String test = "abcde";
        for(int i = 0; i < test.length(); i ++){
            String pattern = test.substring(0, i) + "*" + test.substring(i + 1);
            System.out.println(pattern);
        }
    }
}
