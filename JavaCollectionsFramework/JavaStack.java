package AdvancedJava.JavaCollectionsFramework;
import java.util.EmptyStackException;
import java.util.Stack;
public class JavaStack {
    public static void main(String[] args) {
        Stack<Integer> numbers = new Stack<>();
        numbers.push(1);
        numbers.push(4512);
        numbers.push(5);

        while (!numbers.empty()) {
            System.out.println(numbers.pop());
        }
        try {
            System.out.println(numbers.pop());
        } catch (EmptyStackException e) {
            System.out.println("cell is empty");
        }
    }
}