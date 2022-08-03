package AdvancedJava.JavaCollectionsFramework;
import java.util.*;

public class HashCodeEquals {
    public static void main(String[] args) {

Set<Person> set = new HashSet<>();
Map<Person, String> map = new HashMap<>();

Person person1 = new Person(1,"Slave");
Person person2 = new Person(2, "Slave");


map.put(person1,"123");
map.put(person2, "123");

set.add(person1);
set.add(person2);

        System.out.println(map);
        System.out.println(set);

        System.out.println(person1.equals(person2));
        System.out.println(person2.hashCode());

        //сравнение через hashCode - не является точным          точное сравнение - equals
    }
}
class Person {
    final int id;
    final String name;

    Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public String toString(){
        return id +" " +  name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
