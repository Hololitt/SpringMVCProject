package AdvancedJava.JavaCollectionsFramework;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
public class InterfaceComparator {
    public static void main(String[] args) {
/* StringLengthComparator slc = new StringLengthComparator();

List<String> list = new ArrayList<>();
list.add("qwrqwr");
list.add("w");
list.add("wsdg");
list.add("jkFHujfd");

Collections.sort(list, new StringLengthComparator());
        System.out.println(list);

    }
}
class StringLengthComparator implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {

        if(o1.length()>o2.length()){
                return 1;
        }else if(o1.length()<o2.length()){
            return -1;
        }else{
            return 0;
        }

 */


        Peoples p = new Peoples(1," Billy");
        Peoples p1 = new Peoples(2, " Van");
        Peoples p2 = new Peoples(3, " Slave");
        Peoples p3 = new Peoples(1," asf");

        List<Peoples> list = new ArrayList<>();
        list.add(p);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        Collections.sort(list, new Comparator<Peoples>() {
            @Override
            public int compare(Peoples o1, Peoples o2) {
if(o1.getId()>o2.getId()){
    return 1;
}else if(o1.getId()<o2.getId()){
    return -1;
}else{
    return 0;
}
            }
        });
        System.out.println(list);

    }

}
class Peoples{
    Peoples(Integer id, String name){
        this.name = name;
        this.id = id;
    }
    private String name;
    private int id;

    public int getId() {
        return id;
    }

    public String toString(){
        return id + name;
    }
}