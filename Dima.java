import java.io.*;
import java.util.*;
import java.util.List;

// 26.02.2023
public class Main {
    public static void main(String[] args) throws IOException{
        ArrayList<CoupleOfWords> list = new ArrayList<>();
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your words for learning");
        try {
            while (true) {
                System.out.print("Enter the word: ");
                String word = validator(scanner.nextLine());
                if(word.equals("ready"))
                    break;
                System.out.print("Enter the translation for to this word: ");
                String translation = validator(scanner.nextLine());
                if(translation.equals("ready"))
                    break;
                list.add(new CoupleOfWords(word, translation));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        printInFile(list);
        while(!list.isEmpty()){
            var coupleOfWords = list.get(random.nextInt(0, list.size()));
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
        for(var s : list){
            sb.append(s).append(System.lineSeparator());
        }
        fileWriter.write(sb.toString());
        fileWriter.close();
    }
}
class CoupleOfWords {
    private int counts = 5;
    private final String word;
    private final String translation;
    CoupleOfWords(String word, String translation) {
        this.word = word;
        this.translation = translation;
    }
    public void check(ArrayList<CoupleOfWords> list, CoupleOfWords coupleOfWords) {
        var scanner = new Scanner(System.in);
        var random = new Random();
        List<String> wordAndTranslation = new ArrayList<>();
        wordAndTranslation.add(word);
        wordAndTranslation.add(translation);
        int numberOfVariable = random.nextInt(0, wordAndTranslation.size());
        System.out.println("write translation to word "+ wordAndTranslation.get(numberOfVariable));
wordAndTranslation.remove(numberOfVariable);
        String translationFromUser = scanner.nextLine();
        if(translationFromUser.equals("finish")){
            System.out.println("Program is finished");
            System.exit(0);
        }
        if(translationFromUser.equals("skip")){
            System.out.println("This word was skipped");
            list.remove(coupleOfWords);
        }
        else
        if(translationFromUser.equals(wordAndTranslation.get(0))){
            System.out.println(green+"True!"+reset);
            counts--;
        }else {
            System.out.println(red+"False!"+reset);
            System.out.println("Write answer was "+wordAndTranslation.get(0));
        }
        if(counts == 0){
            list.remove(coupleOfWords);
            System.out.println("You learned this word");
        }
    }
    public final String reset = "\u001B[0m";
    public final String green = "\u001B[32m";
    public final String red = "\u001B[31m";
}
