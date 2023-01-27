import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class Training27 {
    static <T> void start(T t, String usersMethod) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = t.getClass().getDeclaredMethods();
        for(Method method : methods){
            if(method.getName().equals(usersMethod)){
                method.invoke(t);
            }
        }
    }
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Word1 word1 = new Word1(new CountDownLatch(5));
        Word2 word2 = new Word2(new CountDownLatch(5));
        Scanner scanner = new Scanner(System.in);
String usersMethod = scanner.nextLine();
        start(new Word1(new CountDownLatch(5)), usersMethod);
        usersMethod = scanner.nextLine();
        start(new Word2(new CountDownLatch(5)), usersMethod);

        CountDownLatch countDownLatch = new CountDownLatch(5);
        List<Word1> list = new ArrayList<>();
        list.add(new Word1(countDownLatch));
        list.add(new Word2(countDownLatch));
for(Word1 t : list){
    System.out.println("Setting the word");
    t.setWord1(scanner.nextLine());
    System.out.println(word1.getWord1());
    System.out.println("Setting translate to word");
    t.setWord2(scanner.nextLine());
}
String taskForUser = "Translate the word ";
Random random = new Random();
       switch (random.nextInt(0, list.size())) {
            case 0 -> {
                System.out.println(taskForUser + word1.getWord2());
                word1.check(scanner.nextLine());
            }
            case 1 -> {
                System.out.println(taskForUser + word2.getWord2());
                word2.check(scanner.nextLine());
            }
            default -> System.out.println("number outside the search area");
        }

    }

}
class Word1 {
    @Override
    public String toString() {
        return "Word1{" +
                "word1='" + word1 + '\'' +
                ", word2='" + word2 + '\'' +
                '}';
    }
    private final CountDownLatch countDownLatch;
    Word1(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }
   private String word1;
    private String word2;
public void check(String word){
    if(countDownLatch.getCount() == 0){
        System.out.println("finish");
    }
    if(Objects.equals(this.word2, word)){
        countDownLatch.countDown();
        System.out.println("true");
    }else{
        System.out.println("Correct answer is " + getWord1());
    }
}
    public String getWord1() {
        return word1;
    }

    public void setWord1(String word1) {
        this.word1 = word1;
    }

    public String getWord2() {
        return word2;
    }

    public void setWord2(String word2) {
        this.word2 = word2;
    }
}
class Word2 extends Word1 {
    Word2(CountDownLatch countDownLatch) {
        super(countDownLatch);
    }
   private String word1;
    private String word2;

    @Override
    public String getWord1() {
        return word1;
    }

    @Override
    public String getWord2() {
        return word2;
    }
}
