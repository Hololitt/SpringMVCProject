package AdvancedJava.Multithreading;

public class MultyStream {
    public static void main(String[] args) {
MyThread mt1 = new MyThread();
mt1.start();
MyThread mt2 = new MyThread();
mt2.start();
        System.out.println("something");
    }
}
class MyThread extends Thread {
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}