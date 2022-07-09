import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws IOException {

        final String problem = "HuffmanDecoding";
        final String PROBLEM_SOLVING = "ProblemSolving";
        final String DATA_STRUCTURES = "DataStructures";
        final String ALGORITHMS = "Algorithms";
        final String SEARCH = "Search";
        final String ARRAYS = "Arrays";
        final String TREES = "Trees";
        final String LinkedLists = "LinkedLists";

        final String name = String.join(".", new String[]{DATA_STRUCTURES, TREES, problem});
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
