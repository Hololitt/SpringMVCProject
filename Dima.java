import java.util.*;

// 21.02.2023
public class Main {
    public static void main(String[] args) {
        ArrayList<CoupleOfWords> list = new ArrayList<>();
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many words you want to learn?");
        int countOfWords = scanner.nextInt();
        scanner.nextLine();
        for(int i = 0; i<countOfWords; i++){
            System.out.print("Enter the word and translation");
            list.add(new CoupleOfWords(scanner.nextLine(),scanner.nextLine()));
        }
        while(!list.isEmpty()){
            CoupleOfWords coupleOfWords = list.get(random.nextInt(0, list.size()));
            coupleOfWords.check(list, coupleOfWords);
        }
        System.out.println("Program is finished");
    }
}
class CoupleOfWords {
    public final String reset = "\u001B[0m";
    public final String green = "\u001B[32m";
    public final String red = "\u001B[31m";
    public int counts = 5;
    private final String word;
    private final String translation;
    CoupleOfWords(String word, String translation){
        this.word = word;
        this.translation = translation;
    }
    public void check(ArrayList<CoupleOfWords> list, CoupleOfWords coupleOfWords){
        var scanner = new Scanner(System.in);
        System.out.println("write translation to word "+ word);
        if(Objects.equals(scanner.nextLine(), translation)){
            System.out.println(green+"True!"+reset);
            counts--;
        }else{
            System.out.println(red+"False!"+reset);
            System.out.println("Write answer was "+translation);
        }
        if(counts == 0){
            list.remove(coupleOfWords);
            System.out.println("You learned this word");
        }
    }
}
