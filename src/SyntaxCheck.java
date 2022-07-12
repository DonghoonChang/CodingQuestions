import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SyntaxCheck {

    public static void main(String[] args){

        String a = "kc";
        String b = "iu";

        List<String> lst = new ArrayList<>();
        lst.add(a);
        lst.add(b);

        Collections.sort(lst);
        System.out.println(lst);
    }
}
