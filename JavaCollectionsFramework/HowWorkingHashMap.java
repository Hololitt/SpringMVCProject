package AdvancedJava.JavaCollectionsFramework;
import java.util.*;
public class HowWorkingHashMap {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<Integer,String> map = new HashMap();

         map.put(1,"name");
        map.put(2,"jDF");
        map.put(3,"dishgl");

        System.out.println(map.hashCode());
        int hash = map.hashCode();
        int index = hash & (3-1);
        System.out.println(index);
int number = scanner.nextInt();

        int hashcode = map.get(2).hashCode();
            int numberofbox = hashcode & (3-1);
        System.out.println(numberofbox);

    }
}
