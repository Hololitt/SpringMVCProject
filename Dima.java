import java.io.*;
import java.util.*;
import java.util.List;

// 25.02.2023
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
                String word = validator(scanner.nextLine());
                System.out.print("Enter the translation for to this word: ");
                String translation = validator(scanner.nextLine());
                list.add(new CoupleOfWords(word, translation));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        printInFile(list);
        while(!list.isEmpty()){
            CoupleOfWords coupleOfWords = list.get(random.nextInt(0, list.size()));
            coupleOfWords.check(list, coupleOfWords);
        }
        System.out.println("Program is finished");
    }
    public static String validator(String word) throws Exception{
        if(word == null || word.equals("")){
            throw new Exception("unknown type");
        }else if(word.equals("finish")){
            System.out.println("Program is finished");
            System.exit(0);
        }
            return word;
    }
    public static void printInFile(List<CoupleOfWords> list) throws IOException {
        var fileWriter = new FileWriter(
                "C:\\Users\\holol\\Desktop\\IdeaProjects\\first\\src\\Training\\TrainingOfWords\\Words.txt");
        StringBuilder sb = new StringBuilder();
        for(CoupleOfWords s : list){
            sb.append(s).append(System.lineSeparator());
        }
        String b = sb.toString();
        fileWriter.append(b);
        fileWriter.close();
    }
}
class CoupleOfWords {
    public final String reset = "\u001B[0m";
    public final String green = "\u001B[32m";
    public final String red = "\u001B[31m";
    public int counts = 5;
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
        var random = new Random();
        List<String> words = new ArrayList<>();
        words.add(word);
        words.add(translation);
        int numberOfVariable = random.nextInt(0, words.size());
        System.out.println("write translation to word "+ words.get(numberOfVariable));
        words.remove(numberOfVariable);
        String translationFromUser = scanner.nextLine();
        if(translationFromUser.equals("finish")){
            System.out.println("Program is finished");
            System.exit(0);
        }
        if(translationFromUser.equals(words.get(0))){
            System.out.println(green+"True!"+reset);
            counts--;
        }else {
            System.out.println(red+"False!"+reset);
            System.out.println("Write answer was "+word);
        }
        if(counts == 0){
            list.remove(coupleOfWords);
            System.out.println("You learned this word");
        }
    }
}
