package AdvancedJava.JavaCollectionsFramework;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;
public class LinkedMapTreeMap {
    public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);

        Map<Integer, String> map = new HashMap<>();                  //порядок не гарантируется
        Map<Integer, String> linkedHashMap = new LinkedHashMap<>();  //выводет в порядке изначального ввода
        Map<Integer, String> treeMap = new TreeMap<>();              //вывод в порядке интеджера

        int numberofmethod = scanner.nextInt();
        switch (numberofmethod) {
            case 1 -> testMap(map);
            case 2 -> testMap(linkedHashMap);
            case 3 -> testMap(treeMap);
            default -> System.out.println("not recognized number");
        }

    }
    public static void testMap(Map<Integer, String> map){
map.put(1, "Slave");
map.put(2, "Dungeon master");
map.put(3, "Master");
map.put(4, "Van");
map.put(5, "Billy");

for(Map.Entry<Integer, String> entry : map.entrySet()){
    System.out.println(entry.getKey() + " : " + entry.getValue());
}
    }
}
