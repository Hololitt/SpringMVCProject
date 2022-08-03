package AdvancedJava.Multithreading;

public class Synchronized extends Thread{
    private int counter;
    public static void main(String[] args) throws InterruptedException{
        Synchronized s = new Synchronized();
        s.dowork();
    }
public synchronized void increment(){
        counter++;
}
    public void dowork() throws InterruptedException {
Thread thread1 = new Thread(new Runnable() {
    @Override
    public void run() {
        for(int i = 0; i<1000; i++){
            increment();
        }
    }
});
Thread thread2 = new Thread(new Runnable() {
    @Override
    public void run() {
        for(int i = 0; i<1000; i++){
            increment();
        }
    }
});
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(counter);
    }
}

