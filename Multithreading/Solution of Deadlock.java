import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {
    public static void main(String[] args) throws InterruptedException {

        Runner runner = new Runner();
Thread thread1 = new Thread(() -> {
    try {
        runner.firstThread();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
});
Thread thread2 = new Thread(() -> {
    try {
        runner.secondThread();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
});
thread1.start();
thread2.start();

thread1.join();
thread2.join();

runner.finished();
    }
}
class Runner {
    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();
   private final Random random = new Random();
    Account ac1 = new Account();
    Account ac2 = new Account();
   private boolean firstLockTaken = false;
    private boolean secondLockTaken = false;
    public void takeLocks(Lock lock1, Lock lock2) throws InterruptedException {
        while(true){
            try {
                firstLockTaken = lock1.tryLock();
                secondLockTaken = lock2.tryLock();
            }finally {
                if(firstLockTaken && secondLockTaken){
                    return;
                }
                if(firstLockTaken){
                    lock1.unlock();
                }
                if(secondLockTaken){
                    lock2.unlock();
                }
                Thread.sleep(5);
            }
            }
    }
    public void firstThread() throws InterruptedException{
takeLocks(lock1,lock2);
int countOfMoneyForTransfer;
        for (int i = 0; i < 1000; i++) {
            Account.transfer(ac1, ac2, random.nextInt(100));
        }
        lock1.unlock();
        lock2.unlock();
    }
    public void secondThread() throws InterruptedException {
takeLocks(lock1,lock2);
        for (int i = 0; i < 1000; i++) {
            Account.transfer(ac2, ac1, random.nextInt(100));
        }
        lock1.unlock();
        lock2.unlock();
    }
    public void finished() throws InterruptedException{
        Thread.sleep(1000);
        System.out.println(ac1.getBalance());
        Thread.sleep(1000);
        System.out.println(ac2.getBalance());
        Thread.sleep(1000);
        System.out.println("Total balance : " + (ac1.getBalance() + ac2.getBalance()));
    }
}
class Account{
private int balance = 50000;
public void deposit(int amount){
    balance += amount;
}
public void withdraw(int amount){
    balance -= amount;
}
public int getBalance(){
    return balance;
}
public static void transfer(Account ac1, Account ac2, int amount){
    ac1.withdraw(amount);
    ac2.deposit(amount);
}
}
