package AdvancedJava.JavaCollectionsFramework;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
public class MyHashMap {
    public static void main(String[] args) {
/*
        Map<Integer, String> map = new HashMap<>();

        map.put(1,"a");
        map.put(2,"b");
        map.put(3,"c");
        System.out.println(map);
        map.put(3, "d");
        System.out.println(map);
        System.out.println(map.get(1));

*/

Map<Integer, String> map1 = new HashMap<>();

map1.put(10, "i");
map1.put(20, "q");

for(Map.Entry<Integer, String> entry : map1.entrySet()){
    System.out.println(entry.getKey() + " : " + entry.getValue());
}

    }
}
