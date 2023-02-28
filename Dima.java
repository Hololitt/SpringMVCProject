import java.io.*;
import java.util.*;
import java.util.List;

// 28.02.2023
public class Main {
    public static void main(String[] args) throws Exception {
        ArrayList<CoupleOfWords> list = new ArrayList<>();
        Random random = new Random();
      Scanner scanner = new Scanner(System.in);
        System.out.println("Set the word by yourself, or copy from file?");
        System.out.println("Enter: set/file");
        String kindOfStart = validator(scanner.nextLine());
        if(kindOfStart.equals("set")){
            setWords(list);
        }else if(kindOfStart.equals("file")){
            setElementsFromFileToList(list);
        }else{
            throw new Exception();
        }
printInFile(list);
        while(!list.isEmpty()){
            var coupleOfWords = list.get(random.nextInt(0, list.size()));
            coupleOfWords.check(list, coupleOfWords);
        }
        System.out.println("Program is finished");
    }
    public static void setWords(ArrayList<CoupleOfWords> list){
        var scanner = new Scanner(System.in);
        System.out.println("Enter your words for learning");
        try {
            while(true) {
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
    public static void printInFile(ArrayList<CoupleOfWords> list) throws IOException {
        var fileWriter = new FileWriter(
                "C:\\Users\\holol\\Desktop\\IdeaProjects\\first\\src\\Training\\TrainingOfWords\\Words.txt");
        StringBuilder sb = new StringBuilder();
        for (var indexOfObject : list) {
            sb.append(indexOfObject.getWord()).append(System.lineSeparator());
            sb.append(indexOfObject.getTranslation()).append(System.lineSeparator());
        }
        fileWriter.write(sb.toString());
        fileWriter.close();
    }
    public static void setElementsFromFileToList(ArrayList<CoupleOfWords> list) throws Exception {
        var file = new File(
                "C:\\Users\\holol\\Desktop\\IdeaProjects\\first\\src\\Training\\TrainingOfWords\\Words.txt");
        var scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            list.add(new CoupleOfWords(validator(scanner.nextLine()), validator(scanner.nextLine())));
        }
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
    public String toString(){
        return word + " " + translation;
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
        String reset = "\u001B[0m";
        if(translationFromUser.equals("skip")){
            System.out.println("This word was skipped");
            list.remove(coupleOfWords);
        }
        else
        if(translationFromUser.equals(wordAndTranslation.get(0))){
            String green = "\u001B[32m";
            System.out.println(green +"True!"+ reset);
            counts--;
        }else {
            String red = "\u001B[31m";
            System.out.println(red+"False!"+ reset);
            System.out.println("Write answer was "+wordAndTranslation.get(0));
        }
        if(counts == 0){
            list.remove(coupleOfWords);
            System.out.println("You learned this word");
        }
    }
    public String getWord(){
        return word;
    }
    public String getTranslation(){
        return translation;
    }
}
