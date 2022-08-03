package AdvancedJava.JavaCollectionsFramework;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class JavaIterator {
    public static void main(String[] args) {
        List<Integer> ar = new ArrayList();
        ar.add(5);
        ar.add(1);
        ar.add(4);
        ar.add(10);
        Iterator iterator = ar.iterator();
        int idx = 0;
        while(iterator.hasNext()){
            System.out.println(iterator.next());

            if(idx == 0)    //0 is number of element
                iterator.remove();
            idx++;
        }
        System.out.println(ar);
    }
}
