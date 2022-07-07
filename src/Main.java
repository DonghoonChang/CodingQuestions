import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws IOException {

        final String solutions = "Solutions";
        final String problem = "SherlockAndAnagrams";
        final String main = "main";

        try {
            System.out.println("Input from next line:");
            Class<?> solution = Class.forName(String.format("%s.%s", solutions, problem));
            Method method = solution.getMethod(main, String[].class);
            method.invoke(null, (Object) args);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
