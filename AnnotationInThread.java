package AdvancedJava.AnotherThemes;

public class AnnotationInThread {
    public static void main(String[] args) {
        EnterTheWord thread = new EnterTheWord();
thread.start();
        System.out.println("main class");
    }
}
class EnterTheWord extends Thread{
    @Override
    public void run(){          // cant be "int number" in options
        try{
                Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("second class");
    }
}