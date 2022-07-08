import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws IOException {

        final String problem = "GridlandMetro";
        final String name = String.join(".", new String[]{"ProblemSolving", "Algorithms", "Search", problem});
        final String main = "main";

        try {
            System.out.println(String.format("Solving problem: %s", name));
            System.out.println("Input from next line:");
            Class<?> solution = Class.forName(name);
            Method method = solution.getMethod(main, String[].class);
            method.invoke(null, (Object) args);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
