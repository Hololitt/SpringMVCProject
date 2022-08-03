package AdvancedJava.JavaCollectionsFramework;
import java.util.*;

public class MultipleSet {
    public static void main(String[] args) {
        /*
        Set<String> hashset = new HashSet<>();
        hashset.add("Slave");
        hashset.add("Dungeon master");

        System.out.println(hashset.contains("FKfblj"));
        for(String name : hashset){
            System.out.println(name);
        }
*/

        Set<Integer> hashSet = new HashSet<>();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);
        hashSet.add(4);
        hashSet.add(5);

Set<Integer> hashSet2 = new HashSet<>(hashSet);
hashSet2.add(6);
hashSet2.add(7);
hashSet2.add(8);
hashSet2.add(9);
hashSet2.add(1);
 hashSet2.addAll(hashSet);

      for(int count : hashSet2){
            System.out.println(count);
        }

        Set<Integer> intersection = new HashSet<>(hashSet);
        intersection.retainAll(hashSet2);
        System.out.println(intersection);         //пересичение


        Set<Integer> difference = new HashSet<>(hashSet);
        difference.removeAll(hashSet2);
    }
}
