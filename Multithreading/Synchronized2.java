package AdvancedJava.Multithreading;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
public class Synchronized2 {
    public static void main(String[] args) {
Work work = new Work();
work.main();

    }
}
class Work{
Object lock1 = new Object();
Object lock2 = new Object();
    Random random = new Random();
List<Integer> list1 = new ArrayList<>();
List<Integer> list2 = new ArrayList<>();
Thread thread = new Thread();
public void addToList1(){
    synchronized (lock1) {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list1.add(random.nextInt(100));
    }
}
public void addToList2() {
    synchronized (lock2) {

        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list2.add(random.nextInt(100));
    }
}
public void startWork(){
    for(int i = 0; i<1000; i++){
        addToList1();
        addToList2();
    }
}
public void main(){
   long before = System.currentTimeMillis();

   Thread thread1 = new Thread(new Runnable() {
       @Override
       public void run() {
           startWork();
       }
   });
Thread thread2 = new Thread(new Runnable() {
    @Override
    public void run() {
        startWork();
    }
});
thread1.start();
thread2.start();

try{
    thread1.join();
    thread2.join();
}catch(InterruptedException e){
    e.printStackTrace();
}

   long after = System.currentTimeMillis();

    System.out.println(after - before);
    System.out.println(list1.size());
    System.out.println(list2.size());

}

}