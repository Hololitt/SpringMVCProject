package AdvancedJava.Multithreading;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(2);
        for(int i = 0; i<3; i++)
            es.submit(new Worker(i));
        es.shutdown();
        es.awaitTermination(60, TimeUnit.SECONDS);


    }
}
class Worker implements Runnable{
    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();
    List<Integer> list3 = new ArrayList<>();

    private Integer id;

    Worker(int id){
        this.id = id;
    }

    @Override
    public void run() {
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
list1.add(id);
        list2.add(id);
        list3.add(id);
        enterListst();
    }
    public void enterListst(){
        for(Integer integer : list1){
            System.out.println(integer);
        }
        for(Integer integer : list2){
            System.out.println(integer);
        }
        for(Integer integer : list3){
            System.out.println(integer);
        }
    }
}