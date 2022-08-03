package AdvancedJava.JavaCollectionsFramework;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class JavaQueue {
    public static void main(String[] args) {
        /*
        List list = new ArrayList<>();
for(int i = 0; i<10; i++){
  list.add(new Human(i));
}
Queue<Human> people = new ArrayBlockingQueue<>(list.size());
for(int i = 0; i<list.size(); i++){
    people.add((Human) list.get(i));
}
        for(Human human :  people){
            System.out.println(human);
        }

         */

        Human human = new Human(1);
        Human human1 = new Human(2);
        Human human2 = new Human(3);
        Human human3 = new Human(4);



        Queue<Human> people = new ArrayBlockingQueue<>(4);
        people.add(human);             //Throws exceptions                Return special value
        people.add(human3);            //add(e)                           offer(e)
        people.add(human1);            //remove()                         poll()
        people.add(human2);            //element()                        peek()

for(Human enter : people){
    System.out.println(enter);
}
        System.out.println("s");
        System.out.println(people.remove());
        System.out.println(people.peek());
    }
}
class Human{
    private int id;
   public Human(int id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "Human{" +
                "id=" + id +
                '}';
    }
}