import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;

// 14.02.2023
public class Main {
    static <T> void start(T t) throws InvocationTargetException, IllegalAccessException {
        Method[] method = t.getClass().getDeclaredMethods();
        for(Method methods : method){
            if(methods.getName().equals("getValue")){
                if((int)methods.invoke(t) == 0){
                    list.remove(t);
                    System.out.println("finish");
                }
            } else if(methods.getName().equals("check")){
                methods.invoke(t);
            }
        }
    }
    public static ArrayList<Word> list = new ArrayList<>();
    public static void addWord(String word, String translation){
        list.add(new Word(word, translation));
    }
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Random random = new Random();
Scanner scanner = new Scanner(System.in);
        System.out.println("how many words you want to learn?");
        int countOfWordForLearning = scanner.nextInt();
          for(int i = 0; i<countOfWordForLearning; i++){
              addWord(scanner.nextLine(), scanner.nextLine());
          }

       while(!list.isEmpty()){
           start((list.get(random.nextInt(0, list.size()))));
       }
        System.out.println("Program is finished");
    }
}
class Word {
    public final String reset = "\u001B[0m";
    public final String green = "\u001B[32m";
    public final String red = "\u001B[31m";
    CountDownLatch countDownLatch = new CountDownLatch(5);
    Scanner scanner = new Scanner(System.in);
    private final String word;
    private final String translation;
    Word(String word, String translate){
        this.word = word;
        this.translation = translate;
    }
    public void check(){
        System.out.println("write translation to word "+ word);
        if(Objects.equals(scanner.nextLine(), translation)){
            System.out.println(green+"True!"+reset);
            countDownLatch.countDown();
        }else{
            System.out.println(red+"False!"+reset);
            System.out.println("Write answer was "+translation);
        }
    }
    public int getValue(){
        if(countDownLatch.getCount()==0)
            return 0;
        else
            return 1;
    }
}
