import java.io.*;
import java.util.*;

// 24.02.2023
public class Main {
    public static void main(String[] args) throws IOException{
        ArrayList<CoupleOfWords> list = new ArrayList<>();
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many words you want to learn?");
        int countOfWords = scanner.nextInt();
        scanner.nextLine();
        try {
            for (int i = 0; i < countOfWords; i++) {
                System.out.print("Enter the word: ");
                var word = validator(scanner.nextLine());
                System.out.print("Enter the translation for to this word: ");
                var translation = validator(scanner.nextLine());
                list.add(new CoupleOfWords(word, translation));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        while(!list.isEmpty()){
            CoupleOfWords coupleOfWords = list.get(random.nextInt(0, list.size()));
            coupleOfWords.check(list, coupleOfWords);
        }
        System.out.println("Program is finished");
    }
    public static String validator(String word) throws Exception{
        if(word == null || word.equals("")){
            throw new Exception();
        }
        else
            return word;
    }
}
class CoupleOfWords {
    public final String reset = "\u001B[0m";
    public final String green = "\u001B[32m";
    public final String red = "\u001B[31m";
    public int counts = 1;
    private final String word;
    private final String translation;
    public String toString(){
        return word + " " + translation;
    }
    CoupleOfWords(String word, String translation) {
        this.word = word;
        this.translation = translation;
    }
    public void check(ArrayList<CoupleOfWords> list, CoupleOfWords coupleOfWords) {
        var scanner = new Scanner(System.in);
        System.out.println("write translation to word "+ translation);
        if(Objects.equals(scanner.nextLine(), word)){
            System.out.println(green+"True!"+reset);
            counts--;
        }else{
            System.out.println(red+"False!"+reset);
            System.out.println("Write answer was "+word);
        }
        if(counts == 0){
            list.remove(coupleOfWords);
            System.out.println("You learned this word");
        }
    }
}
