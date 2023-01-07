package AdvancedJava.LambdaExpression;

import java.util.*;
import java.util.stream.Collectors;

public class LambdaExpression2 {
    public static void main(String[] args) {

        //map
        int[] arr = new int[10];
        List<Integer> list = new ArrayList<>();
fillArr(arr);
fillList(list);

      arr = Arrays.stream(arr).map(a -> a*2).toArray();
       list = list.stream().map(a -> a*2).toList();

       arr = Arrays.stream(arr).map(a -> 3).toArray();
       list = list.stream().map(a -> 3).toList();

        System.out.println(Arrays.toString(arr));
        System.out.println(list);

// filter method
        int[] arr2 = new int[10];
        List<Integer> list2 = new ArrayList<>();

        fillArr(arr2);
fillList(list2);

        arr2 = Arrays.stream(arr2).filter(a -> a % 2 == 0).toArray();
list2 = list2.stream().filter(a -> a % 2 == 0).toList();

        System.out.println( "filter : " + Arrays.toString(arr2));
        System.out.println("filter : " + list2);

        // forEach
        System.out.print("forEach1 : ");
        Arrays.stream(arr2).forEach(a -> System.out.print(a + "  "));   //Arrays.stream(arr2).forEach(System.out::println);
        System.out.println();
        System.out.print("forEach2 : ");
list2.forEach(a -> System.out.print(a + "  "));           //list2.stream().forEach(a -> System.out.println(a));
        System.out.println();

         //reduce
        int[] arr3 = new int[10];
List<Integer> list3 = new ArrayList<>();

fillArr(arr3);
fillList(list3);

 int sum1 = Arrays.stream(arr3).reduce( (acc, b) -> acc + b).getAsInt();    // произведение всех элементов         int sum1 = Arrays.stream(arr3).reduce(2 (acc, b) -> acc + b);
 int sum2 = list3.stream().reduce((acc, b) -> acc * b).get();              // acc = first element in array           2 - number with which start counting of elements
                                                                                                                    // default acc value = first element
        System.out.println("reduce1 : " + sum1);
        System.out.println("reduce2 : " + sum2);

// custom1
        int[] arr4 = new int[10];
        fillArr(arr4);

        arr4 = Arrays.stream(arr4).filter(a -> a % 2 != 0).map(a -> a * 2).toArray();

        System.out.println(Arrays.toString(arr4));

        //custom2
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(5);

        System.out.println(set);

       set = set.stream().map(a -> a *3).collect(Collectors.toSet());

        System.out.println(set);
    }
    private static void fillArr(int[] arr){
for(int i = 0; i<10; i++){
    arr[i] = i + 1;
}
    }
    private static void fillList(List<Integer> list){
for(int i = 0; i < 10; i++){
    list.add(i + 1);
}
    }
}
