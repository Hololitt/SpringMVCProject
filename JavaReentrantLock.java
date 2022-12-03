package AdvancedJava.Multithreading;

import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JavaReentrantLock {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter count of numbers");
            int countOfNumbers = scanner.nextInt();
            Thread.sleep(1000);
            Task task = new Task(countOfNumbers);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    task.firstThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    task.secondThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        long startTime = System.currentTimeMillis();
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

    task.enterCount();
        long endTime = System.currentTimeMillis();

        System.out.println("Program worked " + (endTime-startTime) + " ms");
    }
    static class Task {
        Task(int number){

            this.number = number;
        }
        private int count;
        private final int number;
        private final Lock lock = new ReentrantLock();

        public void increment() {

            for (int i = 0; i < number; i++) {
                count++;
            }
        }
        public synchronized void  firstThread() throws InterruptedException {

            System.out.println("first Thread is working...");
            Thread.sleep(2000);
            lock.lock();
            increment();
            lock.unlock();
            System.out.println("first Thread stopped working");
            Thread.sleep(2000);
        }
        public synchronized void secondThread() throws InterruptedException{
            System.out.println("second Thread is working...");
            Thread.sleep(2000);
            lock.lock();
            increment();
            lock.unlock();
            System.out.println("second Thread stopped working");
        }
        public void enterCount() throws InterruptedException {
            if(count == 0){
                System.out.println("To small number");
            }else if(number > 20000){
                System.out.println("To big number");
            }
            else{
                Thread.sleep(3000);
            System.out.println(count);
        }
    }
}}