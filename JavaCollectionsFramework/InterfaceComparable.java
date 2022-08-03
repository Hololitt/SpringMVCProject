package AdvancedJava.JavaCollectionsFramework;
import java.util.*;
public class InterfaceComparable {
    public static void main(String[] args) {

List<Persons> peoplelist = new ArrayList();
Set<Persons> personSet = new TreeSet<>();

addElements(peoplelist);
addElements(personSet);

Collections.sort(peoplelist);

        System.out.println(peoplelist);
        System.out.println(personSet);


    }
    private static void addElements(Collection collection){
collection.add(new Persons(3, "Bob"));
collection.add(new Persons(2,"Slave"));
collection.add(new Persons(4, "Billy"));
collection.add(new Persons(1, "Darkholme"));
    }
}
class Persons implements Comparable<Persons>{
    private int id;
    private String name;

    Persons(int id, String name){
        this.id = id;
        this.name = name;
    }
public int getId(){
        return id;
}
public String getName(){
        return name;
}
    @Override
    public String toString() {
        return "Persons{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persons persons = (Persons) o;
        return id == persons.id && Objects.equals(name, persons.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public int compareTo(Persons o){
        if (this.name.length() > o.getName().length()) {
            return 1;
        } else if (this.name.length() < o.getName().length()) {
            return -1;
        } else {
            return 0;
        }
    }

}